package com.lyun.estate.core.supports.resolvers;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.supports.resources.VerifyCode;
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

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Locale;

@ControllerAdvice
public class VerifyCodeArgumentResolver implements HandlerMethodArgumentResolver, Formatter<VerifyCode> {
    public final static String VERIFY_CODE_HEADER = "X-VERIFY-CODE";
    private final static String VERIFY_ID = "verifyId";
    private final static String VERIFY_CODE = "verifyCode";
    private final static String CLIENT_ID = "clientId";

    @Override
    public VerifyCode parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            throw new ValidateException(VERIFY_CODE_HEADER + ".header.isNull", "图片验证码消息头缺失");
        }
        MultiValueMap<String, String> map = QueryStringUtil.parse(text);
        return new VerifyCode()
                .setClientId(Long.parseLong(map.getFirst(CLIENT_ID)))
                .setVerifyCode(map.getFirst(VERIFY_CODE))
                .setVerifyId(map.getFirst(VERIFY_ID));
    }

    @Override
    public String print(VerifyCode object, Locale locale) {
        return null;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return VerifyCode.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String text = webRequest.getHeader(VERIFY_CODE_HEADER);
        if (!StringUtils.hasText(text)) {
            text = webRequest.getNativeRequest(HttpServletRequest.class).getQueryString();
        }
        return parse(text, null);
    }


}
