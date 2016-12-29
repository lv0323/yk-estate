package com.lyun.estate.mgt.supports;

import com.lyun.estate.core.supports.ExecutionContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Component
public class RequestContext implements ExecutionContext {
    private static final String prefix = "executionContext.";
    private Set<String> keys = new HashSet<>();

    @Override
    public String get(String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        return MDC.get(prefix + key);
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
