package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.Line;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CityRepository {

    @Select("select * from t_city")
    List<City> findCities();

    @Select("SELECT * FROM t_city WHERE id = #{id}")
    City findCity(@Param("id") Long id);

    @Select("select * from t_district where city_id = #{cityId} order by id")
    List<District> findOrderedDistrictsByCityId(Long cityId);

    @Select("SELECT sb.* FROM t_district_rel dr LEFT JOIN t_sub_district sb on dr.sub_district_id = sb.id WHERE dr.district_id =#{districtId} ORDER BY sb.id")
    List<SubDistrict> findOrderedSubDistrictsByDistrictId(Long districtId);

    @Select("SELECT * FROM t_district WHERE id = #{id}")
    District findDistrict(@Param("id") Long id);

    @Select("SELECT * FROM t_sub_district WHERE id = #{id}")
    SubDistrict findSubDistrict(@Param("id") Long id);

    @Select("SELECT d.* FROM t_district_rel dr  LEFT JOIN t_district d on dr.district_id = d.id\n" +
            "WHERE dr.is_primary = 'Y' AND dr.sub_district_id = #{id}")
    District findPrimaryDistrict(@Param("id") Long subDistrictId);

    @Select("SELECT l.* FROM t_line_station_rel lsr" +
            " LEFT JOIN t_line l ON lsr.line_id = l.id" +
            " WHERE lsr.station_id = #{stationId} ORDER BY lsr.id LIMIT 1;")
    Line findPrimaryLine(@Param("stationId") Long stationId);
}
