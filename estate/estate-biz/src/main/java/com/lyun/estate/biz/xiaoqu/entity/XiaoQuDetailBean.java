package com.lyun.estate.biz.xiaoqu.entity;

import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-01-12.
 */
public class XiaoQuDetailBean {
    private Long id;
    private BigDecimal ranking;
    private Integer avgPrice;
    private String name;
    private String alias;
    private YN nearLine;
    private String longitude;
    private String latitude;
    private Long cityId;
    private Long districtId;
    private Long subDistrictId;
    private String address;
    private String developers;
    private Integer structureType;
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

    public Long getId() {
        return id;
    }

    public XiaoQuDetailBean setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getRanking() {
        return ranking;
    }

    public XiaoQuDetailBean setRanking(BigDecimal ranking) {
        this.ranking = ranking;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQuDetailBean setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public String getName() {
        return name;
    }

    public XiaoQuDetailBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public XiaoQuDetailBean setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public XiaoQuDetailBean setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public XiaoQuDetailBean setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public XiaoQuDetailBean setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public XiaoQuDetailBean setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDevelopers() {
        return developers;
    }

    public XiaoQuDetailBean setDevelopers(String developers) {
        this.developers = developers;
        return this;
    }

    public Integer getStructureType() {
        return structureType;
    }

    public XiaoQuDetailBean setStructureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public XiaoQuDetailBean setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public String getDevelopYear() {
        return developYear;
    }

    public XiaoQuDetailBean setDevelopYear(String developYear) {
        this.developYear = developYear;
        return this;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public XiaoQuDetailBean setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
        return this;
    }

    public String getPropertyCompanyPhone() {
        return propertyCompanyPhone;
    }

    public XiaoQuDetailBean setPropertyCompanyPhone(String propertyCompanyPhone) {
        this.propertyCompanyPhone = propertyCompanyPhone;
        return this;
    }

    public String getPropertyFee() {
        return propertyFee;
    }

    public XiaoQuDetailBean setPropertyFee(String propertyFee) {
        this.propertyFee = propertyFee;
        return this;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public XiaoQuDetailBean setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public XiaoQuDetailBean setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public XiaoQuDetailBean setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
        return this;
    }

    public Integer getBuildings() {
        return buildings;
    }

    public XiaoQuDetailBean setBuildings(Integer buildings) {
        this.buildings = buildings;
        return this;
    }

    public Integer getHouses() {
        return houses;
    }

    public XiaoQuDetailBean setHouses(Integer houses) {
        this.houses = houses;
        return this;
    }

    public String getContainerRate() {
        return containerRate;
    }

    public XiaoQuDetailBean setContainerRate(String containerRate) {
        this.containerRate = containerRate;
        return this;
    }

    public String getGreenRate() {
        return greenRate;
    }

    public XiaoQuDetailBean setGreenRate(String greenRate) {
        this.greenRate = greenRate;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuDetailBean setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public XiaoQuDetailBean setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public XiaoQuDetailBean setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }
}
