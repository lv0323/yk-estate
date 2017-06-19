package com.lyun.estate.op.dianping.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * Created by localuser on 2017/6/16.
 */
public class Cache {

    private Cache() {
        if (CacheHolder.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }
    public static LoadingCache<String, String> getInstance() {
        return CacheHolder.INSTANCE;
    }

    private static class CacheHolder{
        private static final LoadingCache<String, String> INSTANCE =
                CacheBuilder
                        .newBuilder()
                        .maximumSize(1000)
                        .expireAfterAccess(3, TimeUnit.MINUTES)
                        .build(new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) throws Exception {
                                return null;
                            }
                        });
    }
}
