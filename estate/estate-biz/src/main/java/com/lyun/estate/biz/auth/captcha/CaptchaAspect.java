package com.lyun.estate.biz.auth.captcha;

import com.lyun.estate.biz.user.service.CaptchaService;
import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.utils.ValidateUtil;
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
    @Autowired
    RestContext restContext;

    public void check(Captcha captcha) {
        check(captcha, true);
    }

    public void check(Captcha captcha, boolean evictAfterSuccess) {
        if (!ValidateUtil.isClientId(captcha.getClientId())) {
            throw new ValidateException("clientId.not.exists", "客户端编号不存在");
        }
        if (StringUtils.isEmpty(captcha.getId())) {
            throw new ValidateException("id.isNull", "图片验证码Id不能为空");
        }
        if (StringUtils.isEmpty(captcha.getCode())) {
            throw new ValidateException("code.isNull", "图片验证码不能为空");
        }

        if (!captchaService.isCaptchaCorrect(captcha.getClientId(), captcha.getId(), captcha.getCode(), evictAfterSuccess)) {
            throw new ValidateException("code.illegal", "图片验证码不正确");
        }
        restContext.setClientId(captcha.getClientId() + "");
    }

    @Before(value = "@annotation(checkCaptcha)", argNames = "joinPoint,checkCaptcha")
    public void verifyPointCut(JoinPoint joinPoint, CheckCaptcha checkCaptcha) {
        Arrays.stream(joinPoint.getArgs())
                .filter(a -> a != null && Captcha.class.isAssignableFrom(a.getClass()))
                .findAny()
                .ifPresent(o -> check((Captcha) o, checkCaptcha.evictAfterSuccess()));
    }


}
