package com.lyun.estate.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan({"com.lyun.estate.core"})
@EnableCaching
public class CoreConfig {

    @Bean("evictAfterAccessCacheManager")
    CacheManager evictAfterAccessCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(new HashSet<String>() {{
            add("default");
        }});
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(0, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.MINUTES));
        return caffeineCacheManager;
    }

}
