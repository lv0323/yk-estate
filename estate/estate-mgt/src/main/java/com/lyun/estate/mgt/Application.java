package com.lyun.estate.mgt;

import com.lyun.estate.mgt.config.MgtConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MgtConfig.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    /*
    * Test
    *
    * */

}
