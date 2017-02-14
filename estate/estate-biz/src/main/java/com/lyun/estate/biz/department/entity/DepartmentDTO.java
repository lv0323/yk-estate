package com.lyun.estate.biz.department.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DepartmentDTO {

    private Long id;
    private Long parentId;
    private Long companyId;
    private String name;
    private String telephone;
    private String address;
    private Long cityId;
    private Long districtId;
    private Long subDistrictId;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;
    private Boolean isPrimary;
    private Boolean hasChild;
    private Integer level;


    public Long getId() {
        return id;
    }

    public DepartmentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public DepartmentDTO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public DepartmentDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public DepartmentDTO setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public DepartmentDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DepartmentDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public DepartmentDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public DepartmentDTO setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public DepartmentDTO setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public DepartmentDTO setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public DepartmentDTO setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public DepartmentDTO setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public DepartmentDTO setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Boolean getHasChild() {
        return hasChild;
    }

    public DepartmentDTO setHasChild(Boolean hasChild) {
        this.hasChild = hasChild;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public DepartmentDTO setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public DepartmentDTO setPrimary(Boolean primary) {
        isPrimary = primary;
        return this;
    }
}
