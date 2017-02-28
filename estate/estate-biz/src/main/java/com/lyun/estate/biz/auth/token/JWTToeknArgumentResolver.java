package com.lyun.estate.biz.auth.token;

import com.lyun.estate.biz.auth.sms.SmsCode;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import org.springframework.core.MethodParameter;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.text.ParseException;
import java.util.Locale;

@ControllerAdvice
public class JWTToeknArgumentResolver implements HandlerMethodArgumentResolver, Formatter<JWTToken> {
    public final static String AUTH_HEADER = "auth";
    public final static String TOKEN = "token";
    public final static String REFRESH_TOKEN = "refreshToken";


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SmsCode.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String text = webRequest.getHeader(AUTH_HEADER);
        return parse(text, null);
    }

    @Override
    public JWTToken parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            throw new ValidateException(AUTH_HEADER + ".header.isNull", "AUTH头缺失");
        }
        return new JWTToken(text);
    }

    @Override
    public String print(JWTToken object, Locale locale) {
        return null;
    }
}
