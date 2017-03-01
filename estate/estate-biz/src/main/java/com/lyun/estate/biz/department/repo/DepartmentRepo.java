package com.lyun.estate.biz.department.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.department.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_department (parent_id,company_id,name,telephone,address, " +
            "city_id,district_id,sub_district_id,longitude,latitude) " +
            "values (#{parentId},#{companyId},#{name},#{telephone},#{address}, " +
            "#{cityId},#{districtId},#{subDistrictId},#{longitude},#{latitude})")
    int insert(Department department);

    @Delete("update t_department set is_deleted = true where id =#{id}")
    int deleteById(Long id);

    @Update("update t_department set name=#{name},telephone=#{telephone},address=#{address}, " +
            "city_id=#{cityId},district_id=#{districtId},sub_district_id=#{subDistrictId},longitude=#{longitude},latitude=#{latitude}, " +
            "update_time=CURRENT_TIMESTAMP where id=#{id} and is_deleted= false")
    int update(Department department);

    @Select("select * from t_department where id=#{id} and is_deleted = false")
    Department selectById(Long id);

    @Select("select * from t_department where id=#{id} and is_deleted = false for update")
    Department selectForUpdate(Long id);

    @Select("select * from t_department where company_id=#{companyId} and is_deleted = false order by id")
    PageList<Department> selectByCompanyId(Long companyId, PageBounds pageBounds);

    @Update("update t_department set parent_id = #{parentId} where id = #{id} ")
    int updateParent(@Param("id") Long id, @Param("parentId") Long parentId);

    @Select("select * from t_department where company_id=#{companyId} and is_deleted = false")
    List<Department> listAllByCompanyId(Long companyId);
}
