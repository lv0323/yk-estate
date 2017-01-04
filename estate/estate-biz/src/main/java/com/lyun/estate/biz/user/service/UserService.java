package com.lyun.estate.biz.user.service;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.TokenMapper;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.biz.user.resources.RegisterResponse;
import com.lyun.estate.biz.user.resources.SaltResource;
import com.lyun.estate.biz.user.resources.SaltResponse;
import com.lyun.estate.biz.user.resources.TokenResponse;
import com.lyun.estate.biz.user.service.validator.ChangePasswordResourceValidator;
import com.lyun.estate.biz.user.service.validator.LoginResourceValidator;
import com.lyun.estate.biz.user.service.validator.RegisterResourceValidator;
import com.lyun.estate.biz.utils.clock.ClockTools;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.EstateBizException;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.SmsType;
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
        DataBinder dataBinder = new DataBinder(registerResource, "userRegister");
        dataBinder.setValidator(new RegisterResourceValidator(userMapper, smsCode));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.user.register", "注册校验未通过", bindingResult.getAllErrors());
        }
        RegisterResponse registerResponse = new RegisterResponse();
        genHash(registerResource);
        if (userMapper.createUser(new User().setMobile(smsCode.getMobile())
                .setEmail(registerResource.getEmail())
                .setUserName(registerResource.getUserName())
                .setSalt(registerResource.getSalt())
                .setHash(registerResource.getHash())
                .setClientId(Integer.valueOf(executionContext.getClientId()))
                .setDescription("logRef:" + executionContext.getCorrelationId())) == 1
                ) {
            registerResponse.setRegistered(true);
        } else {
            throw new EstateBizException("error.user.register", "用户注册失败");
        }
        if (registerResource.isLogin()) {
            registerResponse
                    .setToken(getLoginToken(userMapper.loginUser(new LoginResource().setMobile(smsCode == null ? null : smsCode.getMobile())
                            .setUserName(registerResource.getUserName())
                            .setEmail(registerResource.getEmail())), getDefaultValidDays()).getToken());
        }
        return registerResponse;
    }

    private void genHash(RegisterResource registerResource) {
        if (!StringUtils.isEmpty(registerResource.getPassword())) {
            String salt = CommonUtil.getUuid();
            registerResource
                    .setHash(CommonUtil.encryptBySha256(salt + registerResource.getPassword()))
                    .setSalt(salt);
        }
    }

    @Transactional
    public TokenResponse login(LoginResource loginResource, SmsCode smsCode) {
        if (loginResource != null && smsCode == null) {
            DataBinder dataBinder = new DataBinder(loginResource, "userLogin");
            dataBinder.setValidator(new LoginResourceValidator());
            dataBinder.validate();
            BindingResult bindingResult = dataBinder.getBindingResult();
            if (bindingResult.hasErrors()) {
                throw new ValidateException("warn.user.login", bindingResult.getAllErrors());
            }
            User loginUser = userMapper.loginUser(loginResource);
            if (isPasswordRight(loginResource, loginUser)) {
                return getLoginToken(loginUser, loginResource.getValidDays() * 24);
            } else {
                throw new ValidateException("user.login.error", "用户名或密码错误");
            }
        } else {
            if (smsCode.getType() != SmsType.LOGIN) {
                throw new ValidateException("sms.type.illegal", "短信验证码类型应为'LOGIN'");
            }
            return getLoginToken(userMapper.findUserByMobile(smsCode.getMobile()), getDefaultValidDays());
        }
    }

    private boolean isPasswordRight(LoginResource loginResource, User user) {
        if (user != null) {
            if (!StringUtils.isEmpty(loginResource.getPassword())) {
                return CommonUtil.isSha256Equal(user.getSalt() + loginResource.getPassword(), user.getHash());
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(nullToEmptyStr(loginResource.getMobile()))
                        .append(nullToEmptyStr(loginResource.getEmail()))
                        .append(nullToEmptyStr(loginResource.getUserName()))
                        .append(user.getHash());
                return CommonUtil.isSha256Equal(stringBuffer.toString(), loginResource.getSignature());
            }
        } else {
            return false;
        }

    }

    private String nullToEmptyStr(String str) {
        return StringUtils.isEmpty(str) ? "" : str;
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
        return new TokenResponse().setToken(jwtToken.getToken());
    }

    @Transactional
    public TokenResponse changePassword(ChangePasswordResource changePasswordResource, SmsCode smsCode, JWTToken token) {
        if (!StringUtils.isEmpty(executionContext.getUserId())) {
            changePasswordResource.setUserId(Long.valueOf(executionContext.getUserId()));
        }
        DataBinder dataBinder = new DataBinder(changePasswordResource, "changePassword");
        final User[] loginUser = {null};
        dataBinder.setValidator(new ChangePasswordResourceValidator(userMapper, smsCode, (User user) -> {
            user.setUpdateById(changePasswordResource.getUserId() > 0 ? changePasswordResource.getUserId() : user.getId());
            if (!StringUtils.isEmpty(changePasswordResource.getPassword())) {
                String salt = CommonUtil.getUuid();
                user.setSalt(salt).setHash(CommonUtil.encryptBySha256(salt + changePasswordResource.getPassword()));
            } else {
                user.setSalt(changePasswordResource.getSalt()).setHash(changePasswordResource.getHash());
            }
            if (userMapper.updateUser(user) != 1) {
                throw new ValidateException("password.change.fail", "密码修改失败");
            }
            loginUser[0] = user;
        }));
        dataBinder.validate();
        BindingResult bindingResult = dataBinder.getBindingResult();
        if (bindingResult.hasErrors()) {
            throw new ValidateException("warn.password.change", "密码修改失败", bindingResult.getAllErrors());
        }
        tokenProvider.invalidAllUserToken(String.valueOf(loginUser[0].getId()));
        if (changePasswordResource.isLogin()) {
            return getLoginToken(loginUser[0], getDefaultValidDays());
        }
        return new TokenResponse();
    }

    public SaltResponse getUserSalt(SaltResource saltResource) {
        if (StringUtils.isEmpty(saltResource.getMobile())
                && StringUtils.isEmpty(saltResource.getUserName())
                && StringUtils.isEmpty(saltResource.getEmail())) {
            throw new ValidateException("user.properties.isNull", "手机号/用户名/邮箱为空");
        }
        User user = userMapper.loginUser(new LoginResource()
                .setUserName(saltResource.getUserName())
                .setEmail(saltResource.getEmail())
                .setMobile(saltResource.getMobile()));
        if (user == null) {
            throw new ValidateException("user.not.register", "用户未注册");
        } else {
            return new SaltResponse().setSalt(user.getSalt());
        }
    }
}
