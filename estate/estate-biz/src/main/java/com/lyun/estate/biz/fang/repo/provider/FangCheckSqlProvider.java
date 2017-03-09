package com.lyun.estate.biz.fang.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.fang.domian.FangCheckSelector;
import com.lyun.estate.core.repo.SQL;

import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement;
import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-03-02.
 */
public class FangCheckSqlProvider {
    public String list(FangCheckSelector selector) {
        SQL sql = new SQL().SELECT("fc.*,d.name AS department_name, e.name AS employee_name")
                .FROM(" t_fang_check fc")
                .LEFT_OUTER_JOIN("t_department d on fc.department_id = d.id")
                .LEFT_OUTER_JOIN("t_employee e on fc.employee_id = e.id")
                .WHERE_IF("fc.fang_id = #{fangId}", nonNull(selector.getFangId()))
                .WHERE_IF("fc.create_time >= #{minCreateTime}", nonNull(selector.getMinCreateTime()))
                .WHERE_IF("fc.create_time < #{maxCreateTime}", nonNull(selector.getMaxCreateTime()))
                .WHERE_IF("fc.company_id = #{companyId}", nonNull(selector.getCompanyId()));
        if (hasNotNullElement(selector.getDepartmentIds())) {
            sql.WHERE("fc.department_id IN (" + Joiner.on(",").skipNulls().join(selector.getDepartmentIds()) + ")");
        }
        sql.WHERE_IF("fc.employee_id = #{employeeId}", nonNull(selector.getEmployeeId()));
        sql.ORDER_BY("fc.id desc");
        return sql.toString();
    }
}
