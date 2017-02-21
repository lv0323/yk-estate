package com.lyun.estate.biz.audit.repo.provider;

import com.lyun.estate.core.repo.SQL;

import java.util.Map;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class AuditSqlProvider {
    public String findBySubject(Map<String, Object> params) {
        return new SQL() {{

            SELECT("*")
                    .FROM("t_audit")
                    .WHERE("company_id =#{companyId}")
                    .WHERE("subject =#{subject}")
                    .WHERE_IF("create_time >= #{startTime} ", params.get("startTime") != null)
                    .WHERE_IF("create_time < #{endTime}", params.get("endTime") != null);
        }}.toString();
    }
}
