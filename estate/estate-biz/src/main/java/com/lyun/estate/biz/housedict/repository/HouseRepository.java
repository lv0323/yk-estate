package com.lyun.estate.biz.housedict.repository;

import com.lyun.estate.biz.housedict.entity.City;
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

    @Select("SELECT * FROM t_city WHERE id = #{id}")
    City findCity(@Param("id") Long id);


    @Select("SELECT d.* FROM t_district_rel dr  LEFT JOIN t_district d on dr.district_id = d.id\n" +
            "WHERE dr.is_primary = 'Y' AND dr.sub_district_id = #{id}")
    District findPrimaryDistrict(@Param("id") Long subDistrictId);
}
