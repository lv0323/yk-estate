package com.lyun.estate.biz.employee.repo.provider;

import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.core.repo.SQL;

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
