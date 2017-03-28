package com.lyun.estate.biz.housedict.entity;

import com.lyun.estate.core.supports.types.YN;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-03-28.
 */
public class DistrictRel {
    private Long id;
    private Long districtId;
    private Long subDistrictId;
    private YN isPrimary;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public DistrictRel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public DistrictRel setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public DistrictRel setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public YN getIsPrimary() {
        return isPrimary;
    }

    public DistrictRel setIsPrimary(YN isPrimary) {
        this.isPrimary = isPrimary;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DistrictRel setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public DistrictRel setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
