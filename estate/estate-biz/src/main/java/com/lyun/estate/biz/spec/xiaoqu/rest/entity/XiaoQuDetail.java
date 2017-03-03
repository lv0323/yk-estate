package com.lyun.estate.biz.spec.xiaoqu.rest.entity;

import com.lyun.estate.core.supports.types.YN;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuDetail {
    private Long id;
    private Long cityId;
    private Integer avgPrice;
    private String name;
    private String alias;
    private YN nearLine;
    private String city;
    private String district;
    private String subDistrict;
    private String longitude;
    private String latitude;
    private String address;
    private String developers;
    private String structure;
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
    private Integer follows;
    private String imageURI;

    public Long getId() {
        return id;
    }

    public XiaoQuDetail setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuDetail setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQuDetail setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public XiaoQuDetail setName(String name) {
        this.name = name;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public XiaoQuDetail setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public XiaoQuDetail setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public String getCity() {
        return city;
    }

    public XiaoQuDetail setCity(String city) {
        this.city = city;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public XiaoQuDetail setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public XiaoQuDetail setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public XiaoQuDetail setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public XiaoQuDetail setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public XiaoQuDetail setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDevelopers() {
        return developers;
    }

    public XiaoQuDetail setDevelopers(String developers) {
        this.developers = developers;
        return this;
    }

    public String getStructure() {
        return structure;
    }

    public XiaoQuDetail setStructure(String structure) {
        this.structure = structure;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public XiaoQuDetail setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public String getDevelopYear() {
        return developYear;
    }

    public XiaoQuDetail setDevelopYear(String developYear) {
        this.developYear = developYear;
        return this;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public XiaoQuDetail setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
        return this;
    }

    public String getPropertyCompanyPhone() {
        return propertyCompanyPhone;
    }

    public XiaoQuDetail setPropertyCompanyPhone(String propertyCompanyPhone) {
        this.propertyCompanyPhone = propertyCompanyPhone;
        return this;
    }

    public String getPropertyFee() {
        return propertyFee;
    }

    public XiaoQuDetail setPropertyFee(String propertyFee) {
        this.propertyFee = propertyFee;
        return this;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public XiaoQuDetail setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public XiaoQuDetail setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public XiaoQuDetail setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
        return this;
    }

    public Integer getBuildings() {
        return buildings;
    }

    public XiaoQuDetail setBuildings(Integer buildings) {
        this.buildings = buildings;
        return this;
    }

    public Integer getHouses() {
        return houses;
    }

    public XiaoQuDetail setHouses(Integer houses) {
        this.houses = houses;
        return this;
    }

    public String getContainerRate() {
        return containerRate;
    }

    public XiaoQuDetail setContainerRate(String containerRate) {
        this.containerRate = containerRate;
        return this;
    }

    public String getGreenRate() {
        return greenRate;
    }

    public XiaoQuDetail setGreenRate(String greenRate) {
        this.greenRate = greenRate;
        return this;
    }

    public String getImageURI() {
        return imageURI;
    }

    public XiaoQuDetail setImageURI(String imageURI) {
        this.imageURI = imageURI;
        return this;
    }

    public Integer getFollows() {
        return follows;
    }

    public XiaoQuDetail setFollows(Integer follows) {
        this.follows = follows;
        return this;
    }

    @Override
    public String toString() {
        return "XiaoQuDetail{" +
                "id=" + id +
                ", avgPrice=" + avgPrice +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", nearLine=" + nearLine +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", subDistrict='" + subDistrict + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", address='" + address + '\'' +
                ", developers='" + developers + '\'' +
                ", structure='" + structure + '\'' +
                ", buildedYear=" + buildedYear +
                ", developYear='" + developYear + '\'' +
                ", propertyCompany='" + propertyCompany + '\'' +
                ", propertyCompanyPhone='" + propertyCompanyPhone + '\'' +
                ", propertyFee='" + propertyFee + '\'' +
                ", parkingSpace='" + parkingSpace + '\'' +
                ", parkingFee='" + parkingFee + '\'' +
                ", parkingRate='" + parkingRate + '\'' +
                ", buildings=" + buildings +
                ", houses=" + houses +
                ", containerRate='" + containerRate + '\'' +
                ", greenRate='" + greenRate + '\'' +
                ", imageURI='" + imageURI + '\'' +
                '}';
    }
}
