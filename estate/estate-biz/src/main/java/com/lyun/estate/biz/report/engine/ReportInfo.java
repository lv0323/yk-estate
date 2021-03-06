package com.lyun.estate.biz.report.engine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jesse on 2017/1/25.
 */
public class ReportInfo {
    private String id;
    private String region;
    private String sql;
    private List<ReportHeader> headerList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ReportHeader> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<ReportHeader> headerList) {
        this.headerList = headerList;
    }
}
