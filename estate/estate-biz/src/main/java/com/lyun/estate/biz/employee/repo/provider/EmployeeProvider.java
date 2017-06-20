package com.lyun.estate.biz.employee.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.core.repo.SQL;
import com.lyun.estate.core.repo.SqlSupport;

import java.util.Collection;
import java.util.Map;

public class EmployeeProvider {

    public String insert(Employee employee) {
        return new SQL().INSERT_INTO("t_employee")
                .VALUES("company_id", "#{companyId}")
                .VALUES("department_id", "#{departmentId}")
                .VALUES("position_id", "#{positionId}")
                .VALUES("mobile", "#{mobile}")
                .VALUES("name", "#{name}")
                .VALUES("gender", "#{gender}")
                .VALUES("idcard_number", "#{idcardNumber}")
                .VALUES("wechat", "#{wechat}")
                .VALUES("status", "#{status}")
                .VALUES_IF("salt", "#{salt}", employee.getSalt() != null)
                .VALUES_IF("password", "#{password}", employee.getPassword() != null)
                .VALUES_IF("open_contact", "#{openContact}", employee.getOpenContact() != null)
                .VALUES_IF("is_boss", "#{isBoss}", employee.getBoss() != null)
                .VALUES_IF("is_agent", "#{isAgent}", employee.getAgent() != null).toString();
    }

    @SuppressWarnings("unchecked")
    public String selectByCompanyIdAndDeptIds(Map<String, Object> params) {
        Collection<Long> deptIds = (Collection<Long>) params.get("deptIds");
        SQL sql = new SQL()
                .SELECT("e.*, c.abbr as company_abbr, p.name as position_name, d.name as department_name")
                .FROM("t_employee e")
                .LEFT_OUTER_JOIN("t_company c on e.company_id = c.id")
                .LEFT_OUTER_JOIN("t_position p on e.position_id = p.id")
                .LEFT_OUTER_JOIN("t_department d on e.department_id = d.id");
        if (SqlSupport.hasNotNullElement(deptIds)) {
            sql.WHERE("e.department_id IN (" + Joiner.on(",").skipNulls().join(deptIds) + ")");
        }
        sql.WHERE("e.company_id = #{companyId} ")
                .WHERE("e.quit = FALSE")
                .ORDER_BY("e.id");
        return sql.toString();
    }

    public String selectByCompanyIdAndPositionId(Map<String, Object> params) {
        SQL sql = new SQL()
                .SELECT("e.*,c.abbr as company_abbr, p.name as position_name, d.name as department_name")
                .FROM("t_employee e")
                .LEFT_OUTER_JOIN("t_company c on e.company_id = c.id")
                .LEFT_OUTER_JOIN("t_position p on e.position_id = p.id")
                .LEFT_OUTER_JOIN("t_department d on e.department_id = d.id");
        sql.WHERE("e.position_id = #{positionId}");
        sql.WHERE("e.company_id = #{companyId} ")
                .WHERE("e.quit = FALSE")
                .ORDER_BY("e.id");
        return sql.toString();
    }
}
