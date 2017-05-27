package com.lyun.estate.job;

import com.lyun.estate.job.config.JobConfig;
import com.lyun.estate.job.context.JobContext;
import com.lyun.estate.job.fangcollect.Fy01MappingJob;
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
    JobContext jobContext;

    @Autowired
    private Fy01MappingJob fy01MappingJob;

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addCronTask(fy01MappingJob, "0 0 1 ? * SUN");
    }
}
