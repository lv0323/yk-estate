package com.lyun.estate.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class EstateCacheConfig {
    public static final String MANAGER_0_5K = "MANAGER_0_5K";

    public static final String MANAGER_10_5K = "MANAGER_10_5K";

    public static final String MANAGER_60_5K = "MANAGER_10_5K";


    public static final String CAPTCHA_CACHE = "CAPTCHA_CACHE";
    public static final String SMS_CACHE = "SMS_CACHE";
    public static final String MGT_LOGIN_CACHE = "MGT_LOGIN_CACHE";

    @Bean(MANAGER_0_5K)
    @Primary
    public CacheManager manager_0_5K() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(5000));
        return caffeineCacheManager;
    }

    @Bean(MANAGER_10_5K)
    CacheManager manager_10_5K() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(5000)
                .expireAfterWrite(10, TimeUnit.MINUTES));
        return caffeineCacheManager;
    }

    @Bean(MANAGER_60_5K)
    CacheManager manager_60_5K() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(5000)
                .expireAfterWrite(60, TimeUnit.MINUTES));
        return caffeineCacheManager;
    }


}
