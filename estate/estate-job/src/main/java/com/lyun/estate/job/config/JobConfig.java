package com.lyun.estate.job.config;


import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.lyun.estate"})
@Import({CoreConfig.class, BizConfig.class})
public class JobConfig {

}
