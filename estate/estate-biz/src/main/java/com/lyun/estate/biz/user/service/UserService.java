package com.lyun.estate.biz.user.service;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.TokenMapper;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.*;
import com.lyun.estate.biz.user.service.validator.ChangePasswordResourceValidator;
import com.lyun.estate.biz.user.service.validator.LoginResourceValidator;
import com.lyun.estate.biz.user.service.validator.RegisterResourceValidator;
import com.lyun.estate.biz.utils.clock.ClockTools;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.EstateBizException;
import com.lyun.estate.core.supports.exceptions.ValidateException;
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

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenMapper tokenMapper;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    ClockTools clockTools;
    @Autowired
    Environment environment;
    @Autowired
    ExecutionContext executionContext;

    private int getDefaultValidDays() {
        return Integer.valueOf(environment.getRequiredProperty("register.login.default.valid.days"));
    }

    @Transactional
    public RegisterResponse register(RegisterResource registerResource, SmsCode smsCode) {
        if (StringUtils.isEmpty(registerResource.getType())) {
            registerResource.setType(UserType.CUSTOMER);
        }
        DataBinder dataBinder = new DataBinder(registerResource, "userRegister");
        dataBinder.setValidator(new RegisterResourceValidator(userMapper, smsCode));
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
                .setSalt(salt)
                .setType(registerResource.getType())) == 1
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
                    .setJwt(tokenResponse.getJwt());
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
            return getLoginToken(loginUser, loginResource.getValidDays() * 24);
        } else {
            throw new ValidateException("user.login.error", "用户名或密码错误");
        }
    }

    private TokenResponse getLoginToken(User user, int defaultValidDays) {
        JWTToken jwtToken = tokenProvider.generate(String.valueOf(user.getId()),
                Integer.valueOf(executionContext.getClientId()),
                defaultValidDays * 24,
                new HashMap<String, Object>() {{
                    put("clientId", executionContext.getClientId());
                    put("browserName", executionContext.getBrowserName());
                    put("osName", executionContext.getOsName());
                    put("userAddress", executionContext.getUserAddress());
                }});
        return new TokenResponse().setJwt(jwtToken.getToken());
    }

    @Transactional
    public TokenResponse changePassword(ChangePasswordResource changePasswordResource, SmsCode smsCode, JWTToken token) {
        changePasswordResource.setUserId(Long.valueOf(executionContext.getUserId()));
        DataBinder dataBinder = new DataBinder(changePasswordResource, "changePassword");
        final User[] loginUser = {null};
        dataBinder.setValidator(new ChangePasswordResourceValidator(userMapper, smsCode, (User user) -> {
            String salt = CommonUtil.getUuid();
            if (userMapper.updateUser((User) user.setSalt(salt)
                    .setHash(CommonUtil.encryptBySha256(salt + changePasswordResource.getPassword()))
                    .setUpdateById(Long.parseLong(executionContext.getUserId()))) != 1) {
                throw new ValidateException("password.change.fail", "密码修改失败");
            }
            loginUser[0] = user;
        }));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.password.change", bindingResult.getAllErrors());
        }
        if (changePasswordResource.isLogin()) {
            tokenProvider.invalidAllUserToken(String.valueOf(loginUser[0].getId()));
            return getLoginToken(loginUser[0], getDefaultValidDays());
        }
        return new TokenResponse();
    }
}
