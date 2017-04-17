package com.lyun.estate.biz.spec.xiaoqu.mgt.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtXiaoQuFilter {
    private Long cityId;
    private Long districtId;
    private Long subDistrictId;
    private Long xiaoQuId;

    public Long getCityId() {
        return cityId;
    }

    public MgtXiaoQuFilter setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtXiaoQuFilter setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtXiaoQuFilter setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Long getXiaoQuId() {
        return xiaoQuId;
    }

    public MgtXiaoQuFilter setXiaoQuId(Long xiaoQuId) {
        this.xiaoQuId = xiaoQuId;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cityId", cityId)
                .add("districtId", districtId)
                .add("subDistrictId", subDistrictId)
                .add("xiaoQuId", xiaoQuId)
                .toString();
    }
}
