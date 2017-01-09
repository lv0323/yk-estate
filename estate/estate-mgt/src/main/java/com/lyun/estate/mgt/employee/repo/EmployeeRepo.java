package com.lyun.estate.mgt.employee.repo;

import com.lyun.estate.mgt.employee.entity.Employee;
import com.lyun.estate.mgt.employee.repo.provider.EmployeeProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeRepo {

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = EmployeeProvider.class, method = "insert")
    int insert(Employee employee);

    @Delete("delete from t_employee where id = #{id}")
    int deleteById(Long id);

    @Update("update t_employee set " +
            "department_id = #{departmentId}, position_id = #{positionId}, " +
            "mobile = #{mobile}, name = #{name}, gender = #{gender}, " +
            "idcard_number = #{idcardNumber}, wechat = #{wechat}, status = #{status}, " +
            "update_time = CURRENT_TIMESTAMP where id = #{id}")
    int update(Employee employee);

    @Select("select * from t_employee where company_id = #{companyId}")
    @Results({
            @Result(column = "department_id", property = "departmentId", javaType = Long.class),
            @Result(column = "department_id", property = "department", one = @One(select = "com.lyun.estate.mgt.department.repo.DepartmentRepo.selectById")),
            @Result(column = "position_id", property = "positionId", javaType = Long.class),
            @Result(column = "position_id", property = "position", one = @One(select = "com.lyun.estate.mgt.position.repo.PositionRepo.selectById"))
    })
    List<Employee> selectByCompanyId(Long companyId);

    @Select("select * from t_employee where id = #{id}")
    Employee selectById(Long id);
}
