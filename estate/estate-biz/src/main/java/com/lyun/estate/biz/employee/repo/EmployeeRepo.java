package com.lyun.estate.biz.employee.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.repo.provider.EmployeeProvider;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepo {

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = EmployeeProvider.class, method = "insert")
    int insert(Employee employee);

    @Update("update t_employee set " +
            "department_id = #{departmentId}, position_id = #{positionId}," +
            "name = #{name}, gender = #{gender}, open_contact = #{openContact}, " +
            "idcard_number = #{idcardNumber}, wechat = #{wechat}, status = #{status}, entry_date=#{entryDate}, " +
            "update_time = CURRENT_TIMESTAMP where id = #{id}")
    int update(Employee employee);

    @Update("update t_employee set password = #{password} where id = #{id}")
    int updatePassword(Employee employee);

    @Update("update t_employee set avatar_id = #{avatarId} where id = #{id}")
    int avatar(@Param("id") Long id, @Param("avatarId") Long avatarId);

    @Update("update t_employee set quit = true where id = #{id}")
    int quit(Long id);

    @SelectProvider(type = EmployeeProvider.class, method = "selectByCompanyIdAndDeptIds")
    PageList<Employee> selectByCompanyIdAndDeptIds(@Param("companyId") Long companyId,
                                                   @Param("deptIds") Collection<Long> deptIds, PageBounds pageBounds);

    @Select("SELECT e.*,p.name as position_name, d.city_id, d.name as department_name  FROM t_employee e\n" +
            " LEFT JOIN t_position p on e.position_id = p.id LEFT JOIN t_department d on e.department_id = d.id\n" +
            " WHERE e.id = #{id}")
    Employee selectById(Long id);

    @Update("update t_employee set password = #{1}, salt = #{2} where mobile = #{0} and quit = false and password isnull and " +
            "(select secret_key from t_company where id = " +
            "(select company_id from t_employee where mobile = #{0} and quit = false)) = #{3}")
    int active(String mobile, String password, String salt, String secretKey);

    @Select("SELECT e.*, c.ip_check as company_ip_check, p.name as position_name, d.city_id, d.name as department_name\n" +
            "FROM t_employee e LEFT JOIN t_position p on e.position_id = p.id LEFT JOIN t_department d on e.department_id = d.id\n" +
            "LEFT JOIN t_company c on e.company_id = c.id WHERE e.mobile = #{mobile} and e.quit = false")
    Employee selectByMobile(String mobile);

    @Update("update t_employee set open_contact = #{openContact}, wechat = #{wechat}, update_time = now() where id = #{id}")
    int updateContact(Employee employee);

    @Update("update t_employee set device_id = #{deviceId},  update_time = now() where id = #{id}")
    int updateDeviceId(@Param("id") Long id, @Param("deviceId") String deviceId);

    @Update("UPDATE t_employee SET follow_fang_id = #{fangId} WHERE id = #{id};")
    int updateFollowFangId(@Param("id") long id, @Param("fangId") long fangId);

    @Update("UPDATE t_employee SET follow_fang_id = NULL WHERE id = #{id} AND follow_fang_id = #{fangId};")
    int clearFollowFangId(@Param("id") long id, @Param("fangId") long fangId);

    @Update("UPDATE t_employee SET follow_fang_id = NULL WHERE follow_fang_id = #{fangId};")
    int clearAllFollowFangId(Long fangId);

    @SelectProvider(type = EmployeeProvider.class, method = "selectByCompanyIdAndPositionId")
    List<Employee> listByCompanyIdAndPositionId(@Param("companyId") long companyId,
                                                @Param("positionId") long positionId);
}
