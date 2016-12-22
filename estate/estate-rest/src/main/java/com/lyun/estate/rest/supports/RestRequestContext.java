package com.lyun.estate.rest.supports;

import com.lyun.estate.core.supports.ExecutionContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Component
public class RestRequestContext implements ExecutionContext {
    private static final String prefix = "executionContext.";
    @Autowired
    private ApplicationContext applicationContext;
    private Set<String> keys = new HashSet<>();

    @Override
    public String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        String value = MDC.get(prefix + key);
        if (StringUtils.isEmpty(value)) {
            switch (key) {
                case ExecutionContext.METHOD:
                    value = MDC.get(MdcKey.requestMethod.toString());
                    break;
                case ExecutionContext.RESOURCE_PATH:
                    value = MDC.get(MdcKey.requestPath.toString());
                    break;
                case ExecutionContext.USER_ADDRESS:
                    value = MDC.get(MdcKey.requestXForwardedFor.toString());
                    if (StringUtils.isEmpty(value)) {
                        value = MDC.get(MdcKey.requestRemoteHost.toString());
                    }
                    break;
                case ExecutionContext.CLIENT_ID:
                    value = "0";    // root
                    break;
                case ExecutionContext.REQUEST_BASE_URL:
                    value = applicationContext.getEnvironment().getRequiredProperty("core.baseUrl");
                    break;
                case ExecutionContext.REQUEST_LOCAL:
                    value = MDC.get(MdcKey.requestLocale.toString());
                    if (StringUtils.isEmpty(value)) {
                        value = Locale.SIMPLIFIED_CHINESE.toString();
                    }
                    break;
            }
            if (!StringUtils.isEmpty(value)) {
                set(key, value);
            }
        }
        return value;
    }

    @Override
    public void set(String key, String value) {
        if (!keys.contains(key)) {
            keys.add(key);
        }
        if (!StringUtils.isEmpty(value)) {
            MDC.put(prefix + key, value);
        }
    }

    @Override
    public void remove(String key) {
        MDC.remove(prefix + key);
    }

    @Override
    public void clear() {
        keys.forEach(this::remove);
    }
}
