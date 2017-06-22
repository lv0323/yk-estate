package com.lyun.estate.biz.application;

import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;

public class CommonApplicationSqlProvider {

    public String findApplications(Map<String, Object> params) {
        return new SQL() {{
            SELECT("*");
            FROM("T_COMMON_APPLICATION");
            WHERE("1=1");
            WHERE_IF("TYPE = #{type}", !StringUtils.isEmpty(params.get("type")));
            WHERE_IF("id = #{id}", !StringUtils.isEmpty(params.get("id")));
            WHERE_IF("STATUS = #{status}", params.get("status") != null);
            WHERE_IF("APPLICANT_ID = #{applicantId}", params.get("applicantId") != null);
            WHERE_IF("CREATE_TIME >= #{startTime}", params.get("startTime") != null);
            WHERE_IF("CREATE_TIME < #{endTime}", params.get("endTime") != null);
            ORDER_BY("ID DESC");
        }}.toString();
    }
}
