package com.lyun.estate.biz.user.service;

import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.TokenMapper;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.biz.user.resources.RegisterResponse;
import com.lyun.estate.biz.user.resources.TokenResponse;
import com.lyun.estate.biz.user.service.validator.ChangePasswordResourceValidator;
import com.lyun.estate.biz.user.service.validator.LoginResourceValidator;
import com.lyun.estate.biz.user.service.validator.RegisterResourceValidator;
import com.lyun.estate.biz.utils.clock.ClockTools;
import com.lyun.estate.core.supports.exceptions.EstateBizException;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.Constant;
import com.lyun.estate.core.supports.types.UserType;
import com.lyun.estate.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    Environment environment;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    ClockTools clockTools;

    @Transactional
    public RegisterResponse register(RegisterResource registerResource) {
        if (StringUtils.isEmpty(registerResource.getType())) {
            registerResource.setType(UserType.CUSTOMER);
        }
        DataBinder dataBinder = new DataBinder(registerResource, "userRegister");
        dataBinder.setValidator(new RegisterResourceValidator(userMapper));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.user.register", "注册校验未通过", bindingResult.getAllErrors());
        }
        RegisterResponse registerResponse = new RegisterResponse();
        String salt = CommonUtil.getUuid();
        if (userMapper.createUser(new User().setMobile(registerResource.getMobile())
                .setEmail(registerResource.getEmail())
                .setUserName(registerResource.getUserName())
                .setHash(CommonUtil.encryptBySha256(salt + registerResource.getPassword()))
                .setSalt(salt)) == 1
                ) {
            registerResponse.setRegistered(true);
        } else {
            throw new EstateBizException("error.user.register", "用户注册失败");
        }
        if (registerResource.isLogin()) {
            TokenResponse tokenResponse = login(new LoginResource()
                    .setEmail(registerResource.getEmail())
                    .setMobile(registerResource.getMobile())
                    .setUserName(registerResource.getUserName())
                    .setPassword(registerResource.getPassword())
                    .setValidDays(getDefaultValidDays()));
            registerResponse
                    .setJwt(tokenResponse.getJwt())
                    .setJwtExpireTime(tokenResponse.getJwtExpireTime());
        }
        return registerResponse;
    }

    @Transactional
    public TokenResponse login(LoginResource loginResource) {
        if (StringUtils.isEmpty(loginResource.getType())) {
            loginResource.setType(UserType.CUSTOMER);
        }
        DataBinder dataBinder = new DataBinder(loginResource, "userLogin");
        dataBinder.setValidator(new LoginResourceValidator());
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.user.login", bindingResult.getAllErrors());
        }
        User loginUser = userMapper.loginUser(loginResource);
        if (loginUser != null && CommonUtil.isSha256Equal(loginUser.getSalt() + loginResource.getPassword(), loginUser.getHash())) {
            JWTToken jwtToken = tokenProvider.generate(String.valueOf(loginUser.getId()), Constant.CLIENT_ID.WEB, loginResource.getValidDays() * 24);
            return new TokenResponse().setJwt(jwtToken.getToken());
        } else {
            throw new ValidateException("user.login.error", "用户名或密码错误");
        }
    }

    private int getDefaultValidDays() {
        return Integer.valueOf(environment.getRequiredProperty("register.login.default.valid.days"));
    }


    public TokenResponse changePassword(ChangePasswordResource changePasswordResource) {
        DataBinder dataBinder = new DataBinder(changePasswordResource, "changePassword");
        dataBinder.setValidator(new ChangePasswordResourceValidator());
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.user.login", bindingResult.getAllErrors());
        }
        return new TokenResponse();
    }
}
