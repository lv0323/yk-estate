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
                "  e.name AS applyName," +
                "  d.id   AS applyDeptId," +
                "  d.name AS applyDeptName," +
                "  c.id   AS applyCompanyId," +
                "  c.abbr AS applyCompanyShortName")
                .FROM("t_approval a")
                .LEFT_OUTER_JOIN("t_employee e ON a.apply_id = e.id")
                .LEFT_OUTER_JOIN(" t_department d ON e.department_id = d.id")
                .LEFT_OUTER_JOIN("t_company c ON e.company_id = c.id")
                .WHERE_IF("a.type = #{type}", Objects.nonNull(params.get("type")))
                .WHERE_IF("a.status = #{status}", Objects.nonNull(params.get("status")))
                .WHERE_IF("a.apply_id = #{applyId}", Objects.nonNull(params.get("applyId")))
                .WHERE_IF("e.company_id = #{applyCompanyId}", Objects.nonNull(params.get("applyCompanyId")))
                .WHERE_IF("e.department_id = #{applyDeptId}", Objects.nonNull(params.get("applyDeptId")))
                .toString();
    }
}
