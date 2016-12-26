package com.lyun.estate.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lyun.estate.biz.config.BizConfig;
import com.lyun.estate.core.config.CoreConfig;
import com.lyun.estate.core.supports.resolvers.PageBoundsArgumentResolver;
import com.lyun.estate.core.supports.resolvers.PageListSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Configuration
@ComponentScan({"com.lyun.estate.rest"})
@Import({CoreConfig.class, BizConfig.class})
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@EnableSwagger2
public class RestConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private PageBoundsArgumentResolver pageBoundsArgumentResolver;


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


}
