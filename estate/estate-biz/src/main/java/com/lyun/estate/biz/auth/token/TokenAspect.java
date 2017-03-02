package com.lyun.estate.biz.auth.token;

import com.lyun.estate.core.supports.context.RestContext;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TokenAspect {

    @Autowired
    RestContext restContext;
    @Autowired
    private TokenProvider tokenProvider;

    @Before(value = "@annotation(checkToken)", argNames = "joinPoint,checkToken")
    public void check(JoinPoint joinPoint, CheckToken checkToken) {
        Arrays.stream(joinPoint.getArgs())
                .filter(o -> o != null && JWTToken.class.isAssignableFrom(o.getClass()))
                .findAny()
                .ifPresent(o -> authorize((JWTToken) o));
    }

    public void authorize(JWTToken token) {
        if (!tokenProvider.validate(token.getToken())) {
            throw new EstateException(ExCode.TOKEN_INVALID);
        }
        restContext.setUserId(Long.valueOf(tokenProvider.getSubject(token.getToken())));
        restContext.setClientId((String) tokenProvider.getClaim(token.getToken(), "clientId"));
    }

}
