package com.lyun.estate.job.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestJob {

    private final Logger logger = LoggerFactory.getLogger(TestJob.class);

    public void output() {
        logger.info("output message..");
    }

}
