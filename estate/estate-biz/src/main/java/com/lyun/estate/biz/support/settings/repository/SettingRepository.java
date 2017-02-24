package com.lyun.estate.biz.support.settings.repository;

import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.biz.support.settings.entity.Setting;
import com.lyun.estate.core.config.EstateCacheConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheManager = EstateCacheConfig.MANAGER_10_5K)
public interface SettingRepository {

    @Select("SELECT * FROM t_setting WHERE name_space = #{nameSpace} AND  key = #{key};")
    @Cacheable(cacheNames = {"setting-cache"})
    Setting find(@Param("nameSpace") NameSpace nameSpace, @Param("key") String key);

    @Select("SELECT * FROM t_setting WHERE id = #{id}")
    @Cacheable(cacheNames = {"setting-cache"})
    Setting findOne(@Param("id") Long id);


}
