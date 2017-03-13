package com.lyun.estate.biz.houselicence.repository;

import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.entity.HouseLicenceDTO;
import com.lyun.estate.biz.support.def.BizType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-20.
 */
@Repository
public interface HouseLicenceRepo {

    @Options(useGeneratedKeys = true)
    @Insert("INSERT INTO t_house_licence (community_id, building_id, building_unit_id, house_no, biz_type, status) VALUES\n" +
            "  (#{communityId},#{buildingId},#{buildingUnitId},#{houseNo},#{bizType},#{status});")
    int save(HouseLicence houseLicence);

    @Select("select * from t_house_licence where id = #{id}")
    HouseLicence findOne(Long id);

    @Select("SELECT * FROM t_house_licence WHERE community_id = #{communityId} AND building_id = #{buildingId}\n" +
            "AND  building_unit_id = #{buildingUnitId} AND house_no=#{houseNo} AND biz_type =#{bizType} AND status='ACTIVE' AND is_deleted =FALSE LIMIT 1")
    HouseLicence findActive(@Param("communityId") Long communityId, @Param("bizType") BizType bizType,
                            @Param("buildingId") Long buildingId, @Param("buildingUnitId") Long buildingUnitId,
                            @Param("houseNo") String houseNo);

    @Update("update t_house_licence set status = 'INVALID' where id = #{id} and is_deleted =FALSE")
    int invalid(Long id);

    @Update("update t_house_licence set status = 'ACTIVE' where id = #{id} and is_deleted =FALSE")
    int active(long id);

    @Select("SELECT hl.*, c.name AS xiao_qu_name, b.name AS building_name, bu.unit_name AS building_unit_name\n" +
            "FROM t_house_licence hl  LEFT JOIN t_community c ON hl.community_id = c.id  LEFT JOIN t_building b ON hl.building_id = b.id\n" +
            "  LEFT JOIN t_building_unit bu ON hl.building_unit_id = bu.id WHERE hl.id = #{id}")
    HouseLicenceDTO findDTOById(Long id);

}
