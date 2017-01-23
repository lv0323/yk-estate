package com.lyun.estate.biz.houselicence.entity;

import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.houselicence.def.LicenceStatus;
import com.lyun.estate.core.supports.types.YN;

import java.util.Date;

/**
 * Created by Jeffrey on 2016-12-26.
 */
public class HouseLicence {
    private Long id;
    private BizType type;
    private LicenceStatus status;
    private Long communityId;
    private String building;
    private String buildingUnit;
    private String houseNo;
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

    public BizType getType() {
        return type;
    }

    public HouseLicence setType(BizType type) {
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

    public String getBuilding() {
        return building;
    }

    public HouseLicence setBuilding(String building) {
        this.building = building;
        return this;
    }

    public String getBuildingUnit() {
        return buildingUnit;
    }

    public HouseLicence setBuildingUnit(String buildingUnit) {
        this.buildingUnit = buildingUnit;
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
                ", building='" + building + '\'' +
                ", buildingUnit='" + buildingUnit + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
