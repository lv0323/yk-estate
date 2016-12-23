package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.user.domain.User;
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
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterResource registerResource = (RegisterResource) target;
        if (StringUtils.isEmpty(registerResource.getMobile())) {
            errors.reject("mobile.isNull", "手机号码不能为空");
        }
        if (!ValidateUtil.isMobile(registerResource.getMobile())) {
            errors.reject("mobile.notMatch", "手机号码格式错误");
        }
        if (StringUtils.isEmpty(registerResource.getPassword())) {
            errors.reject("label.isNull", "密码不能为空");
        }
        if (!ValidateUtil.isPassword(registerResource.getPassword())) {
            errors.reject("label.lengthMax", null, "密码格式应为8-32位半角非特殊字符");
        }
        if (errors.hasErrors()) {
            return;
        }
        if (!StringUtils.isEmpty(userMapper.findByMobile(registerResource.getMobile()))) {
            errors.reject("mobile.registered", new String[]{registerResource.getMobile()}, "手机号码{0}已经注册");
        }
    }
}
