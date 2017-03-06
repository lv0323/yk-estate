package com.lyun.estate.biz.houselicence.entity;

import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.houselicence.def.LicenceStatus;

import java.util.Date;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicence {
    private Long id;
    private BizType bizType;
    private LicenceStatus status;
    private Long communityId;
    private Long buildingId;
    private Long buildingUnitId;
    private String houseNo;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public HouseLicence setId(Long id) {
        this.id = id;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public HouseLicence setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public LicenceStatus getStatus() {
        return status;
    }

    public HouseLicence setStatus(LicenceStatus status) {
        this.status = status;
        return this;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public HouseLicence setCommunityId(Long communityId) {
        this.communityId = communityId;
        return this;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public HouseLicence setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public Long getBuildingUnitId() {
        return buildingUnitId;
    }

    public HouseLicence setBuildingUnitId(Long buildingUnitId) {
        this.buildingUnitId = buildingUnitId;
        return this;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public HouseLicence setHouseNo(String houseNo) {
        this.houseNo = houseNo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public HouseLicence setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public HouseLicence setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public HouseLicence setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    @Override
    public String toString() {
        return "HouseLicence{" +
                "id=" + id +
                ", bizType=" + bizType +
                ", status=" + status +
                ", communityId=" + communityId +
                ", buildingId=" + buildingId +
                ", buildingUnitId=" + buildingUnitId +
                ", houseNo='" + houseNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
