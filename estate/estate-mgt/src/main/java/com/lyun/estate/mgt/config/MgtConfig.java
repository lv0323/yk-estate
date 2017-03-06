package com.lyun.estate.mgt.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Strings;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.core.supports.pagebound.PageListSerializer;
import com.lyun.estate.core.supports.types.Constant;
import com.lyun.estate.mgt.advice.AuthInterceptor;
import com.lyun.estate.mgt.context.MgtContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;

@Configuration
@EnableSwagger2
@ComponentScan({"com.lyun"})
@MapperScan("com.lyun.estate.mgt.*.repo")
@Import({CoreConfig.class, BizConfig.class})
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class MgtConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Autowired
    private MgtContext mgtContext;

    @Autowired
    private PageBoundsArgumentResolver pageBoundsArgumentResolver;

    @Bean
    public FreeMarkerConfigurer freemarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/freemarker/");
        configurer.setDefaultEncoding("UTF-8");

        // ADD YOUR PARAMS BELOW FOR PARAM USING IN FREEMARKER
        HashMap<String, Object> params = new HashMap<>();
        params.put("contextPath", Strings.nullToEmpty(env.getProperty("mgt.context.path")));
        configurer.setFreemarkerVariables(params);
        params.put("bts", System.currentTimeMillis() / 1000L);
        params.put("clientId", String.valueOf(Constant.CLIENT_ID.MGT));
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
                .setCachePeriod(60 * 60 * 24);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        if (this.pageBoundsArgumentResolver != null) {
            argumentResolvers.add(this.pageBoundsArgumentResolver);
        }
    }

    @Bean
    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(new PageListSerializer());
        return new MappingJackson2HttpMessageConverter(objectMapper.registerModule(module));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(mgtContext))
                .excludePathPatterns(
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/fonts/**",
                        "/**/favicon.ico",
                        "/index",
                        "/swagger-resources/**",
                        "/**/api-docs",
                        "/captcha",
                        "/sms",
                        "/api/auth/active",
                        "/api/auth/salt",
                        "/api/auth/login");
    }
}
