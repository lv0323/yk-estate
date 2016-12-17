package com.lyun.estate.core.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        OkHttpClient okHttpClient = new OkHttpClient();
        OkHttp3ClientHttpRequestFactory httpRequestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        httpRequestFactory.setConnectTimeout(30 * 1000);
        httpRequestFactory.setReadTimeout(30 * 1000);
        httpRequestFactory.setWriteTimeout(30 * 1000);
        return new RestTemplate(httpRequestFactory);
    }

}
