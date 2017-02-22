package com.lyun.estate.biz.houselicence.repository;

import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
    HouseLicence findActive(Long communityId, BizType bizType, Long buildingId, Long buildingUnitId, String houseNo);

    @Update("update t_house_licence set status = 'INVALID' where id = #{id} and is_deleted =FALSE")
    HouseLicence invalid(Long id);
}
