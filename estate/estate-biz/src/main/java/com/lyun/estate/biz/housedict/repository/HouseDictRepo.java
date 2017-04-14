package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.domain.DomainHouseCount;
import com.lyun.estate.biz.housedict.domain.XiaoQuOption;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.entity.DistrictRel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Repository
public interface HouseDictRepo {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_building (community_id, company_id, name, floors, stairs, houses, description, create_by_id, version)" +
            " VALUES (#{communityId}, #{companyId}, #{name}, #{floors}, #{stairs}, #{houses}, #{description}, #{createById}, 1)")
    int saveBuilding(Building building);

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_building_unit (building_id, unit_name, create_by_id)\n" +
            "VALUES (#{buildingId}, #{unitName},#{createById})")
    int saveBuildingUnit(BuildingUnit buildingUnit);

    @Select("SELECT * FROM t_building_unit where id =#{id}")
    BuildingUnit findBuildingUnit(Long id);

    @Select("SELECT * FROM t_building where id =#{id}")
    Building findBuilding(Long id);

    @Select("SELECT * FROM t_building_unit where building_id =#{buildingId} AND is_deleted = false")
    List<BuildingUnit> findBuildingUnitByBuildingId(Long buildingId);

    @Select("SELECT * FROM t_building where community_id =#{communityId} AND company_id =#{companyId} AND is_deleted = false")
    List<Building> findBuildingByCommunityIdAndCompanyId(@Param("communityId") Long communityId,
                                                         @Param("companyId") Long companyId);

    @Select("SELECT xq.id as xiao_qu_id, c.name as xiao_qu_name FROM t_xiao_qu xq LEFT JOIN t_community c on xq.community_id =c.id\n" +
            "WHERE c.city_id = #{cityId} order by id asc limit 20")
    List<XiaoQuOption> findTop20XiaoQuOptions(Long cityId);

    @Select("SELECT   c.sub_district_id as domain_id, sum(xq.sell_house_count) as sell_house_count, sum(xq.rent_house_count) as rent_house_count " +
            " FROM t_xiao_qu xq LEFT JOIN t_community c ON xq.community_id = c.id WHERE C.sub_district_id = #{subDistrictId} GROUP BY c.sub_district_id;")
    DomainHouseCount houseCountForSubDistrict(Long subDistrictId);

    @Update("UPDATE t_sub_district SET sell_house_count = #{sellHouseCount},rent_house_count = #{rentHouseCount} WHERE id = #{domainId}")
    int updateSubDistrictHouseCount(DomainHouseCount subDistrictHouseCount);

    @Select("SELECT * FROM t_district_rel WHERE sub_district_id = #{subDistrictId} ")
    List<DistrictRel> findDistrictRel(Long subDistrictId);

    @Select("SELECT dr.district_id as domain_id, sum(sd.sell_house_count) AS sell_house_count,  sum(sd.rent_house_count) AS rent_house_count\n" +
            "FROM t_district_rel dr LEFT JOIN t_sub_district sd ON dr.sub_district_id = sd.id\n" +
            "WHERE dr.district_id = #{districtId} GROUP BY dr.district_id;")
    DomainHouseCount houseCountForDistricts(Long districtId);

    @Update("UPDATE t_district SET sell_house_count = #{sellHouseCount},rent_house_count = #{rentHouseCount} WHERE id = #{domainId}")
    int updateDistrictHouseCount(DomainHouseCount districtHouseCount);
}
