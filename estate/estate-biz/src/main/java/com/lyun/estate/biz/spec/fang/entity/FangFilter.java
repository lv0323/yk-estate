package com.lyun.estate.biz.spec.fang.entity;

import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.spec.fang.def.*;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class FangFilter {
    private BizType bizType;
    private String district;
    private String subDistrict;
    private String line;
    private String stataion;
    private List<ShiCountsFilter> shiCountsFilters;
    private List<Orientation> orientations;
    private List<AreaFilter> areaFilters;
    private List<HouseTag> houseTags;
    private List<YearFilter> yearFilters;
    private List<FloorType> floorTypes;
    private List<Decorate> decorates;
    private List<ElevatorFilter> elevatorFilters;
    private StructureType structureType;
    private Integer minPrice;
    private Integer maxPrice;
    private String keyword;

    public BizType getBizType() {
        return bizType;
    }

    public FangFilter setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public FangFilter setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public FangFilter setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public String getLine() {
        return line;
    }

    public FangFilter setLine(String line) {
        this.line = line;
        return this;
    }

    public String getStataion() {
        return stataion;
    }

    public FangFilter setStataion(String stataion) {
        this.stataion = stataion;
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

    public List<AreaFilter> getAreaFilters() {
        return areaFilters;
    }

    public FangFilter setAreaFilters(List<AreaFilter> areaFilters) {
        this.areaFilters = areaFilters;
        return this;
    }

    public List<HouseTag> getHouseTags() {
        return houseTags;
    }

    public FangFilter setHouseTags(List<HouseTag> houseTags) {
        this.houseTags = houseTags;
        return this;
    }

    public List<YearFilter> getYearFilters() {
        return yearFilters;
    }

    public FangFilter setYearFilters(List<YearFilter> yearFilters) {
        this.yearFilters = yearFilters;
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

    public StructureType getStructureType() {
        return structureType;
    }

    public FangFilter setStructureType(StructureType structureType) {
        this.structureType = structureType;
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

    public String getKeyword() {
        return keyword;
    }

    public FangFilter setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }
}
