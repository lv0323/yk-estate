package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegisterResourceValidator implements Validator {
    private UserMapper userMapper;
    private SmsCode smsCode;

    public RegisterResourceValidator(UserMapper userMapper, SmsCode smsCode) {
        this.userMapper = userMapper;
        this.smsCode = smsCode;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterResource registerResource = (RegisterResource) target;
        if (StringUtils.isEmpty(registerResource.getPassword())) {
            if (StringUtils.isEmpty(registerResource.getSalt()) ||
                    StringUtils.isEmpty(registerResource.getHash())) {
                errors.reject("password.isNull", "密码不能为空");
            } else {
                if (!ValidateUtil.lengthMin(registerResource.getSalt(), 30)) {
                    errors.reject("salt.illegal", "salt不合法");
                }
                if (!ValidateUtil.lengthMin(registerResource.getHash(), 40)) {
                    errors.reject("hash.illegal", "hash不合法");
                }
            }
        } else {
            if (!ValidateUtil.isPassword(registerResource.getPassword())) {
                errors.reject("password.illegal", "密码格式应为8-32位半角非特殊字符");
            }
        }
        if (smsCode != null) {
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findUserByMobile(smsCode.getMobile()))) {
                errors.reject("mobile.registered", new String[]{smsCode.getMobile()}, "该手机号已经注册");
            }
        } else if (!StringUtils.isEmpty(registerResource.getUserName())) {
            if (!ValidateUtil.lengthMax(registerResource.getUserName(), 24)) {
                errors.reject("userName.maxLength", new Integer[]{24}, "用户名最大长度为{0}");
            }
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findUser(registerResource))) {
                errors.reject("userName.registered", new String[]{registerResource.getUserName()}, "用户名{0}已经注册");
            }
        } else if (!StringUtils.isEmpty(registerResource.getEmail())) {
            if (!ValidateUtil.isEmail(registerResource.getEmail())) {
                errors.reject("email.illegal", "邮箱格式错误");
            }
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findUser(registerResource))) {
                errors.reject("email.registered", new String[]{registerResource.getEmail()}, "邮箱{0}已经注册");
            }
        } else {
            errors.reject("register.error", "需提供手机/邮箱/用户名注册");
        }
    }
}
