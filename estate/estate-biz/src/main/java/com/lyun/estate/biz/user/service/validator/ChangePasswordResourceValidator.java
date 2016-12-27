package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChangePasswordResourceValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordResource changePasswordResource = (ChangePasswordResource) target;
        if (!changePasswordResource.isForget() && StringUtils.isEmpty(changePasswordResource.getOldPassword())) {
            errors.reject("old.password.isNull", "密码不能为空");
        }
        if (!ValidateUtil.isPassword(changePasswordResource.getPassword())) {
            errors.reject("password.illegal", "密码格式应为8-32位半角非特殊字符");
        }
    }
}
