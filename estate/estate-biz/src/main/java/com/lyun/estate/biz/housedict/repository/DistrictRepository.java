package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DistrictRepository {

    @Select("select * from t_city")
    List<City> findCities();

    @Select("select * from t_district where city_id = #{id}")
    List<District> findDistrictsByCityId(Long id);

    @Select("select * from t_sub_district where id in (select sub_district_id from t_district_rel where district_id = #{id})")
    List<SubDistrict> findSubDistrictsByDistrictId(Long id);
}
