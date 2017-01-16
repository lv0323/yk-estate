package com.lyun.estate.job.supports.context;

import com.lyun.estate.core.supports.ExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionContext extends ExecutionContext {
    private final static String JOB_NAME = "job.name";

    public String getJobName() {
        return get(JOB_NAME);
    }

    public void setJobName(String jobName) {
        set(JOB_NAME, jobName);
    }
}
