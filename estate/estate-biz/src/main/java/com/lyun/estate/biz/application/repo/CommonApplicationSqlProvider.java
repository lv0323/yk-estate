package com.lyun.estate.biz.application.repo;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.application.entity.CommonApplicationEntity;
import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommonApplicationSqlProvider {

    public String findApplications(Map<String, Object> params) {
        return new SQL() {{
            SELECT("*");
            FROM("T_COMMON_APPLICATION");
            WHERE("1=1");
            WHERE_IF("id = #{id}", !StringUtils.isEmpty(params.get("id")));

            if (params.get("status") != null) {
                List statusList = ((List<CommonApplicationEntity.Status>)params.get("status")).stream().map(status -> status.name()).collect(Collectors.toList());
                WHERE("STATUS IN (" + Joiner.on(",").skipNulls().join(statusList) + ")");
            }

            if (params.get("types") != null) {
                List types = ((List<CommonApplicationEntity.Status>)params.get("types")).stream().map(type -> type.name()).collect(Collectors.toList());
                WHERE("TYPES IN (" + Joiner.on(",").skipNulls().join(types) + ")");
            }

            WHERE_IF("APPLICANT_ID = #{applicantId}", params.get("applicantId") != null);
            WHERE_IF("CREATE_TIME >= #{startTime}", params.get("startTime") != null);
            WHERE_IF("CREATE_TIME < #{endTime}", params.get("endTime") != null);
            ORDER_BY("ID DESC");
        }}.toString();
    }
}
