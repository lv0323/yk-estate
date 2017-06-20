package com.lyun.estate.biz.report.domain;

import java.util.List;

public class ReportChartData {

    private List<String> legendList;
    private List<String> xAxis;
    private List<ReportChartSeries> seriesList;

    public List<String> getLegendList() {
        return legendList;
    }

    public void setLegendList(List<String> legendList) {
        this.legendList = legendList;
    }

    public List<ReportChartSeries> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<ReportChartSeries> seriesList) {
        this.seriesList = seriesList;
    }

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }
}
