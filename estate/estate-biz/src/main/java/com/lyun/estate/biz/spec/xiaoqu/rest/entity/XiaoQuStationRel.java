package com.lyun.estate.biz.spec.xiaoqu.rest.entity;

/**
 * Created by Jeffrey on 2017-02-07.
 */
public class XiaoQuStationRel {
    private Long xiaoQuId;
    private Long stationId;
    private String stationName;
    private Integer distance;
    private String lineName;

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public XiaoQuStationRel setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public XiaoQuStationRel setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    public String getStationName() {
        return stationName;
    }

    public XiaoQuStationRel setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public Integer getDistance() {
        return distance;
    }

    public XiaoQuStationRel setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public String getLineName() {
        return lineName;
    }

    public XiaoQuStationRel setLineName(String lineName) {
        this.lineName = lineName;
        return this;
    }
}
