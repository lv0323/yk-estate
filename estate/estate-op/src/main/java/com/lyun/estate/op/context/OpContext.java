package com.lyun.estate.op.context;

import com.lyun.estate.core.supports.context.BaseContext;
import org.springframework.stereotype.Component;

@Component
public class OpContext extends BaseContext {

    private static final String CORRELATION_ID = "correlationId";

    public String getCorrelationId() {
        return (String) get(CORRELATION_ID);
    }

    public void setCorrelationId(String CorrelationId) {
        put(CORRELATION_ID, CorrelationId);
    }

}
