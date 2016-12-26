package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.RegisterResource;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RegisterResourceValidator implements Validator {
    private UserMapper userMapper;

    public RegisterResourceValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterResource registerResource = (RegisterResource) target;
        if (StringUtils.isEmpty(registerResource.getPassword())) {
            errors.reject("label.isNull", "密码不能为空");
        }
        if (!ValidateUtil.isPassword(registerResource.getPassword())) {
            errors.reject("password.illegal", "密码格式应为8-32位半角非特殊字符");
        }

        boolean flag = false;
        if (!StringUtils.isEmpty(registerResource.getMobile())) {
            if (!ValidateUtil.isMobile(registerResource.getMobile())) {
                errors.reject("mobile.illegal", "手机号码格式错误");
            }
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findByMobile(registerResource.getMobile()))) {
                errors.reject("mobile.registered", new String[]{registerResource.getMobile()}, "手机号码{0}已经注册");
            }
            flag = true;
        }
        if (!StringUtils.isEmpty(registerResource.getUserName())) {
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findByMobile(registerResource.getMobile()))) {
                errors.reject("username.registered", new String[]{registerResource.getUserName()}, "用户名{0}已经注册");
            }
            flag = true;
        }
        if (!StringUtils.isEmpty(registerResource.getEmail())) {
            if (!ValidateUtil.isEmail(registerResource.getEmail())) {
                errors.reject("email.illegal", "邮箱格式错误");
            }
            if (errors.hasErrors()) {
                return;
            }
            if (!StringUtils.isEmpty(userMapper.findByMobile(registerResource.getMobile()))) {
                errors.reject("email.registered", new String[]{registerResource.getEmail()}, "邮箱{0}已经注册");
            }
            flag = true;
        }
        if (!flag) {
            errors.reject("register.error", "需提供手机/邮箱/用户名注册");
        }
    }
}
