package com.lyun.estate.biz.spec.xiaoqu.mgt.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtXiaoQuSummary {
    private Long id;
    private Long cityId;
    private String name;
    private Long districtId;
    private Long subDistrictId;
    private String district;
    private String subDistrict;
    private Integer buildedYear;
    private Integer structureType;
    private String structureStr;
    private String address;
    private String imageURI;

    public Long getId() {
        return id;
    }

    public MgtXiaoQuSummary setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public MgtXiaoQuSummary setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public MgtXiaoQuSummary setName(String name) {
        this.name = name;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtXiaoQuSummary setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtXiaoQuSummary setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public MgtXiaoQuSummary setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public MgtXiaoQuSummary setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public MgtXiaoQuSummary setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public Integer getStructureType() {
        return structureType;
    }

    public MgtXiaoQuSummary setStructureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    public String getStructureStr() {
        return structureStr;
    }

    public MgtXiaoQuSummary setStructureStr(String structureStr) {
        this.structureStr = structureStr;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MgtXiaoQuSummary setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public MgtXiaoQuSummary setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("cityId", cityId)
                .add("name", name)
                .add("districtId", districtId)
                .add("subDistrictId", subDistrictId)
                .add("district", district)
                .add("subDistrict", subDistrict)
                .add("buildedYear", buildedYear)
                .add("structureType", structureType)
                .add("structureStr", structureStr)
                .add("address", address)
                .add("imageURI", imageURI)
                .toString();
    }
}
