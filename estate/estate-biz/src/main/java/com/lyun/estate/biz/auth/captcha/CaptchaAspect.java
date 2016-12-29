package com.lyun.estate.biz.auth.captcha;

import com.lyun.estate.biz.user.service.CaptchaService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Aspect
@Component
public class CaptchaAspect {

    @Autowired
    CaptchaService captchaService;

    public void check(Captcha Captcha) {
        if (StringUtils.isEmpty(Captcha.getClientId())) {
            throw new ValidateException("clientId.isNull", "clientId不能为空");
        }
        if (StringUtils.isEmpty(Captcha.getId())) {
            throw new ValidateException("id.isNull", "图片验证码Id不能为空");
        }
        if (StringUtils.isEmpty(Captcha.getCode())) {
            throw new ValidateException("code.isNull", "图片验证码不能为空");
        }
        if (!captchaService.isCaptchaCorrect(Captcha.getClientId(), Captcha.getId(), Captcha.getCode())) {
            throw new ValidateException("code.illegal", "图片验证码不正确");
        }
    }

    @Before(value = "@annotation(checkCaptcha)", argNames = "joinPoint,checkCaptcha")
    public void verifyPointCut(JoinPoint joinPoint, CheckCaptcha checkCaptcha) {
        Arrays.stream(joinPoint.getArgs())
                .filter(a -> a != null && Captcha.class.isAssignableFrom(a.getClass()))
                .findAny()
                .ifPresent(o -> check((Captcha) o));
    }


}
