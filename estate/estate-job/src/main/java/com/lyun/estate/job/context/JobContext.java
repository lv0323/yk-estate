package com.lyun.estate.job.context;

import com.lyun.estate.core.supports.context.BaseContext;
import org.springframework.stereotype.Component;

@Component
public class JobContext extends BaseContext {
    private final static String JOB_NAME = "jobName";

    private static final String CORRELATION_ID = "correlationId";

    public String getJobName() {
        return (String) get(JOB_NAME);
    }

    public void setJobName(String jobName) {
        put(JOB_NAME, jobName);
    }

    public String getCorrelationId() {
        return (String) get(CORRELATION_ID);
    }

    public void setCorrelationId(String CorrelationId) {
        put(CORRELATION_ID, CorrelationId);
    }

}
