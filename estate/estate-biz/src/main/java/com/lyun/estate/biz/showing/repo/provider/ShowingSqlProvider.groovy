package com.lyun.estate.biz.showing.repo.provider

import com.google.common.base.Joiner
import com.lyun.estate.biz.showing.entity.ShowingSelector
import com.lyun.estate.core.repo.SQL

import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement
import static java.util.Objects.nonNull

/**
 * Created by Jeffrey on 2017-03-07.
 */
class ShowingSqlProvider {
    String list(ShowingSelector selector) {
        return new SQL() {
            {
                SELECT("s.*,d.name AS department_name, e.name AS employee_name")
                FROM(" t_showing s")
                LEFT_OUTER_JOIN("t_department d on s.department_id = d.id")
                LEFT_OUTER_JOIN("t_employee e on s.employee_id = e.id")
                WHERE_IF("s.fang_id = #{fangId}", nonNull(selector.getFangId()))
                WHERE_IF("s.process = #{process}", nonNull(selector.getProcess()))
                WHERE_IF("s.create_time >= #{minCreateTime}", nonNull(selector.getMinCreateTime()))
                WHERE_IF("s.create_time < #{maxCreateTime}", nonNull(selector.getMaxCreateTime()))
                WHERE_IF("s.company_id = #{companyId}", nonNull(selector.getCompanyId()))
                if (hasNotNullElement(selector.getDepartmentIds())) {
                    WHERE("s.department_id IN (" + Joiner.on(",").
                            skipNulls().
                            join(selector.getDepartmentIds()) + ")")
                }
                WHERE_IF("s.employee_id = #{employeeId}", nonNull(selector.getEmployeeId()))
                ORDER_BY("s.id desc")
            }
        }.toString()

    }
}
