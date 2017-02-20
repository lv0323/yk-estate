package com.lyun.estate.biz.housedict.entity;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-20.
 */
public class BuildingUnit {
    private Long id;
    private Long buildingId;
    private String unitName;
    private Long createById;
    private Date createTime;
    private Long updateById;
    private Date updateTime;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public BuildingUnit setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public BuildingUnit setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }

    public String getUnitName() {
        return unitName;
    }

    public BuildingUnit setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public Long getCreateById() {
        return createById;
    }

    public BuildingUnit setCreateById(Long createById) {
        this.createById = createById;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BuildingUnit setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public BuildingUnit setUpdateById(Long updateById) {
        this.updateById = updateById;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public BuildingUnit setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public BuildingUnit setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }
}
