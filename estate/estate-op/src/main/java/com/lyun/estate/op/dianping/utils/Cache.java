package com.lyun.estate.op.dianping.utils;
import com.lyun.estate.op.config.DianpingProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by localuser on 2017/6/16.
 */
@Component
public class Cache {
    @Autowired
    private DianpingProperties properties;

    HashMap<String, Long> cache = new HashMap<>();

    synchronized public void put(String key, long time){
        cache.put(key, time);
    }

    synchronized public Optional<Long> get(String key){
        Long putTime = null;
        putTime = cache.get(key);

        //不存在
        if(putTime == null){
            return Optional.empty();
        }
        //已过期
        if( System.currentTimeMillis() - putTime > properties.getCacheTimeLimit()){
            cache.remove(key);
            return Optional.empty();
        }

        return Optional.of(putTime);
    }
}
