package com.lyun.estate.biz.fangcollect.domain;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.support.def.BizType;

/**
 * Created by robin on 17/5/5.
 */
public class FangPoolSelector {
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Integer sCounts;
    private FloorType floorTypes;
    private Integer minPrice;
    private Integer maxPrice;
    private HouseType houseType;

    public Long getCityId() {
        return cityId;
    }

    public FangPoolSelector setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public FangPoolSelector setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public FangPoolSelector setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public FangPoolSelector setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public FloorType getFloorTypes() {
        return floorTypes;
    }

    public FangPoolSelector setFloorTypes(FloorType floorTypes) {
        this.floorTypes = floorTypes;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public FangPoolSelector setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public FangPoolSelector setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public FangPoolSelector setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }
}
