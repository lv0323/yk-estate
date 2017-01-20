package com.lyun.estate.biz.department.repo;

import com.lyun.estate.biz.department.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_department (parent_id,company_id,name,short_name,telephone,address, " +
            "city_id,district_id,sub_district_id,longitude,latitude) " +
            "values (#{parentId},#{companyId},#{name},#{shortName},#{telephone},#{address}, " +
            "#{cityId},#{districtId},#{subDistrictId},#{longitude},#{latitude})")
    int insert(Department department);

    @Delete("delete from t_department where id = #{id} and parent_id notnull and (select count(*) from t_department where parent_id = #{id}) = 0")
    int deleteById(Long id);

    @Update("update t_department set parent_id=#{parentId},name=#{name},short_name=#{shortName},telephone=#{telephone},address=#{address}, " +
            "city_id=#{cityId},district_id=#{districtId},sub_district_id=#{subDistrictId},longitude=#{longitude},latitude=#{latitude}, " +
            "update_time=CURRENT_TIMESTAMP where id=#{id} and #{parentId} <> #{id}")
    int update(Department department);

    @Select("select * from t_department where id=#{id}")
    Department selectById(Long id);

    @Select("select * from t_department where company_id=#{companyId}")
    @Results({
            @Result(column = "city_id", property = "cityId", javaType = Long.class),
            @Result(column = "city_id", property = "city", one = @One(select = "com.lyun.estate.biz.housedict.repository.HouseRepository.findCity")),
            @Result(column = "district_id", property = "districtId", javaType = Long.class),
            @Result(column = "district_id", property = "district", one = @One(select = "com.lyun.estate.biz.housedict.repository.HouseRepository.findDistrict")),
            @Result(column = "sub_district_id", property = "subDistrictId", javaType = Long.class),
            @Result(column = "sub_district_id", property = "subDistrict", one = @One(select = "com.lyun.estate.biz.housedict.repository.HouseRepository.findSubDistrict"))
    })
    List<Department> selectByCompanyId(Long companyId);
}
