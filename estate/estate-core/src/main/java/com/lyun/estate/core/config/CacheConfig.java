package com.lyun.estate.core.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    public static final String EVICT_CACHE_NAME = "evictDefault";

    @Bean("cacheManager")
    @Override
    public CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }

    @Bean("evictCacheManager")
    CacheManager evictCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(new HashSet<String>() {{
            add(EVICT_CACHE_NAME);
        }});
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES));
        return caffeineCacheManager;
    }

    @Override
    public CacheResolver cacheResolver() {
        return context -> {
            List<Cache> result = new ArrayList<>();
            context.getOperation().getCacheNames().forEach(name -> {
                if (name.equals(EVICT_CACHE_NAME)) {
                    result.add(evictCacheManager().getCache(name));
                } else {
                    result.add(cacheManager().getCache(name));
                }
            });
            return result;
        };
    }
}
