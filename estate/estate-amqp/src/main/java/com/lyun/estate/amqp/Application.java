package com.lyun.estate.amqp;

import com.lyun.estate.amqp.config.AmqpConfig;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String... args) {
        SpringApplication app = new SpringApplication(AmqpConfig.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
