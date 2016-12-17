package com.lyun.estate.rest;

import com.lyun.estate.rest.config.RestConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestConfig.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
