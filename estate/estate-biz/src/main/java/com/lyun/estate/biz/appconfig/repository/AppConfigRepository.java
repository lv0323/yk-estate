package com.lyun.estate.biz.appconfig.repository;

import com.lyun.estate.biz.appconfig.entity.Region;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-16.
 */
@Repository
public interface AppConfigRepository {

    @Select("SELECT *, 'CITY' AS TYPE FROM t_city ORDER BY id")
    List<Region> findCities();

    @Select("SELECT max(a) FROM ( SELECT create_time AS a FROM t_city UNION ALL SELECT update_time AS a FROM t_city ) b")
    Date findCityLastUpdatedTime();

    @Select("SELECT * , 'DISTRICT' AS TYPE FROM t_district WHERE city_id = #{cityId} ORDER BY id")
    List<Region> findDistricts(@Param("cityId") Long cityId);


    @Select("SELECT sb.*, 'SUB_DISTRICT' AS TYPE FROM t_district_rel dr LEFT JOIN t_sub_district sb on dr.sub_district_id = sb.id WHERE dr.district_id =#{districtId} ORDER BY sb.id")
    List<Region> findSubDistricts(@Param("districtId") Long districtId);

    @Select("SELECT max(a) FROM (\n" +
            "SELECT create_time as a FROM t_city WHERE id = #{cityId}\n" +
            "UNION ALL SELECT update_time as a FROM t_city WHERE id = #{cityId}\n" +
            "UNION ALL SELECT max(create_time) as a FROM t_district WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(update_time) as a FROM t_district WHERE city_id = #{cityId}\n" +
            "UNION ALL SELECT max(sb.create_time) as a FROM t_district d LEFT JOIN t_district_rel dr on d.id = dr.district_id\n" +
            "LEFT JOIN t_sub_district sb on dr.sub_district_id = sb.id WHERE d.city_id = #{cityId}\n" +
            "UNION ALL SELECT max(sb.update_time) as a FROM t_district d LEFT JOIN t_district_rel dr on d.id = dr.district_id\n" +
            "LEFT JOIN t_sub_district sb on dr.sub_district_id = sb.id WHERE d.city_id = #{cityId}) as b;")
    Date findDistrictsRelLastUpdatedTime(@Param("cityId") Long cityId);
}
