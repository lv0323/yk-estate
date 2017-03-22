package com.lyun.estate.biz.fang.domian;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.spec.fang.rest.def.IntPair;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.core.supports.types.YN;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-24.
 */
public class FangSelector extends TagSelector {
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Long subDistrictId;
    private Long lineId;
    private Long stationId;
    private List<Integer> sCounts;
    private List<Orientation> orientations;
    private List<FloorType> floorTypes;
    private YN hasElevator;
    private List<StructureType> structureTypes;
    private List<Long> xiaoQuIds;
    private List<Long> excludeIds;
    private HouseProcess process;
    private Integer minPrice;
    private Integer maxPrice;
    private List<IntPair> areas;
    private List<IntPair> years;
    private List<HouseType> houseTypes;
    private Long fangId;

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

    public HouseProcess getProcess() {
        return process;
    }

    public FangSelector setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public List<IntPair> getAreas() {
        return areas;
    }

    public FangSelector setAreas(List<IntPair> areas) {
        this.areas = areas;
        return this;
    }

    public List<IntPair> getYears() {
        return years;
    }

    public FangSelector setYears(List<IntPair> years) {
        this.years = years;
        return this;
    }

    public List<HouseType> getHouseTypes() {
        return houseTypes;
    }

    public FangSelector setHouseTypes(List<HouseType> houseTypes) {
        this.houseTypes = houseTypes;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangSelector setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }
}
