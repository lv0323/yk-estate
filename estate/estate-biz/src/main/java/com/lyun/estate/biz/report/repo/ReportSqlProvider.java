package com.lyun.estate.biz.report.repo;

import java.util.Map;

/**
 * Created by jesse on 2017/3/13.
 */
public class ReportSqlProvider {
    public String getSql(Map<String, Object> params) {
        return params.get("sql").toString();
    }
}
