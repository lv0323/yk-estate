package com.lyun.estate.biz.auth.sms;

import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.biz.sms.def.SmsType;
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
public class SmsCodeArgumentResolver implements HandlerMethodArgumentResolver, Formatter<SmsCode> {
    public final static String SMS_CODE_HEADER = "X-SMS-CODE";
    private final static String SMS_ID = "id";
    private final static String CODE = "code";
    private final static String SERIAL = "serial";
    private final static String TYPE = "type";
    private final static String MOBILE = "mobile";
    private final static String CLIENT_ID = "clientId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SmsCode.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String text = webRequest.getHeader(SMS_CODE_HEADER);
        return parse(text, null);
    }

    @Override
    public SmsCode parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isEmpty(text)) {
            throw new ValidateException(SMS_CODE_HEADER + ".header.isNull", "短信验证码消息头缺失");
        }
        MultiValueMap<String, String> map = QueryStringUtil.parse(text);
        String type = map.getFirst(TYPE);
        String clientId = map.getFirst(CLIENT_ID);
        int cid = StringUtils.isEmpty(clientId) ? 0 : Integer.valueOf(clientId);
        return new SmsCode()
                .setCode(map.getFirst(CODE))
                .setMobile(map.getFirst(MOBILE))
                .setSerial(map.getFirst(SERIAL))
                .setId(map.getFirst(SMS_ID))
                .setType(SmsType.getKeys().contains(type) ? SmsType.valueOf(type) : null)
                .setClientId(cid);
    }

    @Override
    public String print(SmsCode object, Locale locale) {
        return null;
    }
}
