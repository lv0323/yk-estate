package com.lyun.estate.biz.appconfig.repository;

import com.lyun.estate.core.config.EstateCacheConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-16.
 */
@Repository
@CacheConfig(cacheManager = EstateCacheConfig.MANAGER_60_5K)
public interface AppConfigRepository {

    @Select("SELECT max(update_time) FROM t_city")
    @Cacheable(cacheNames = {"city-last-updated-time"})
    Date findCityLastUpdatedTime();

    @Select("SELECT max(a) FROM ( SELECT update_time as a FROM t_city WHERE id = #{cityId}\n" +
            "UNION ALL SELECT max(update_time) as a FROM t_district WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(update_time) as a FROM t_sub_district WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(dr.update_time) as a FROM t_district d LEFT JOIN t_district_rel dr on d.id = dr.district_id\n" +
            "WHERE d.city_id = #{cityId}) as b")
    @Cacheable(cacheNames = {"district-rel-last-updated-time"})
    Date findDistrictRelLastUpdatedTime(@Param("cityId") Long cityId);


    @Select("SELECT max(a) FROM ( SELECT update_time as a FROM t_city WHERE id = #{cityId}\n" +
            "UNION ALL SELECT max(update_time) as a FROM t_line WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(update_time) as a FROM t_station WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(lsr.update_time) as a FROM t_line l LEFT JOIN t_line_station_rel lsr on l.id = lsr.line_id\n" +
            "WHERE l.city_id = #{cityId}) as b;")
    @Cacheable(cacheNames = {"line-rel-last-updated-time"})
    Date findLineRelLastUpdatedTime(Long cityId);
}
