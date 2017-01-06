package com.lyun.estate.job.user;

import com.lyun.estate.job.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AttentionJob extends BaseJob {
    @Override
    public Logger logger() {
        return LoggerFactory.getLogger(AttentionJob.class);
    }

    @Override
    public void runJob() {
        setJobName("关注消息推送");
        // job detail
    }

}
