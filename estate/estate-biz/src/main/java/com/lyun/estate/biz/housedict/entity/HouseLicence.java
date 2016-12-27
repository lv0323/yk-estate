package com.lyun.estate.biz.housedict.entity;

import com.lyun.estate.biz.housedict.def.LicenceStatus;
import com.lyun.estate.biz.housedict.def.LicenceType;
import com.lyun.estate.core.supports.types.YN;

import java.util.Date;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicence {
    private Long id;
    private LicenceType type;
    private LicenceStatus status;
    private Long communityId;
    private Long buildingId;
    private Long buildingUnitId;
    private Long houseNumber;
    private Date createTime;
    private Date updateTime;
    private YN isDeleted;

    public Long getId() {
        return id;
    }

    public HouseLicence setId(Long id) {
        this.id = id;
        return this;
    }

    public LicenceType getType() {
        return type;
    }

    public HouseLicence setType(LicenceType type) {
        this.type = type;
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

    public Long getHouseNumber() {
        return houseNumber;
    }

    public HouseLicence setHouseNumber(Long houseNumber) {
        this.houseNumber = houseNumber;
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

    public YN getIsDeleted() {
        return isDeleted;
    }

    public HouseLicence setIsDeleted(YN isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    @Override
    public String toString() {
        return "HouseLicence{" +
                "id=" + id +
                ", type=" + type +
                ", status=" + status +
                ", communityId=" + communityId +
                ", buildingId=" + buildingId +
                ", buildingUnitId=" + buildingUnitId +
                ", houseNumber=" + houseNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
