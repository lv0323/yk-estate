package com.lyun.estate.biz.fang.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.fang.domian.FangFollowSelector;
import com.lyun.estate.core.repo.SQL;

import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement;
import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-03-02.
 */
public class FangFollowSqlProvider {
    public String listBySelector(FangFollowSelector selector) {
        return new SQL() {{
            SELECT("ff.*,d.name AS department_name, e.name AS employee_name, e.avatar_id")
                    .FROM(" t_fang_follow ff")
                    .LEFT_OUTER_JOIN("t_fang_info_owner fio ON ff.fang_id = fio.fang_id AND fio.is_deleted = FALSE")
                    .LEFT_OUTER_JOIN("t_department d on ff.department_id = d.id")
                    .LEFT_OUTER_JOIN("t_employee e on ff.employee_id = e.id")
                    .WHERE_IF("ff.fang_id = #{fangId}", nonNull(selector.getFangId()))
                    .WHERE_IF("ff.follow_type = #{followType}", nonNull(selector.getFollowType()))
                    .WHERE_IF("ff.create_time >= #{minFollowTime}", nonNull(selector.getMinFollowTime()))
                    .WHERE_IF("ff.create_time < #{maxFollowTime}", nonNull(selector.getMaxFollowTime()))
                    .WHERE_IF("ff.company_id = #{companyId}", nonNull(selector.getCompanyId()));
            if (hasNotNullElement(selector.getDepartmentIds())) {
                WHERE("ff.department_id IN (" + Joiner.on(",").skipNulls().join(selector.getDepartmentIds()) + ")");
            }
            WHERE_IF("ff.employee_id = #{employeeId}", nonNull(selector.getEmployeeId()));
            if (hasNotNullElement(selector.getIoDepartmentIds())) {
                WHERE("fio.department_id IN (" + Joiner.on(",").skipNulls().join(selector.getIoDepartmentIds()) + ")");
            }
            WHERE_IF("fio.employee_id = #{ioEmployeeId}", nonNull(selector.getIoEmployeeId()));
            ORDER_BY("ff.id desc");
        }}.toString();
    }
}
