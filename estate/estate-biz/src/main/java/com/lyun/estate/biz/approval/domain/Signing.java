package com.lyun.estate.biz.approval.domain;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-06-15.
 */
public class Signing {
    private String companyType;
    private String companyName;
    private String companyAbbr;
    private Long cityId;
    private String cityName;
    private String address;
    private String bossName;
    private String bossMobile;
    private String partAInChargeId;//本公司负责人编号
    private String partAInChargeName;//本公司负责人姓名
    private String note;
    //签约详情
    private Integer years;
    private Integer storeCount;
    private String startDate;
    private String endDate;
    private BigDecimal price;

    private Long parentId;
    private Long companyId;

    public String getCompanyType() {
        return companyType;
    }

    public Signing setCompanyType(String companyType) {
        this.companyType = companyType;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Signing setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getCompanyAbbr() {
        return companyAbbr;
    }

    public Signing setCompanyAbbr(String companyAbbr) {
        this.companyAbbr = companyAbbr;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Signing setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public Signing setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Signing setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBossName() {
        return bossName;
    }

    public Signing setBossName(String bossName) {
        this.bossName = bossName;
        return this;
    }

    public String getBossMobile() {
        return bossMobile;
    }

    public Signing setBossMobile(String bossMobile) {
        this.bossMobile = bossMobile;
        return this;
    }

    public String getPartAInChargeId() {
        return partAInChargeId;
    }

    public Signing setPartAInChargeId(String partAInChargeId) {
        this.partAInChargeId = partAInChargeId;
        return this;
    }

    public String getPartAInChargeName() {
        return partAInChargeName;
    }

    public Signing setPartAInChargeName(String partAInChargeName) {
        this.partAInChargeName = partAInChargeName;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Signing setNote(String note) {
        this.note = note;
        return this;
    }

    public Integer getYears() {
        return years;
    }

    public Signing setYears(Integer years) {
        this.years = years;
        return this;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public Signing setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public Signing setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public Signing setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Signing setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Signing setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Signing setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }
}
