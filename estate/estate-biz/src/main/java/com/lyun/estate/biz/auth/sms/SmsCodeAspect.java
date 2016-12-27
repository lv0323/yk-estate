package com.lyun.estate.biz.auth.sms;

import com.lyun.estate.biz.message.service.SmsService;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.resources.SmsCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class SmsCodeAspect {

    @Autowired
    SmsService smsService;

    public void verify(SmsCode smsCode) {
        if (!smsService.isSmsCodeCorrect(smsCode)) {
            throw new ValidateException("smsCode.illegal", "短信验证码错误");
        }
    }

    @Before(value = "@annotation(checkSmsCode)", argNames = "joinPoint,checkSmsCode")
    public void verifyPointCut(JoinPoint joinPoint, CheckSmsCode checkSmsCode) {
        Optional<Object> optional = Arrays.asList(joinPoint.getArgs()).stream()
                .filter(a -> a != null && SmsCode.class.isAssignableFrom(a.getClass())).findAny();
        if (optional.isPresent()) {
            verify((SmsCode) optional.get());
        }
    }
}
