package com.lyun.estate.biz.keyword.repository;

import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.core.config.EstateCacheConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
@CacheConfig(cacheManager = EstateCacheConfig.MANAGER_360_5K)
public interface KeywordRepository {

    @Select("SELECT  xq.id,  'XIAO_QU' AS domain_type,  c.name,  c.alias,  c.name_kw AS keyword\n" +
            "FROM t_xiao_qu xq LEFT JOIN t_community c ON xq.community_id = c.id WHERE c.city_id = #{cityId} ORDER BY xq.id;")
    @Cacheable(cacheNames = {"keywords-xiao-qu"})
    List<KeywordBean> loadXiaoQu(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'DISTRICT' AS domain_type,  name, name_kw AS keyword FROM t_district WHERE city_id = #{cityId};")
    @Cacheable(cacheNames = {"keywords-district"})
    List<KeywordBean> loadDistrict(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'SUB_DISTRICT' AS domain_type,  name,  name_kw AS keyword FROM t_sub_district WHERE city_id = #{cityId};")
    @Cacheable(cacheNames = {"keywords-sub-district"})
    List<KeywordBean> loadSubDistrict(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'LINE' AS domain_type,  name, name_kw AS keyword FROM t_line WHERE city_id = #{cityId};")
    @Cacheable(cacheNames = {"keywords-line"})
    List<KeywordBean> loadLine(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'STATION' AS domain_type,  name, name_kw AS keyword FROM t_station WHERE city_id = #{cityId};")
    @Cacheable(cacheNames = {"keywords-station"})
    List<KeywordBean> loadStation(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'COMMUNITY' AS domain_type, name , alias, name_kw AS keyword FROM t_community WHERE city_id = #{cityId} ORDER BY id;")
    @Cacheable(cacheNames = {"keywords-community"})
    List<KeywordBean> loadCommunity(@Param("cityId") Long cityId);
}
