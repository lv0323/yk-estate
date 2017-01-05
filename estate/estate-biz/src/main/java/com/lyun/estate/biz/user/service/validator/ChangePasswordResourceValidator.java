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
        if (StringUtils.isEmpty(changePasswordResource.getPassword())) {
            if (StringUtils.isEmpty(changePasswordResource.getSalt())
                    && StringUtils.isEmpty(changePasswordResource.getHash())) {
                errors.reject("salt.hash.isNull", "salt hash不能为空");
            }
        } else if (!ValidateUtil.isPassword(changePasswordResource.getPassword())) {
            errors.reject("password.illegal", "密码格式应为8-32位半角非特殊字符");
        }
        if (smsCode == null) {
            if (changePasswordResource.getSignature() == null && changePasswordResource.getOldPassword() == null) {
                errors.reject("signature.oldPassword.isNull", "signature oldPassword 不能同时为空");
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
                if (!isOldPasswordIllegal(changePasswordResource, user)) {
                    errors.reject("old.password.error", "密码错误");
                }
            }
        } else {
            if (errors.hasErrors()) {
                return;
            }
            user = userMapper.findUserByMobile(smsCode.getMobile());
            if (user == null) {
                errors.reject("user.not.exist", "未找到注册用户");
            }
        }
        if (!errors.hasErrors()) {
            changePasswordValidatorCallBack.callBack(user);
        }
    }

    private boolean isOldPasswordIllegal(ChangePasswordResource changePasswordResource, User user) {
        if (!StringUtils.isEmpty(changePasswordResource.getOldPassword())) {
            return CommonUtil.isSha256Equal(user.getSalt() + changePasswordResource.getOldPassword(), user.getHash());
        } else {
            StringBuffer sb = new StringBuffer();
            sb.append(user.getHash());
            sb.append(changePasswordResource.getSalt());
            sb.append(changePasswordResource.getHash());
            return CommonUtil.isSha256Equal(sb.toString(), changePasswordResource.getSignature());
        }
    }

}
