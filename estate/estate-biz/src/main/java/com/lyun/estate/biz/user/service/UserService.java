package com.lyun.estate.biz.user.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.Token;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.sms.def.SmsType;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.*;
import com.lyun.estate.biz.user.service.validator.ChangePasswordResourceValidator;
import com.lyun.estate.biz.user.service.validator.LoginResourceValidator;
import com.lyun.estate.biz.user.service.validator.RegisterResourceValidator;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EasyCodeException;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    RestContext restContext;
    @Autowired
    FileService fileService;


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
                .setClientId(Integer.valueOf(restContext.getClientId()))
                .setDescription("logRef:" + restContext.getCorrelationId())) == 1
                ) {
            registerResponse.setRegistered(true);
        } else {
            throw new EasyCodeException("error.user.register", "用户注册失败");
        }
        if (registerResource.isLogin()) {
            registerResponse
                    .setToken(getLoginToken(userMapper.findUserByMobile(smsCode.getMobile())).getToken());
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
                return getLoginToken(loginUser);
            } else {
                throw new ValidateException("user.login.error", "用户名或密码错误");
            }
        } else {
            if (smsCode.getType() != SmsType.LOGIN) {
                throw new ValidateException("sms.type.illegal", "短信验证码类型应为'LOGIN'");
            }
            return getLoginToken(userMapper.findUserByMobile(smsCode.getMobile()));
        }
    }

    private boolean isPasswordRight(LoginResource loginResource, User user) {
        if (user != null) {
            if (!StringUtils.isEmpty(loginResource.getPassword())) {
                return CommonUtil.isSha256Equal(user.getSalt() + loginResource.getPassword(), user.getHash());
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(Strings.nullToEmpty(loginResource.getMobile()))
                        .append(Strings.nullToEmpty(loginResource.getEmail()))
                        .append(Strings.nullToEmpty(loginResource.getUserName()))
                        .append(user.getHash());
                return CommonUtil.isSha256Equal(stringBuffer.toString(), loginResource.getSignature());
            }
        } else {
            return false;
        }
    }

    private TokenResponse getLoginToken(User user) {
        JWTToken token = tokenProvider.generate(String.valueOf(user.getId()),
                Integer.valueOf(restContext.getClientId()),
                new HashMap<String, Object>() {{
                    put("clientId", restContext.getClientId());
                    put("avatarURI",
                            Optional.ofNullable(user.getAvatorId())
                                    .map(t -> Optional.ofNullable(
                                            fileService.findOne(t))
                                            .map(FileDescription::getFileURI)
                                            .orElse(null))
                                    .orElse(null));
                }});
        return new TokenResponse().setToken(token.getToken()).setRefreshToken(token.getRefreshToken());
    }

    @Transactional
    public TokenResponse changePassword(ChangePasswordResource changePasswordResource, SmsCode smsCode,
                                        JWTToken token) {
        if (restContext.getUserId() != null) {
            changePasswordResource.setUserId(restContext.getUserId());
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
            return getLoginToken(loginUser[0]);
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

    public TokenResponse refreshToken(JWTToken jwtToken) {
        Token oldToken = tokenProvider.checkRefreshToken(jwtToken.getRefreshToken());
        User user = userMapper.findUserById(Long.valueOf(oldToken.getUserId()));
        JWTToken newJWTToken = tokenProvider.refresh(oldToken.getId(),
                String.valueOf(user.getId()),
                new HashMap<String, Object>() {{
                    put("clientId", restContext.getClientId());
                    put("avatarURI",
                            Optional.ofNullable(user.getAvatorId())
                                    .map(t -> Optional.ofNullable(
                                            fileService.findOne(t))
                                            .map(FileDescription::getFileURI)
                                            .orElse(null))
                                    .orElse(null));
                }});
        return new TokenResponse().setToken(newJWTToken.getToken()).setRefreshToken(newJWTToken.getRefreshToken());
    }
}
