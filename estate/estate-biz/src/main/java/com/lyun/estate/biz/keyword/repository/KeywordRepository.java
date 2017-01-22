package com.lyun.estate.biz.keyword.repository;

import com.lyun.estate.biz.keyword.entity.KeywordBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
public interface KeywordRepository {

    @Select("SELECT  id,  'XIAO_QU' AS domain_type, name , alias, (name || CASE WHEN alias IS NULL THEN '' ELSE ',' || alias END) AS keyword FROM t_community WHERE city_id = #{cityId} ORDER BY id;")
    List<KeywordBean> loadXiaoQu(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'DISTRICT' AS domain_type,  name, name AS keyword FROM t_district WHERE city_id = #{cityId};")
    List<KeywordBean> loadDistrict(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'SUB_DISTRICT' AS domain_type,  name,  name AS keyword FROM t_sub_district WHERE city_id = #{cityId};")
    List<KeywordBean> loadSubDistrict(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'LINE' AS domain_type,  name, name AS keyword FROM t_line WHERE city_id = #{cityId};")
    List<KeywordBean> loadLine(@Param("cityId") Long cityId);

    @Select("SELECT  id,  'STATION' AS domain_type,  name, name AS keyword FROM t_station WHERE city_id = #{cityId};")
    List<KeywordBean> loadStation(@Param("cityId") Long cityId);
}
