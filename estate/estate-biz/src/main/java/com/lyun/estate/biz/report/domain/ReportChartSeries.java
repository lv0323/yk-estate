package com.lyun.estate.biz.report.domain;

import java.util.List;

/**
 * Created by jesse on 2017/3/15.
 */
public class ReportChartSeries {
    private String name;
    private String stack;
    private List data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
