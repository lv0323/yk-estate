package com.lyun.estate.biz.message.service.validator;

import com.lyun.estate.biz.message.resources.SmsResource;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.types.SmsType;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SmsResourceValidator implements Validator {
    private UserMapper userMapper;

    public SmsResourceValidator(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SmsResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SmsResource smsResource = (SmsResource) target;
        if (StringUtils.isEmpty(smsResource.getMobile())) {
            errors.reject("mobile.isNull", "手机号码不能为空");
        }
        if (!ValidateUtil.isMobile(smsResource.getMobile())) {
            throw new ValidateException("mobile.illegal", "手机号码非法");
        }
        if (StringUtils.isEmpty(smsResource.getType())) {
            errors.reject("sms.type.isNull", "短信类型不正确");
        }
        if (errors.hasErrors()) {
            return;
        }
        if (smsResource.getType() == SmsType.LOGIN || smsResource.getType() == SmsType.FORGET_PASSWORD) {
            if (userMapper.findUserByMobile(smsResource.getMobile()) == null) {
                errors.reject("mobile.not.register", new String[]{smsResource.getMobile()}, "手机号{0}尚未注册");
            }
        }
    }
}
