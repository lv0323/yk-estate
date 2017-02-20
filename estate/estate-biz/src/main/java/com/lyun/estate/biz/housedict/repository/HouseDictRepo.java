package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Repository
public interface HouseDictRepo {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_building (community_id, name, floors, stairs, houses, description, create_by_id, version)" +
            " VALUES (#{communityId}, #{name}, #{floors}, #{stairs}, #{houses}, #{description}, #{createById}, 1)")
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

    @Select("SELECT * FROM t_building where community_id =#{communityId} AND is_deleted = false")
    List<Building> findBuildingByCommunityId(Long communityId);
}
