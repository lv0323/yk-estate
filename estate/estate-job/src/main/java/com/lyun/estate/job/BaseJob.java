package com.lyun.estate.job;


import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.job.supports.context.JobExecutionContext;

public abstract class BaseJob implements Runnable {
    private JobExecutionContext jobExecutionContext;

    @Override
    public void run() {
        initJobMeta();
        runJob();
        clear();
    }

    protected void initJobMeta() {
        jobExecutionContext.setCorrelationId(CommonUtil.getUuid());
    }

    private void clear() {
        jobExecutionContext.clear();
    }

    protected abstract void runJob();

    protected abstract void setJobExecutionContext();

}
