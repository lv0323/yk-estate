package com.lyun.estate.biz.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;

public class Department {

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
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    private Boolean isDeleted;


    public Long getId() {
        return id;
    }

    public Department setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Department setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Department setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Department setName(String name) {
        this.name = name;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public Department setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Department setAddress(String address) {
        this.address = address;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Department setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Department setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Department setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public Department setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public Department setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Department setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public Department setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

/*<<<<<<< HEAD
    public Department setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
=======*/
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
