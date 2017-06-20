package com.lyun.estate.op.dianping.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by localuser on 2017/6/16.
 */
@Component
public class CommentWriteCache {

    private final Cache<String, Long> cache =
            CacheBuilder
                    .newBuilder()
                    .maximumSize(10000)
                    .expireAfterWrite(1, TimeUnit.MINUTES)
                    .build();

    public void put(String key, Long timestamp) {
        cache.put(key, timestamp);
    }

    public Optional<Long> get(String key) {
        return Optional.ofNullable(cache.getIfPresent(key));
    }

}
