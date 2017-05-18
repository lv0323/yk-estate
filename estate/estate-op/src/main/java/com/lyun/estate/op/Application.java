package com.lyun.estate.op;

import com.lyun.estate.op.config.OpConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpConfig.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        app.run(args);
    }

}
