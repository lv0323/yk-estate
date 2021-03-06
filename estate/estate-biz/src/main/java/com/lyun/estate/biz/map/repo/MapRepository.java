package com.lyun.estate.biz.map.repo;

import com.lyun.estate.biz.map.repo.provider.MapSqlProvider;
import com.lyun.estate.biz.map.domain.EstateMapResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-10.
 */
public interface MapRepository {


    @Select("select district.id,district.name,district.sell_avg_price as avg_price,district.longitude,district.latitude,'DISTRICT' as domain_type,district.sell_house_count as building_counts" +
            " from t_district district where district.city_id = #{cityId}")
    List<EstateMapResource> findAllSellDistrictListByMap(@Param("cityId") int cityId);

    @Select("select district.id,district.name,district.sell_avg_price as avg_price,district.longitude,district.latitude,'DISTRICT' as domain_type,district.rent_house_count as building_counts" +
            " from t_district district where district.city_id = #{cityId}")
    List<EstateMapResource> findAllRentDistrictListByMap(@Param("cityId") int cityId);

    @Select("select subDistrict.id,subDistrict.name,subDistrict.sell_avg_price as avg_price,subDistrict.longitude,subDistrict.latitude,'SUB_DISTRICT' as domain_type,subDistrict.sell_house_count as building_counts" +
            " from t_sub_district subDistrict" +
            " join t_district_rel rel on rel.sub_district_id = subDistrict.id and rel.is_primary='Y'" +
            " join t_district district on district.id = rel.district_id" +
            " where district.city_id = #{cityId}")
    List<EstateMapResource> findAllSellSubDistrictListByMap(@Param("cityId") int cityId);

    @Select("select subDistrict.id,subDistrict.name,subDistrict.sell_avg_price as avg_price,subDistrict.longitude,subDistrict.latitude,'SUB_DISTRICT' as domain_type,subDistrict.rent_house_count as building_counts" +
            " from t_sub_district subDistrict" +
            " join t_district_rel rel on rel.sub_district_id = subDistrict.id and rel.is_primary='Y'" +
            " join t_district district on district.id = rel.district_id" +
            " where district.city_id = #{cityId}")
    List<EstateMapResource> findAllRentSubDistrictListByMap(@Param("cityId") int cityId);

    @SelectProvider(type = MapSqlProvider.class, method = "findSellCommunityListByMap")
    List<EstateMapResource> findSellCommunityListByMap(@Param("minLongitude") BigDecimal minLongitude,
                                                       @Param("maxLongitude") BigDecimal maxLongitude,
                                                       @Param("minLatitude") BigDecimal minLatitude,
                                                       @Param("maxLatitude") BigDecimal maxLatitude,
                                                       @Param("cityId") Integer cityId);

    @SelectProvider(type = MapSqlProvider.class, method = "findRentCommunityListByMap")
    List<EstateMapResource> findRentCommunityListByMap(@Param("minLongitude") BigDecimal minLongitude,
                                                       @Param("maxLongitude") BigDecimal maxLongitude,
                                                       @Param("minLatitude") BigDecimal minLatitude,
                                                       @Param("maxLatitude") BigDecimal maxLatitude,
                                                       @Param("cityId") Integer cityId);
}
