package com.lyun.estate.biz.department.repo;

import com.lyun.estate.biz.department.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentRepo {

    @Options(useGeneratedKeys = true)
    @Insert("insert into t_department (parent_id,company_id,name,short_name,telephone,address) values (#{parentId},#{companyId},#{name},#{shortName},#{telephone},#{address})")
    int insert(Department department);

    @Delete("delete from t_department where id = #{id} and parent_id notnull and (select count(*) from t_department where parent_id = #{id}) = 0")
    int deleteById(Long id);

    @Update("update t_department set parent_id=#{parentId},name=#{name},short_name=#{shortName},telephone=#{telephone},address=#{address},update_time=CURRENT_TIMESTAMP where id=#{id}")
    int update(Department department);

    @Select("select * from t_department where id=#{id}")
    Department selectById(Long id);

    @Select("select * from t_department where company_id=#{companyId}")
    List<Department> selectByCompanyId(Long companyId);
}
