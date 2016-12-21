package com.lyun.estate.biz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.lyun.estate.biz"})
@MapperScan({"com.lyun.estate.biz"})
public class BizConfig {

}
