package com.lyun.estate.biz.housedict.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-02-20.
 */
public class Building {
    private Long id;
    private Long communityId;
    private Long companyId;
    private String name;
    private Integer floors;
    private Integer stairs;
    private Integer houses;
    private String description;
    private Long createById;
    private Date createTime;
    private Long updateById;
    private Date updateTime;
    private Boolean isDeleted;
    private Integer version;
    private List<BuildingUnit> units;

    public Long getId() {
        return id;
    }

    public Building setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public Building setCommunityId(Long communityId) {
        this.communityId = communityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Building setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getFloors() {
        return floors;
    }

    public Building setFloors(Integer floors) {
        this.floors = floors;
        return this;
    }

    public Integer getStairs() {
        return stairs;
    }

    public Building setStairs(Integer stairs) {
        this.stairs = stairs;
        return this;
    }

    public Integer getHouses() {
        return houses;
    }

    public Building setHouses(Integer houses) {
        this.houses = houses;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Building setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getCreateById() {
        return createById;
    }

    public Building setCreateById(Long createById) {
        this.createById = createById;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Building setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getUpdateById() {
        return updateById;
    }

    public Building setUpdateById(Long updateById) {
        this.updateById = updateById;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Building setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Building setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Building setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public List<BuildingUnit> getUnits() {
        return units;
    }

    public Building setUnits(List<BuildingUnit> units) {
        this.units = units;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Building setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
}
