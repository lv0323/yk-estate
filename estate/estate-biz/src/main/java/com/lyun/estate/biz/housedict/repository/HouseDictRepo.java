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
    int saveBuilding(Building building);//插入楼栋信息

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_building_unit (building_id, unit_name, create_by_id)\n" +
            "VALUES (#{buildingId}, #{unitName},#{createById})")
    int saveBuildingUnit(BuildingUnit buildingUnit);//插入单元信息

    @Select("SELECT * FROM t_building_unit where id =#{id}")
    BuildingUnit findBuildingUnit(Long id);//根据单元id查找单元

    @Select("SELECT * FROM t_building where id =#{id}")
    Building findBuilding(Long id);// 查找楼栋信息根据id

    @Select("SELECT * FROM t_building where id =#{id} FOR UPDATE ")
    Building findBuildingForUpdate(Long id);// for update 一种行级锁，一旦用户对某个行施加了行级加锁别的会话不能修改，只有当前会话提交（回滚）后，或当前会话结束事，别的会话才可以修改；


    @Select("SELECT * FROM t_building_unit where building_id =#{buildingId} AND is_deleted = false")
    List<BuildingUnit> findBuildingUnitByBuildingId(Long buildingId);

    @Select("SELECT * FROM t_building where community_id =#{communityId} AND company_id =#{companyId} AND is_deleted = false ORDER BY id")
    List<Building> findBuildingByCommunityIdAndCompanyId(@Param("communityId") Long communityId,
                                                         @Param("companyId") Long companyId);// 查询楼栋 以小区id 公司id为条件

    @Select("SELECT xq.id as xiao_qu_id, c.name as xiao_qu_name FROM t_xiao_qu xq LEFT JOIN t_community c on xq.community_id =c.id\n" +
            "WHERE c.city_id = #{cityId} order by id asc limit 20")
    List<XiaoQuOption> findTop20XiaoQuOptions(Long cityId);// 从

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

    @Update("UPDATE t_building SET name = #{name}, floors = #{floors}, stairs = #{stairs}, houses = #{houses}, description = #{description}, " +
            "update_by_id = #{operatorId} ,update_time = now() WHERE id = #{buildingId}")
    int updateBuildingInfo(@Param("buildingId") Long buildingId,
                           @Param("name") String name,
                           @Param("floors") Integer floors,
                           @Param("stairs") Integer stairs,
                           @Param("houses") Integer houses,
                           @Param("description") String description,
                           @Param("operatorId") Long operatorId);


    @Update("UPDATE t_building_unit SET is_deleted = TRUE , update_by_id = #{operatorId}, update_time = now() WHERE building_id = #{buildingId}")
    int deleteOldBuildingUnit(@Param("buildingId") Long buildingId,
                              @Param("operatorId") Long operatorId);

    @Update("UPDATE t_building SET is_deleted = TRUE , update_by_id = #{operatorId}, update_time = now() WHERE id = #{buildingId}")
    int deleteBuilding(@Param("buildingId") Long buildingId,
                       @Param("operatorId") Long operatorId);
}
