package com.lyun.estate.biz.xiaoqu.entity;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuSelector {
    private Long cityId;
    private Long districtId;
    private Long subDistrictId;
    private Long lineId;
    private Long stationId;
    private List<Long> xiaoQuIds;
    private Integer minPrice;
    private Integer maxPrice;
    private List<Integer> structureTypes;
    private Integer minYear;
    private Integer maxYear;
    private List<Long> excludeIds;

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuSelector setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public XiaoQuSelector setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public XiaoQuSelector setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public List<Long> getXiaoQuIds() {
        return xiaoQuIds;
    }

    public XiaoQuSelector setXiaoQuIds(List<Long> xiaoQuIds) {
        this.xiaoQuIds = xiaoQuIds;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public XiaoQuSelector setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public XiaoQuSelector setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }


    public Integer getMinYear() {
        return minYear;
    }

    public XiaoQuSelector setMinYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public XiaoQuSelector setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public List<Integer> getStructureTypes() {
        return structureTypes;
    }

    public XiaoQuSelector setStructureTypes(List<Integer> structureTypes) {
        this.structureTypes = structureTypes;
        return this;
    }

    public List<Long> getExcludeIds() {
        return excludeIds;
    }

    public XiaoQuSelector setExcludeIds(List<Long> excludeIds) {
        this.excludeIds = excludeIds;
        return this;
    }

    public Long getLineId() {
        return lineId;
    }

    public XiaoQuSelector setLineId(Long lineId) {
        this.lineId = lineId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public XiaoQuSelector setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoQuSelector{" +
                "cityId=" + cityId +
                ", districtId=" + districtId +
                ", subDistrictId=" + subDistrictId +
                ", lineId=" + lineId +
                ", stationId=" + stationId +
                ", xiaoQuIds=" + xiaoQuIds +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", structureTypes=" + structureTypes +
                ", minYear=" + minYear +
                ", maxYear=" + maxYear +
                ", excludeIds=" + excludeIds +
                '}';
    }
}

