package com.lyun.estate.biz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.lyun.estate.biz"})
@MapperScan(basePackages = "com.lyun.estate.biz")
@PropertySource(value = "classpath:estate/biz/biz.properties", ignoreResourceNotFound = true)
@PropertySource(value = "classpath:file://${LVJINSUO_HOME}/conf/estate/biz/biz.properties", ignoreResourceNotFound = true)
public class BizConfig {

}
