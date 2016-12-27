package com.lyun.estate.biz.auth.token;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class TokenAspect {

    @Autowired
    private TokenProvider tokenProvider;

    @Before(value = "@annotation(checkToken)", argNames = "joinPoint,checkToken")
    public void check(JoinPoint joinPoint, CheckToken checkToken) {
        Optional<Object> token = Arrays.stream(joinPoint.getArgs()).filter(o -> o != null && JWTToken.class.isAssignableFrom(o.getClass())).findAny();
        token.ifPresent(o -> authorize((JWTToken) o));
    }

    public void authorize(JWTToken token) {
        if (!tokenProvider.validate(token.getToken())) {
            throw new ValidateException("token.invalid", "token无效");
        }
    }

}
