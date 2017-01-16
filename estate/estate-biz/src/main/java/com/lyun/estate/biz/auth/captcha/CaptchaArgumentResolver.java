package com.lyun.estate.biz.auth.captcha;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.utils.QueryStringUtil;
import org.springframework.core.MethodParameter;
import org.springframework.format.Formatter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.text.ParseException;
import java.util.Locale;

@ControllerAdvice
public class CaptchaArgumentResolver implements HandlerMethodArgumentResolver, Formatter<Captcha> {
    public final static String CAPTCHA_HEADER = "X-CAPTCHA";
    private final static String CLIENT_ID = "clientId";
    private final static String ID = "id";
    private final static String CODE = "code";

    @Override
    public Captcha parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            throw new ValidateException(CAPTCHA_HEADER + ".header.isNull", "图片验证码消息头缺失");
        }
        MultiValueMap<String, String> map = QueryStringUtil.parse(text);
        int clientId = 0;
        try {
            clientId = Integer.parseInt(map.getFirst(CLIENT_ID));
        } catch (Exception e) {
        }
        return new Captcha()
                .setClientId(clientId)
                .setCode(map.getFirst(CODE))
                .setId(map.getFirst(ID));
    }

    @Override
    public String print(Captcha object, Locale locale) {
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Captcha.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String text = webRequest.getHeader(CAPTCHA_HEADER);
        return parse(text, null);
    }


}
