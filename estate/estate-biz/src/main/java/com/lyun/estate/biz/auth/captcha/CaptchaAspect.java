package com.lyun.estate.biz.auth.captcha;

import com.lyun.estate.biz.user.service.CaptchaService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class CaptchaAspect {

    @Autowired
    CaptchaService captchaService;

    public void verify(VerifyCode verifyCode) {
        if(StringUtils.isEmpty(verifyCode.getClientId())){
            throw new ValidateException("clientId.isNull", "clientId不能为空");
        }
        if(StringUtils.isEmpty(verifyCode.getVerifyId())){
            throw new ValidateException("verifyId.isNull", "图片验证码Id不能为空");
        }
        if(StringUtils.isEmpty(verifyCode.getVerifyCode())){
            throw new ValidateException("verifyCode.isNull", "图片验证码不能为空");
        }
        if (!captchaService.isVerifyCodeCorrect(verifyCode.getClientId(), verifyCode.getVerifyId(), verifyCode.getVerifyCode())) {
            throw new ValidateException("verifyCode.illegal", "图片验证码不正确");
        }
    }

    @Before(value = "@annotation(checkCaptcha)", argNames = "joinPoint,checkCaptcha")
    public void verifyPointCut(JoinPoint joinPoint, CheckCaptcha checkCaptcha) {
        Optional<Object> optional = Arrays.asList(joinPoint.getArgs()).stream()
                .filter(a -> a != null && VerifyCode.class.isAssignableFrom(a.getClass())).findAny();
        if (optional.isPresent()) {
            verify((VerifyCode) optional.get());
        }
    }


}
