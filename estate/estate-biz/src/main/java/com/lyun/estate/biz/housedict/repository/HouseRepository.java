package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jeffrey on 2017-01-10.
 */
public interface HouseRepository {

    @Select("SELECT * FROM t_district WHERE id = #{id}")
    District findDistrict(@Param("id") Long id);

    @Select("SELECT * FROM t_sub_district WHERE id = #{id}")
    SubDistrict findSubDistrict(@Param("id") Long id);
}
