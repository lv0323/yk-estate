package com.lyun.estate.biz.spec.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.spec.fang.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.def.ShiCountsFilter;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class FangFilter {
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Long subDistrictId;
    private Long lineId;
    private Long stationId;
    private List<ShiCountsFilter> shiCountsFilters;
    private List<Orientation> orientations;
    private List<HouseTag> houseTags;
    private List<FloorType> floorTypes;
    private List<Decorate> decorates;
    private List<ElevatorFilter> elevatorFilters;
    private List<StructureType> structureTypes;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private Integer minArea;
    private Integer maxArea;
    private String keyword;

    public Long getCityId() {
        return cityId;
    }

    public FangFilter setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangFilter setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public FangFilter setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public FangFilter setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Long getLineId() {
        return lineId;
    }

    public FangFilter setLineId(Long lineId) {
        this.lineId = lineId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public FangFilter setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    public List<ShiCountsFilter> getShiCountsFilters() {
        return shiCountsFilters;
    }

    public FangFilter setShiCountsFilters(
            List<ShiCountsFilter> shiCountsFilters) {
        this.shiCountsFilters = shiCountsFilters;
        return this;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }

    public FangFilter setOrientations(List<Orientation> orientations) {
        this.orientations = orientations;
        return this;
    }

    public List<HouseTag> getHouseTags() {
        return houseTags;
    }

    public FangFilter setHouseTags(List<HouseTag> houseTags) {
        this.houseTags = houseTags;
        return this;
    }

    public List<FloorType> getFloorTypes() {
        return floorTypes;
    }

    public FangFilter setFloorTypes(List<FloorType> floorTypes) {
        this.floorTypes = floorTypes;
        return this;
    }

    public List<Decorate> getDecorates() {
        return decorates;
    }

    public FangFilter setDecorates(List<Decorate> decorates) {
        this.decorates = decorates;
        return this;
    }

    public List<ElevatorFilter> getElevatorFilters() {
        return elevatorFilters;
    }

    public FangFilter setElevatorFilters(List<ElevatorFilter> elevatorFilters) {
        this.elevatorFilters = elevatorFilters;
        return this;
    }

    public List<StructureType> getStructureTypes() {
        return structureTypes;
    }

    public FangFilter setStructureTypes(List<StructureType> structureTypes) {
        this.structureTypes = structureTypes;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public FangFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public FangFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public FangFilter setMinYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public FangFilter setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public FangFilter setMinArea(Integer minArea) {
        this.minArea = minArea;
        return this;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public FangFilter setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public FangFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    @Override
    public String toString() {
        return "FangFilter{" +
                "cityId=" + cityId +
                ", bizType=" + bizType +
                ", districtId=" + districtId +
                ", subDistrictId=" + subDistrictId +
                ", lineId=" + lineId +
                ", stationId=" + stationId +
                ", shiCountsFilters=" + shiCountsFilters +
                ", orientations=" + orientations +
                ", houseTags=" + houseTags +
                ", floorTypes=" + floorTypes +
                ", decorates=" + decorates +
                ", elevatorFilters=" + elevatorFilters +
                ", structureTypes=" + structureTypes +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", minYear=" + minYear +
                ", maxYear=" + maxYear +
                ", minArea=" + minArea +
                ", maxArea=" + maxArea +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
