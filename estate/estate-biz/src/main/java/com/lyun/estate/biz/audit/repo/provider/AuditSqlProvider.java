package com.lyun.estate.biz.audit.repo.provider;

import com.lyun.estate.core.repo.SQL;

import java.util.Map;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class AuditSqlProvider {
    public String findBySubject(Map<String, Object> params) {
        return new SQL() {{
            SELECT("a.id, a.company_id, a.department_id, a.operator_id, a.subject, a.target_id, a.domain_type, a.content, a.create_time" +
                    ", d.name as department_name, e.name as operator_name")
                    .FROM("t_audit a")
                    .LEFT_OUTER_JOIN("t_department d on a.department_id = d.id ")
                    .LEFT_OUTER_JOIN("t_employee e on a.operator_id = e.id")
                    .WHERE("a.company_id =#{companyId}")
                    .WHERE("a.subject =#{subject}")
                    .WHERE_IF("a.create_time >= #{startTime} ", params.get("startTime") != null)
                    .WHERE_IF("a.create_time < #{endTime}", params.get("endTime") != null);
        }}.toString();
    }
}
