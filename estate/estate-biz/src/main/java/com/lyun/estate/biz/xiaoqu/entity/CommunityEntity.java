package com.lyun.estate.biz.xiaoqu.entity;

import com.lyun.estate.biz.fang.def.StructureType;
import com.lyun.estate.core.supports.types.YN;

import java.math.BigDecimal;
import java.util.Date;

public class CommunityEntity {
    private long id;
    private String name;
    private String alias;
    private long cityId;
    private long subDistrictId;
    private YN nearLine;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String address;
    private String developers;
    private StructureType structureType;
    private int buildedYear;
    private int developYear;
    private String propertyCompany;
    private String propertyCompanyPhone;
    private String propertyFee;
    private String parkingSpace;
    private String parkingFee;
    private String parkingRate;
    private int buildings;
    private int houses;
    private String containerRate;
    private String greenRate;
    private String ljId;
    private long createById;
    private Date createTime;
    private long updateById;
    private Date updateTime;
    private YN isDeleted;
    private int version;
    private String nameKw;
    private String aliasKw;

    public long getId() {
        return id;
    }

    public CommunityEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommunityEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public CommunityEntity setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public long getCityId() {
        return cityId;
    }

    public CommunityEntity setCityId(long cityId) {
        this.cityId = cityId;
        return this;
    }

    public long getSubDistrictId() {
        return subDistrictId;
    }

    public CommunityEntity setSubDistrictId(long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public YN getNearLine() {
        return nearLine;
    }

    public CommunityEntity setNearLine(YN nearLine) {
        this.nearLine = nearLine;
        return this;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public CommunityEntity setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public CommunityEntity setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CommunityEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDevelopers() {
        return developers;
    }

    public CommunityEntity setDevelopers(String developers) {
        this.developers = developers;
        return this;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public CommunityEntity setStructureType(StructureType structureType) {
        this.structureType = structureType;
        return this;
    }

    public int getBuildedYear() {
        return buildedYear;
    }

    public CommunityEntity setBuildedYear(int buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public int getDevelopYear() {
        return developYear;
    }

    public CommunityEntity setDevelopYear(int developYear) {
        this.developYear = developYear;
        return this;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public CommunityEntity setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
        return this;
    }

    public String getPropertyCompanyPhone() {
        return propertyCompanyPhone;
    }

    public CommunityEntity setPropertyCompanyPhone(String propertyCompanyPhone) {
        this.propertyCompanyPhone = propertyCompanyPhone;
        return this;
    }

    public String getPropertyFee() {
        return propertyFee;
    }

    public CommunityEntity setPropertyFee(String propertyFee) {
        this.propertyFee = propertyFee;
        return this;
    }

    public String getParkingSpace() {
        return parkingSpace;
    }

    public CommunityEntity setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
        return this;
    }

    public String getParkingFee() {
        return parkingFee;
    }

    public CommunityEntity setParkingFee(String parkingFee) {
        this.parkingFee = parkingFee;
        return this;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public CommunityEntity setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
        return this;
    }

    public int getBuildings() {
        return buildings;
    }

    public CommunityEntity setBuildings(int buildings) {
        this.buildings = buildings;
        return this;
    }

    public int getHouses() {
        return houses;
    }

    public CommunityEntity setHouses(int houses) {
        this.houses = houses;
        return this;
    }

    public String getContainerRate() {
        return containerRate;
    }

    public CommunityEntity setContainerRate(String containerRate) {
        this.containerRate = containerRate;
        return this;
    }

    public String getGreenRate() {
        return greenRate;
    }

    public CommunityEntity setGreenRate(String greenRate) {
        this.greenRate = greenRate;
        return this;
    }

    public String getLjId() {
        return ljId;
    }

    public CommunityEntity setLjId(String ljId) {
        this.ljId = ljId;
        return this;
    }

    public long getCreateById() {
        return createById;
    }

    public CommunityEntity setCreateById(long createById) {
        this.createById = createById;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CommunityEntity setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public long getUpdateById() {
        return updateById;
    }

    public CommunityEntity setUpdateById(long updateById) {
        this.updateById = updateById;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public CommunityEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public YN getIsDeleted() {
        return isDeleted;
    }

    public CommunityEntity setIsDeleted(YN isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public CommunityEntity setVersion(int version) {
        this.version = version;
        return this;
    }

    public String getNameKw() {
        return nameKw;
    }

    public CommunityEntity setNameKw(String nameKw) {
        this.nameKw = nameKw;
        return this;
    }

    public String getAliasKw() {
        return aliasKw;
    }

    public CommunityEntity setAliasKw(String aliasKw) {
        this.aliasKw = aliasKw;
        return this;
    }
}
