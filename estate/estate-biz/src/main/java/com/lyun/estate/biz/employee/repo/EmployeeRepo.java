package com.lyun.estate.biz.employee.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.provider.EmployeeProvider;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

public interface EmployeeRepo {

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = EmployeeProvider.class, method = "insert")
    int insert(Employee employee);

    @Update("update t_employee set " +
            "department_id = #{departmentId}, position_id = #{positionId}, " +
            "mobile = #{mobile}, name = #{name}, gender = #{gender}, " +
            "idcard_number = #{idcardNumber}, wechat = #{wechat}, status = #{status}, " +
            "update_time = CURRENT_TIMESTAMP where id = #{id}")
    int update(Employee employee);

    @Update("update t_employee set avatar_id = #{avatarId} where id = #{id}")
    int avatar(@Param("id") Long id, @Param("avatarId") Long avatarId);

    @Update("update t_employee set quit = true where id = #{id}")
    int quit(Long id);

    @SelectProvider(type = EmployeeProvider.class, method = "selectByCompanyIdAndDeptIds")
    PageList<Employee> selectByCompanyIdAndDeptIds(@Param("companyId") Long companyId,
                                                   @Param("deptIds") Collection<Long> deptIds, PageBounds pageBounds);

    @Select("select * from t_employee where company_id = #{companyId} and is_boss = true")
    Employee selectBossByCompanyId(Long companyId);

    @Select("select * from t_employee where id = #{id}")
    Employee selectById(Long id);

    @Update("update t_employee set password = #{1}, salt = #{2} where mobile = #{0} and quit = false and password isnull and " +
            "(select secret_key from t_company where id = " +
            "(select company_id from t_employee where mobile = #{0} and quit = false)) = #{3}")
    int active(String mobile, String password, String salt, String secretKey);

    @Select("select * from t_employee where mobile = #{mobile} and quit = false")
    Employee selectByMobile(String mobile);

    @Select("select count(*) from t_employee where position_id = #{id} and quit = false")
    int countByPositionId(Long id);

    @Select("select * from t_employee where id =#{id}")
    Employee findOne(Long id);
}
