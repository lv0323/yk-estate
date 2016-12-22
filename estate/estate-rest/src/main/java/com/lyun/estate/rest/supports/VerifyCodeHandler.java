package com.lyun.estate.rest.supports;

import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.core.supports.annotations.CheckVerifyCode;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.resources.VerifyCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

@Aspect
public class VerifyCodeHandler {

    @Autowired
    UserService userService;

    public void verify(VerifyCode verifyCode) {
        if(userService.isVerifyCodeCorrect(verifyCode.getClientId(), verifyCode.getVerifyId(), verifyCode.getVerifyCode())){
            throw new ValidateException("verifyCode.illegal","验证码非法");
        }
    }

    @Before(value = "@annotation(CheckverifyCode)", argNames = "verifyCode")
    public void verifyPointCut(JoinPoint joinPoint) {
        Optional<Object> optional = Arrays.asList(joinPoint.getArgs()).stream()
                .filter(a -> a != null && VerifyCode.class.isAssignableFrom(a.getClass())).findAny();
        if(optional.isPresent()){
            verify((VerifyCode)optional.get());
        }
    }


}
