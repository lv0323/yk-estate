package com.lyun.estate.biz.report.domain;

import java.math.BigDecimal;

/**
 * Created by jesse on 2017/3/15.
 */
public class ReportChartResultData {
    private String xAxis;
    private BigDecimal seriesData;

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public BigDecimal getSeriesData() {
        return seriesData;
    }

    public void setSeriesData(BigDecimal seriesData) {
        this.seriesData = seriesData;
    }
}
