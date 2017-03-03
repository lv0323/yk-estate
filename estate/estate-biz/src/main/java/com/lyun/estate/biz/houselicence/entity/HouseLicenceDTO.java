package com.lyun.estate.biz.houselicence.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.def.LicenceStatus;

import java.util.Date;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicenceDTO {
    private Long id;
    private BizType bizType;
    private LicenceStatus status;
    private String xiaoQuName;
    private Long buildingId;
    private String buildingName;
    private Long buildingUnitId;
    private String buildingUnitName;
    private String houseNo;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public HouseLicenceDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public HouseLicenceDTO setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public LicenceStatus getStatus() {
        return status;
    }

    public HouseLicenceDTO setStatus(LicenceStatus status) {
        this.status = status;
        return this;
    }

    public String getXiaoQuName() {
        return xiaoQuName;
    }

    public HouseLicenceDTO setXiaoQuName(String xiaoQuName) {
        this.xiaoQuName = xiaoQuName;
        return this;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public HouseLicenceDTO setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public HouseLicenceDTO setBuildingName(String buildingName) {
        this.buildingName = buildingName;
        return this;
    }

    public Long getBuildingUnitId() {
        return buildingUnitId;
    }

    public HouseLicenceDTO setBuildingUnitId(Long buildingUnitId) {
        this.buildingUnitId = buildingUnitId;
        return this;
    }

    public String getBuildingUnitName() {
        return buildingUnitName;
    }

    public HouseLicenceDTO setBuildingUnitName(String buildingUnitName) {
        this.buildingUnitName = buildingUnitName;
        return this;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public HouseLicenceDTO setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public HouseLicenceDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public HouseLicenceDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public HouseLicenceDTO setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("bizType", bizType)
                .add("status", status)
                .add("xiaoQuName", xiaoQuName)
                .add("buildingId", buildingId)
                .add("buildingName", buildingName)
                .add("buildingUnitId", buildingUnitId)
                .add("buildingUnitName", buildingUnitName)
                .add("houseNo", houseNo)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("isDeleted", isDeleted)
                .toString();
    }
}
