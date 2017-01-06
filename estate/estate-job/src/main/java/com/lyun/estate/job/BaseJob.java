package com.lyun.estate.job;


import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.job.supports.context.JobExecutionContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseJob implements Runnable {


    @Autowired
    private JobExecutionContext jobExecutionContext;
    private String jobName;

    @Override
    public void run() {
        Logger logger = logger();
        initJobMeta();
        logger.info("=== job {} start ===", jobName);
        try {
            runJob();
        } catch (Throwable throwable) {
            logger.error("job {} exception :", jobName, throwable);
        } finally {
            logger.info("=== job {} end ===", jobName);
            clear();
        }
    }

    private void initJobMeta() {
        jobExecutionContext.setCorrelationId(CommonUtil.getUuid());
        jobExecutionContext.setJobName(jobName);
    }

    private void clear() {
        jobExecutionContext.clear();
    }


    public void setJobExecutionContext(JobExecutionContext jobExecutionContext) {
        this.jobExecutionContext = jobExecutionContext;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public abstract Logger logger();

    public abstract void runJob();
}
