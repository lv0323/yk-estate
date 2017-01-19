package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.spec.xiaoqu.entity.EstateMapResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-10.
 */
public interface HouseRepository {

    @Select("SELECT * FROM t_district WHERE id = #{id}")
    District findDistrict(@Param("id") Long id);

    @Select("SELECT * FROM t_sub_district WHERE id = #{id}")
    SubDistrict findSubDistrict(@Param("id") Long id);

    @Select("SELECT * FROM t_city WHERE id = #{id}")
    City findCity(@Param("id") Long id);


    @Select("SELECT d.* FROM t_district_rel dr  LEFT JOIN t_district d on dr.district_id = d.id\n" +
            "WHERE dr.is_primary = 'Y' AND dr.sub_district_id = #{id}")
    District findPrimaryDistrict(@Param("id") Long subDistrictId);

    @Select("select district.id,district.name,district.sell_avg_price,district.longitude,district.latitude,'DISTRICT' as domain_type,district.sell_house_count as building_counts" +
            " from t_district district where district.city_id = #{cityId}")
    List<EstateMapResource> findAllSellDistrictListByMap(@Param("cityId") int cityId);

    @Select("select district.id,district.name,district.sell_avg_price,district.longitude,district.latitude,'DISTRICT' as domain_type,district.rent_house_count as building_counts" +
            " from t_district district where district.city_id = #{cityId}")
    List<EstateMapResource> findAllRentDistrictListByMap(@Param("cityId") int cityId);

    @Select("select subDistrict.id,subDistrict.name,subDistrict.sell_avg_price,subDistrict.longitude,subDistrict.latitude,'SUB_DISTRICT' as domain_type,subDistrict.sell_house_count as building_counts\n" +
            " from t_sub_district" +
            " join t_district_rel rel on rel.sub_district_id = subDistrict.id and rel.is_primary='Y'\n" +
            " join t_district district on district.id = rel.district_id\n" +
            " where district.city_id = #{cityId}")
    List<EstateMapResource> findAllSellSubDistrictListByMap(@Param("cityId") int cityId);

    @Select("select subDistrict.id,subDistrict.name,subDistrict.sell_avg_price,subDistrict.longitude,subDistrict.latitude,'SUB_DISTRICT' as domain_type,subDistrict.rent_house_count as building_counts\n" +
            " from t_sub_district" +
            " join t_district_rel rel on rel.sub_district_id = subDistrict.id and rel.is_primary='Y'\n" +
            " join t_district district on district.id = rel.district_id\n" +
            " where district.city_id = #{cityId}")
    List<EstateMapResource> findAllRentSubDistrictListByMap(@Param("cityId") int cityId);
}
