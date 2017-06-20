package com.lyun.estate.biz.approval.repo.provider;

import com.lyun.estate.core.repo.SQL;

import java.util.Map;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-06-19.
 */
public class ApprovalSqlProvider {

    public String list(Map<String, Object> params) {
        return new SQL().SELECT("  a.*," +
                "  e.name AS apply_name," +
                "  d.id   AS apply_dept_id," +
                "  d.name AS apply_dept_name," +
                "  c.id   AS apply_company_id," +
                "  c.abbr AS apply_company_short_name")
                .FROM("t_approval a")
                .LEFT_OUTER_JOIN("t_employee e ON a.apply_id = e.id")
                .LEFT_OUTER_JOIN(" t_department d ON e.department_id = d.id")
                .LEFT_OUTER_JOIN("t_company c ON e.company_id = c.id")
                .WHERE_IF("a.type = #{type}", Objects.nonNull(params.get("type")))
                .WHERE_IF("a.status = #{status}", Objects.nonNull(params.get("status")))
                .WHERE_IF("a.apply_id = #{applyId}", Objects.nonNull(params.get("applyId")))
                .WHERE_IF("e.company_id = #{applyCompanyId}", Objects.nonNull(params.get("applyCompanyId")))
                .WHERE_IF("e.department_id = #{applyDeptId}", Objects.nonNull(params.get("applyDeptId")))
                .WHERE_IF("a.create_time >= #{startTime}", Objects.nonNull(params.get("startTime")))
                .WHERE_IF("a.create_time < #{endTime}", Objects.nonNull(params.get("endTime")))
                .ORDER_BY("a.create_time DESC")
                .toString();
    }
}
