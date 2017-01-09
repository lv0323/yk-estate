package com.lyun.estate.job;


import com.lyun.estate.core.utils.CommonUtil;
import com.lyun.estate.job.supports.context.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseJob implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String jobName;
    @Autowired
    private JobExecutionContext jobExecutionContext;

    @Override
    public void run() {
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
        setJobName(jobName());
        jobExecutionContext.setJobName(jobName());
        jobExecutionContext.setCorrelationId(CommonUtil.getUuid());
    }

    private void clear() {
        jobExecutionContext.clear();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public JobExecutionContext getJobExecutionContext() {
        return jobExecutionContext;
    }

    public void setJobExecutionContext(JobExecutionContext jobExecutionContext) {
        this.jobExecutionContext = jobExecutionContext;
    }

    public abstract String jobName();

    public abstract void runJob();
}
