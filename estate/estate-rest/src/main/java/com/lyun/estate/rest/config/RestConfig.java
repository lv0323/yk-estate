package com.lyun.estate.rest.config;

import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan({"com.lyun.estate.rest"})
@Import({CoreConfig.class, BizConfig.class})
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@EnableSwagger2
public class RestConfig {

}
