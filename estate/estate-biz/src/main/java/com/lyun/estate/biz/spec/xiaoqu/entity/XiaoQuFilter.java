package com.lyun.estate.biz.spec.xiaoqu.entity;

import com.lyun.estate.biz.fang.def.StructureType;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuFilter {
    private Long cityId;
    private Long districtId;
    private Long subDistrictId;
    private Long lineId;
    private Long stationId;
    private StructureType structureType;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private String keyword;

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuFilter setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public XiaoQuFilter setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public XiaoQuFilter setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public XiaoQuFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public XiaoQuFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public XiaoQuFilter setStructureType(StructureType structureType) {
        this.structureType = structureType;
        return this;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public XiaoQuFilter setMinYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public XiaoQuFilter setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public XiaoQuFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Long getLineId() {
        return lineId;
    }

    public XiaoQuFilter setLineId(Long lineId) {
        this.lineId = lineId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public XiaoQuFilter setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoQuFilter{" +
                "cityId=" + cityId +
                ", districtId=" + districtId +
                ", subDistrictId=" + subDistrictId +
                ", lineId=" + lineId +
                ", stationId=" + stationId +
                ", structureType=" + structureType +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minYear=" + minYear +
                ", maxYear=" + maxYear +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
