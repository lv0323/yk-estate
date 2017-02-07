package com.lyun.estate.biz.department.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Date;

public class Department {

    private Long id;
    private Long parentId;
    @NotNull
    private Long companyId;
    @NotNull
    private String name;
    private String shortName;
    private String telephone;
    private String address;
    @NotNull
    @JsonIgnore
    private Long cityId;
    @Null
    private City city;
    @NotNull
    @JsonIgnore
    private Long districtId;
    @Null
    private District district;
    @NotNull
    @JsonIgnore
    private Long subDistrictId;
    @Null
    private SubDistrict subDistrict;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;


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

    public String getShortName() {
        return shortName;
    }

    public Department setShortName(String shortName) {
        this.shortName = shortName;
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

    public City getCity() {
        return city;
    }

    public Department setCity(City city) {
        this.city = city;
        return this;
    }

    public District getDistrict() {
        return district;
    }

    public Department setDistrict(District district) {
        this.district = district;
        return this;
    }

    public SubDistrict getSubDistrict() {
        return subDistrict;
    }

    public Department setSubDistrict(SubDistrict subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }
}
