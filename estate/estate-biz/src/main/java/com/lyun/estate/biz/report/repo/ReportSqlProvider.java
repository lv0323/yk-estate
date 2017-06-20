package com.lyun.estate.biz.report.repo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by jesse on 2017/3/13.
 */
public class ReportSqlProvider {
    private final static Logger logger = LoggerFactory.getLogger(ReportSqlProvider.class);
    public String getSql(Map<String, Object> params) {
        return params.get("sql").toString();
    }
}
