package com.lyun.estate.biz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({"com.lyun.estate.biz"})
@MapperScan({"com.lyun.estate.biz.*.repository"})
@PropertySource(value = "classpath:/biz/biz.properties")
public class BizConfig {

}
