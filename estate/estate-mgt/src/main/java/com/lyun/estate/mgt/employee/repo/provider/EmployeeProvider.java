package com.lyun.estate.mgt.employee.repo.provider;

import com.lyun.estate.core.repo.SQL;
import com.lyun.estate.mgt.employee.entity.Employee;

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
                .VALUES_IF("is_agent", "#{isAgent}", employee.getIsAgent() != null).toString();
    }
}
