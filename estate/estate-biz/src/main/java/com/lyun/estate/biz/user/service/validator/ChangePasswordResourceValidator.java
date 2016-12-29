package com.lyun.estate.biz.user.service.validator;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.biz.user.domain.User;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.biz.user.resources.ChangePasswordResource;
import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ChangePasswordResourceValidator implements Validator {
    private UserMapper userMapper;
    private ChangePasswordValidatorCallBack changePasswordValidatorCallBack;
    private SmsCode smsCode;

    public ChangePasswordResourceValidator(UserMapper userMapper, SmsCode smsCode, ChangePasswordValidatorCallBack changePasswordValidatorCallBack) {
        this.userMapper = userMapper;
        this.smsCode = smsCode;
        this.changePasswordValidatorCallBack = changePasswordValidatorCallBack;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePasswordResource.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChangePasswordResource changePasswordResource = (ChangePasswordResource) target;
        User user = null;
        if (!ValidateUtil.isPassword(changePasswordResource.getPassword())) {
            errors.reject("password.illegal", "密码格式应为8-32位半角非特殊字符");
        }
        if (StringUtils.isEmpty(changePasswordResource.getUserType())) {
            errors.reject("userType.isNull", "用户类型不能为空");
        }
        if (smsCode == null) {
            if (StringUtils.isEmpty(changePasswordResource.getOldPassword())) {
                errors.reject("old.password.isNull", "密码不能为空");
            }
            if (changePasswordResource.getUserId() == 0) {
                errors.reject("userId.isNull", "用户Id不能为空");
            }
            if (errors.hasErrors()) {
                return;
            }
            user = userMapper.changePasswordUser(changePasswordResource);
            if (user == null) {
                errors.reject("user.not.exist", "未找到注册用户");
            } else {
                if (!CommonUtil.isSha256Equal(user.getSalt() + changePasswordResource.getOldPassword(), user.getHash())) {
                    errors.reject("old.password.error", "密码错误");
                }
            }
        } else {
            if (StringUtils.isEmpty(changePasswordResource.getMobile())) {
                errors.reject("mobile.isNull", "忘记密码手机号不能为空");
            } else {
                if (!changePasswordResource.getMobile().equals(smsCode.getMobile())) {
                    errors.reject("mobile.not.match", "手机号码跟验证手机号不一致");
                }
            }
            if (errors.hasErrors()) {
                return;
            }
            user = userMapper.changePasswordUser(changePasswordResource);
            if (user == null) {
                errors.reject("user.not.exist", "未找到注册用户");
            }
        }

        if (!errors.hasErrors()) {
            changePasswordValidatorCallBack.callBack(user);
        }

    }

}
