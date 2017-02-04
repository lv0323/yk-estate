package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.core.supports.types.YN;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-24.
 */
public class FangSelector {
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Long subDistrictId;
    private Long lineId;
    private Long stationId;
    private List<Integer> sCounts;
    private List<Orientation> orientations;
    private List<FloorType> floorTypes;
    private List<Decorate> decorates;
    private YN hasElevator;
    private List<StructureType> structureTypes;
    private List<Long> xiaoQuIds;
    private List<Long> excludeIds;
    private List<Showing> showings;
    private YN isOnly;
    private YN nearLine;
    private Integer overYears;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minYear;
    private Integer maxYear;
    private Integer minArea;
    private Integer maxArea;

    public Long getCityId() {
        return cityId;
    }

    public FangSelector setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangSelector setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public FangSelector setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public FangSelector setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Long getLineId() {
        return lineId;
    }

    public FangSelector setLineId(Long lineId) {
        this.lineId = lineId;
        return this;
    }

    public Long getStationId() {
        return stationId;
    }

    public FangSelector setStationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    public List<Integer> getsCounts() {
        return sCounts;
    }

    public FangSelector setsCounts(List<Integer> sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public List<Orientation> getOrientations() {
        return orientations;
    }

    public FangSelector setOrientations(List<Orientation> orientations) {
        this.orientations = orientations;
        return this;
    }

    public List<FloorType> getFloorTypes() {
        return floorTypes;
    }

    public FangSelector setFloorTypes(List<FloorType> floorTypes) {
        this.floorTypes = floorTypes;
        return this;
    }

    public List<Decorate> getDecorates() {
        return decorates;
    }

    public FangSelector setDecorates(List<Decorate> decorates) {
        this.decorates = decorates;
        return this;
    }

    public YN getHasElevator() {
        return hasElevator;
    }

    public FangSelector setHasElevator(YN hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public List<StructureType> getStructureTypes() {
        return structureTypes;
    }

    public FangSelector setStructureTypes(List<StructureType> structureTypes) {
        this.structureTypes = structureTypes;
        return this;
    }

    public List<Long> getXiaoQuIds() {
        return xiaoQuIds;
    }

    public FangSelector setXiaoQuIds(List<Long> xiaoQuIds) {
        this.xiaoQuIds = xiaoQuIds;
        return this;
    }

    public List<Long> getExcludeIds() {
        return excludeIds;
    }

    public FangSelector setExcludeIds(List<Long> excludeIds) {
        this.excludeIds = excludeIds;
        return this;
    }

    public List<Showing> getShowings() {
        return showings;
    }

    public FangSelector setShowings(List<Showing> showings) {
        this.showings = showings;
        return this;
    }

    public YN getIsOnly() {
        return isOnly;
    }

    public FangSelector setIsOnly(YN isOnly) {
        this.isOnly = isOnly;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public FangSelector setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public Integer getOverYears() {
        return overYears;
    }

    public FangSelector setOverYears(Integer overYears) {
        this.overYears = overYears;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public FangSelector setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public FangSelector setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Integer getMinYear() {
        return minYear;
    }

    public FangSelector setMinYear(Integer minYear) {
        this.minYear = minYear;
        return this;
    }

    public Integer getMaxYear() {
        return maxYear;
    }

    public FangSelector setMaxYear(Integer maxYear) {
        this.maxYear = maxYear;
        return this;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public FangSelector setMinArea(Integer minArea) {
        this.minArea = minArea;
        return this;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public FangSelector setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
        return this;
    }
}
