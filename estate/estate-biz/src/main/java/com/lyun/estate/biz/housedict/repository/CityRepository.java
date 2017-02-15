package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CityRepository {

    @Select("select * from t_city")
    List<City> findCities();

    @Select("SELECT * FROM t_city WHERE id = #{id}")
    City findCity(@Param("id") Long id);

    @Select("select * from t_district where city_id = #{cityId} order by id")
    List<District> findOrderedDistricts(Long cityId);

    @Select("SELECT sb.* FROM t_district_rel dr LEFT JOIN t_sub_district sb on dr.sub_district_id = sb.id WHERE dr.district_id =#{districtId} ORDER BY sb.id")
    List<SubDistrict> findOrderedSubDistricts(Long districtId);

    @Select("SELECT * FROM t_district WHERE id = #{id}")
    District findDistrict(@Param("id") Long id);

    @Select("SELECT * FROM t_sub_district WHERE id = #{id}")
    SubDistrict findSubDistrict(@Param("id") Long id);

    @Select("SELECT d.* FROM t_district_rel dr  LEFT JOIN t_district d on dr.district_id = d.id " +
            " WHERE dr.is_primary = 'Y' AND dr.sub_district_id = #{id}")
    District findPrimaryDistrict(@Param("id") Long subDistrictId);

    @Select("SELECT l.* FROM t_line_station_rel lsr" +
            " LEFT JOIN t_line l ON lsr.line_id = l.id" +
            " WHERE lsr.station_id = #{stationId} ORDER BY lsr.id LIMIT 1;")
    Line findPrimaryLine(@Param("stationId") Long stationId);

    @Select("select * from t_line where city_id = #{cityId} order by id")
    List<Line> findOrderedLines(Long cityId);

    @Select("SELECT s.* FROM t_line_station_rel lsr LEFT JOIN t_station s on lsr.station_id = s.id WHERE lsr.line_id = #{lineId} ORDER BY s.id")
    List<Station> findOrderedStations(Long lineId);

    @Update("UPDATE T_CITY SET NAME_KW=#{keyword} WHERE id=#{id}")
    int updateCityKeyword(@Param("id") Long id, @Param("keyword") String keyword);

    @Update("UPDATE T_DISTRICT SET NAME_KW=#{keyword} WHERE id=#{id}")
    int updateDistrictKeyword(@Param("id") Long id, @Param("keyword") String keyword);

    @Update("UPDATE T_SUB_DISTRICT SET NAME_KW=#{keyword} WHERE id=#{id}")
    int updateSubDistrictKeyword(@Param("id") Long id, @Param("keyword") String keyword);
}
