package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.user.resources.LoginResource;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginResourceValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginResource loginResource = (LoginResource) target;
        if (StringUtils.isEmpty(loginResource.getPassword())) {
            if (StringUtils.isEmpty(loginResource.getSignature())) {
                errors.reject("signature.password.both.null", "签名信息和密码不能同时为空");
            }
        }
        if (!StringUtils.isEmpty(loginResource.getEmail())) {
            if (!ValidateUtil.isEmail(loginResource.getEmail())) {
                errors.rejectValue("email.illegal", "邮箱格式不正确");
            }
        } else if (!StringUtils.isEmpty(loginResource.getMobile())) {
            if (!ValidateUtil.isMobile(loginResource.getMobile())) {
                errors.rejectValue("mobile.illegal", "手机号码格式不正确");
            }
        } else if (!StringUtils.isEmpty(loginResource.getUserName())) {
        } else {
            errors.reject("login.error", "请提供用户名/手机号/邮箱登陆");
        }
    }
}
