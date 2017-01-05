package com.lyun.estate.mgt.config;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.Strings;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;

@Configuration
@EnableSwagger2
@ComponentScan({"com.lyun.estate"})
@MapperScan("com.lyun.estate.mgt.*.repo")
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

    @Bean
    public HttpMessageConverter jacksonConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.getObjectMapper().setPropertyNamingStrategy(
                PropertyNamingStrategy.SNAKE_CASE);
        return converter;
    }
}
