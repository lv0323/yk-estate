package com.lyun.estate.biz.spec.xiaoqu.mgt.entity;

import com.google.common.base.MoreObjects;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtXiaoQuDetail {
    private Long id;
    private Long cityId;
    private String name;
    private String alias;
    private Long districtId;
    private Long subDistrictId;
    private String district;
    private String subDistrict;
    private String address;
    private String developers;
    private Integer structureType;
    private String structureStr;
    private Integer buildedYear;
    private String developYear;
    private String propertyCompany;
    private String propertyCompanyPhone;
    private String propertyFee;
    private String parkingSpace;
    private String parkingFee;
    private String parkingRate;
    private Integer buildings;
    private Integer houses;
    private String containerRate;
    private String greenRate;
    private String imageURI;
    private String longitude;
    private String latitude;

    public Long getId() {
        return id;
    }

    public MgtXiaoQuDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public MgtXiaoQuDetail setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getName() {
        return name;
    }

    public MgtXiaoQuDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public MgtXiaoQuDetail setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtXiaoQuDetail setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtXiaoQuDetail setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public MgtXiaoQuDetail setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public MgtXiaoQuDetail setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public MgtXiaoQuDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDevelopers() {
        return developers;
    }

    public MgtXiaoQuDetail setDevelopers(String developers) {
        this.developers = developers;
        return this;
    }

    public String getStructureStr() {
        return structureStr;
    }

    public MgtXiaoQuDetail setStructureStr(String structureStr) {
        this.structureStr = structureStr;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public MgtXiaoQuDetail setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public String getDevelopYear() {
        return developYear;
    }

    public MgtXiaoQuDetail setDevelopYear(String developYear) {
        this.developYear = developYear;
        return this;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public MgtXiaoQuDetail setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
        return this;
    }

    public String getPropertyCompanyPhone() {
        return propertyCompanyPhone;
    }

    public MgtXiaoQuDetail setPropertyCompanyPhone(String propertyCompanyPhone) {
        this.propertyCompanyPhone = propertyCompanyPhone;
        return this;
    }

    public String getPropertyFee() {
        return propertyFee;
    }

    public MgtXiaoQuDetail setPropertyFee(String propertyFee) {
        this.propertyFee = propertyFee;
        return this;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public MgtXiaoQuDetail setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public MgtXiaoQuDetail setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public MgtXiaoQuDetail setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
        return this;
    }

    public Integer getBuildings() {
        return buildings;
    }

    public MgtXiaoQuDetail setBuildings(Integer buildings) {
        this.buildings = buildings;
        return this;
    }

    public Integer getHouses() {
        return houses;
    }

    public MgtXiaoQuDetail setHouses(Integer houses) {
        this.houses = houses;
        return this;
    }

    public String getContainerRate() {
        return containerRate;
    }

    public MgtXiaoQuDetail setContainerRate(String containerRate) {
        this.containerRate = containerRate;
        return this;
    }

    public String getGreenRate() {
        return greenRate;
    }

    public MgtXiaoQuDetail setGreenRate(String greenRate) {
        this.greenRate = greenRate;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public MgtXiaoQuDetail setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public Integer getStructureType() {
        return structureType;
    }

    public MgtXiaoQuDetail setStructureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("cityId", cityId)
                .add("name", name)
                .add("alias", alias)
                .add("districtId", districtId)
                .add("subDistrictId", subDistrictId)
                .add("district", district)
                .add("subDistrict", subDistrict)
                .add("address", address)
                .add("developers", developers)
                .add("structureType", structureType)
                .add("structureStr", structureStr)
                .add("buildedYear", buildedYear)
                .add("developYear", developYear)
                .add("propertyCompany", propertyCompany)
                .add("propertyCompanyPhone", propertyCompanyPhone)
                .add("propertyFee", propertyFee)
                .add("parkingSpace", parkingSpace)
                .add("parkingFee", parkingFee)
                .add("parkingRate", parkingRate)
                .add("buildings", buildings)
                .add("houses", houses)
                .add("containerRate", containerRate)
                .add("greenRate", greenRate)
                .add("imageURI", imageURI)
                .add("longitude", longitude)
                .add("latitude", latitude)
                .toString();
    }
}
