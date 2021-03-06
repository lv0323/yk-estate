package com.lyun.estate.biz.xiaoqu.entity;

/**
 * Created by Jeffrey on 2017-01-10.
 */
public class XiaoQuSummaryBean {
    private Long id;
    private Long cityId;
    private String name;
    private Integer sellHouseCount;
    private Integer rentHouseCount;
    private Long districtId;
    private Long subDistrictId;
    private Integer buildedYear;
    private Integer avgPrice;
    private Integer structureType;

    public Long getId() {
        return id;
    }

    public XiaoQuSummaryBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public XiaoQuSummaryBean setName(String name) {
        this.name = name;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public XiaoQuSummaryBean setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public XiaoQuSummaryBean setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public Integer getBuildedYear() {
        return buildedYear;
    }

    public XiaoQuSummaryBean setBuildedYear(Integer buildedYear) {
        this.buildedYear = buildedYear;
        return this;
    }

    public Integer getAvgPrice() {
        return avgPrice;
    }

    public XiaoQuSummaryBean setAvgPrice(Integer avgPrice) {
        this.avgPrice = avgPrice;
        return this;
    }

    public Integer getStructureType() {
        return structureType;
    }

    public XiaoQuSummaryBean setStructureType(Integer structureType) {
        this.structureType = structureType;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public XiaoQuSummaryBean setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Integer getSellHouseCount() {
        return sellHouseCount;
    }

    public XiaoQuSummaryBean setSellHouseCount(Integer sellHouseCount) {
        this.sellHouseCount = sellHouseCount;
        return this;
    }

    public Integer getRentHouseCount() {
        return rentHouseCount;
    }

    public XiaoQuSummaryBean setRentHouseCount(Integer rentHouseCount) {
        this.rentHouseCount = rentHouseCount;
        return this;
    }
}
