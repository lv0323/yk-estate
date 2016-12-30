package com.lyun.estate.biz.auth.sms;

import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SmsCodeAspect {

    @Autowired
    SmsService smsService;
    @Autowired
    ExecutionContext executionContext;

    public void check(SmsCode smsCode) {
        if (!smsService.isSmsCodeCorrect(smsCode)) {
            throw new ValidateException("smsCode.illegal", "短信验证码错误");
        }
        executionContext.setClientId(smsCode.getClientId() + "");
    }

    @Before(value = "@annotation(checkSmsCode)", argNames = "joinPoint,checkSmsCode")
    public void verifyPointCut(JoinPoint joinPoint, CheckSmsCode checkSmsCode) {
        Arrays.stream(joinPoint.getArgs())
                .filter(a -> a != null && SmsCode.class.isAssignableFrom(a.getClass()))
                .findAny()
                .ifPresent(o -> check((SmsCode) o));
    }
}
