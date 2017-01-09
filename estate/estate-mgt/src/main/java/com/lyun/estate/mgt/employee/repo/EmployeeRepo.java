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
    List<Employee> selectByCompanyId(Long companyId);

    @Select("select * from t_employee where id = #{id}")
    Employee selectById(Long id);
}
