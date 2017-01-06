package com.lyun.estate.job;

import com.lyun.estate.job.config.JobConfig;
import com.lyun.estate.job.supports.context.JobExecutionContext;
import com.lyun.estate.job.test.TestJob;
import com.lyun.estate.job.user.AttentionJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
@EnableAsync
@Import(JobConfig.class)
public class JobScheduler implements SchedulingConfigurer {

    @Autowired
    JobExecutionContext jobExecutionContext;

    @Autowired
    private TestJob testJob;

    @Autowired
    private AttentionJob attentionJob;

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());

        // add your task here
        taskRegistrar.addCronTask(() -> testJob.output(), "0/5 * * * * *");
        taskRegistrar.addCronTask(attentionJob, "0/1 * * * * *");
    }
}
