package com.lyun.estate.mgt.config;


import com.google.common.base.Strings;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;

@Configuration
@ComponentScan({"com.lyun.estate"})
@Import({CoreConfig.class, BizConfig.class})
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class MgtConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        configurer.setDefaultEncoding("UTF-8");

        // ADD YOUR PARAMS BELOW FOR PARAM USING IN FREEMARKER
        HashMap<String, Object> params = new HashMap<>();
        params.put("contextPath", Strings.nullToEmpty(env.getProperty("estate.context-path")));
        configurer.setFreemarkerVariables(params);

        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver viewResolver() {
        return new FreeMarkerViewResolver();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("/resources/css/")
                .setCachePeriod(60 * 60 * 24);
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/resources/js/")
                .setCachePeriod(60 * 60 * 24);
        registry.addResourceHandler("/img/**")
                .addResourceLocations("/resources/img/")
                .setCachePeriod(0);
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("/resources/favicon.ico")
                .setCachePeriod(60 * 60 * 24 * 7);
    }

}
