package com.lyun.estate.biz.keyword.repository;

import com.lyun.estate.biz.keyword.entity.KeywordBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Repository
public interface KeywordRepository {

    @Select("SELECT  id,  'XIAO_QU' AS domain_type, name , alias, (name || CASE WHEN alias IS NULL THEN '' ELSE ',' || alias END) AS keyword FROM t_community ORDER BY id;")
    List<KeywordBean> loadXiaoQu();

    @Select("SELECT  id,  'DISTRICT' AS domain_type,  name, name AS keyword FROM t_district;")
    List<KeywordBean> loadDistrict();

    @Select("SELECT  id,  'SUB_DISTRICT' AS domain_type,  name,  name AS keyword FROM t_sub_district;")
    List<KeywordBean> loadSubDistrict();

    @Select("SELECT  id,  'LINE' AS domain_type,  name, name AS keyword FROM t_line;")
    List<KeywordBean> loadLine();

    @Select("SELECT  id,  'STATION' AS domain_type,  name, name AS keyword FROM t_station;")
    List<KeywordBean> loadStation();
}
