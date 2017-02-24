package com.lyun.estate.biz.spec.fang.mgt.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.spec.fang.rest.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.rest.def.HouseTypeFilter;
import com.lyun.estate.biz.spec.fang.rest.def.IntPair;
import com.lyun.estate.biz.spec.fang.rest.def.ShiCountsFilter;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtFangFilter {
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
    private String keyword;
    private List<IntPair> areas;
    private List<IntPair> years;
    private List<HouseTypeFilter> houseTypeFilters;

    public Long getCityId() {
        return cityId;
    }

    public MgtFangFilter setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public MgtFangFilter setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtFangFilter setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtFangFilter setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Long getLineId() {
        return lineId;
    }

    public MgtFangFilter setLineId(Long lineId) {
        this.lineId = lineId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public MgtFangFilter setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    public List<ShiCountsFilter> getShiCountsFilters() {
        return shiCountsFilters;
    }

    public MgtFangFilter setShiCountsFilters(
            List<ShiCountsFilter> shiCountsFilters) {
        this.shiCountsFilters = shiCountsFilters;
        return this;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }

    public MgtFangFilter setOrientations(List<Orientation> orientations) {
        this.orientations = orientations;
        return this;
    }

    public List<HouseTag> getHouseTags() {
        return houseTags;
    }

    public MgtFangFilter setHouseTags(List<HouseTag> houseTags) {
        this.houseTags = houseTags;
        return this;
    }

    public List<FloorType> getFloorTypes() {
        return floorTypes;
    }

    public MgtFangFilter setFloorTypes(List<FloorType> floorTypes) {
        this.floorTypes = floorTypes;
        return this;
    }

    public List<Decorate> getDecorates() {
        return decorates;
    }

    public MgtFangFilter setDecorates(List<Decorate> decorates) {
        this.decorates = decorates;
        return this;
    }

    public List<ElevatorFilter> getElevatorFilters() {
        return elevatorFilters;
    }

    public MgtFangFilter setElevatorFilters(List<ElevatorFilter> elevatorFilters) {
        this.elevatorFilters = elevatorFilters;
        return this;
    }

    public List<StructureType> getStructureTypes() {
        return structureTypes;
    }

    public MgtFangFilter setStructureTypes(List<StructureType> structureTypes) {
        this.structureTypes = structureTypes;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public MgtFangFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public MgtFangFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public String getKeyword() {
        return keyword;
    }

    public MgtFangFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public List<IntPair> getAreas() {
        return areas;
    }

    public MgtFangFilter setAreas(List<IntPair> areas) {
        this.areas = areas;
        return this;
    }

    public List<IntPair> getYears() {
        return years;
    }

    public MgtFangFilter setYears(List<IntPair> years) {
        this.years = years;
        return this;
    }

    public List<HouseTypeFilter> getHouseTypeFilters() {
        return houseTypeFilters;
    }

    public MgtFangFilter setHouseTypeFilters(
            List<HouseTypeFilter> houseTypeFilters) {
        this.houseTypeFilters = houseTypeFilters;
        return this;
    }
}
