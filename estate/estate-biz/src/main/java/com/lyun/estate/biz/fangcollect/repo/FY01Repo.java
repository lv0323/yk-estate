package com.lyun.estate.biz.fangcollect.repo;

import com.lyun.estate.biz.fangcollect.entity.FY01Fang;
import com.lyun.estate.biz.fangcollect.entity.FangPoolDistrict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by robin on 17/5/5.
 */

@Repository
public interface FY01Repo {

    @Select("SELECT * FROM t_fang_o1fy WHERE map_process is NULL or map_process!='processed'")
    List<FY01Fang> selectUnProcessItems();

    @Update("UPDATE t_fang_o1fy SET map_process = 'processed' where third_party_id = #{thirdPartyId}")
    int updateProcess(String thirdPartId);

    @Select("SELECT * FROM t_fang_pool_district where name = #{name} AND city_id = #{cityId}")
    List<FangPoolDistrict> getDistrict(@Param("name")String name, @Param("cityId")Long cityId);

    @Select("SELECT * FROM t_fang_pool_district where city_id = #{cityId} And is_default = true")
    FangPoolDistrict getDefaultDistrict(Long cityId);

}
