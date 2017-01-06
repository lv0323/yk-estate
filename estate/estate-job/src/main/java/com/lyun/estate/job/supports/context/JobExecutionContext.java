package com.lyun.estate.job.supports.context;

import com.lyun.estate.core.supports.ExecutionContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JobExecutionContext implements ExecutionContext {
    private final static String prefix = "executionContext.";
    private final static Set<String> keys = new HashSet<>();

    @Override
    public String get(String key) {
        return MDC.get(prefix + key);
    }

    @Override
    public void set(String key, String value) {
        String tmp = prefix + key;
        MDC.put(tmp, value);
        keys.add(tmp);
    }

    @Override
    public void remove(String key) {
        MDC.remove(prefix + key);
    }

    @Override
    public void clear() {
        clear(keys);
    }

    private void clear(Set<String> keys) {
        keys.forEach(this::remove);
    }
}
