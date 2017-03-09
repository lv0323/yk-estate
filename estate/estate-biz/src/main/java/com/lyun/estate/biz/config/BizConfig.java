package com.lyun.estate.biz.config;

import com.lyun.sms.client.config.SmsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.lyun.estate.biz"})
@MapperScan(basePackages = {"com.lyun.estate.biz.**.repository", "com.lyun.estate.biz.**.repo"})
@PropertySource(value = "classpath:estate/biz/biz.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:estate/biz/oss.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file://${LVJINSUO_HOME}/conf/estate/biz/biz.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file://${LVJINSUO_HOME}/conf/estate/biz/oss.properties", ignoreResourceNotFound = true)
@Import(SmsClientConfig.class)
public class BizConfig {

}
