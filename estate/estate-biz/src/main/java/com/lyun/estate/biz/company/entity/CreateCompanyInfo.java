package com.lyun.estate.biz.company.entity;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.company.domain.Company;
import com.lyun.estate.biz.company.domain.CompanySigning;
import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.employee.entity.Employee;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-05-15.
 */
public class CreateCompanyInfo {
    private Long parentId;
    private Long cityId;
    private CompanyDefine.Type type;
    private String name;
    private String abbr;
    private String address;
    private String introduction;

    private Long partAId;
    private Date startDate;
    private Date endDate;
    private Integer years;
    private Integer storeCount;
    private BigDecimal price;

    private String bossName;
    private String mobile;


    public Long getParentId() {
        return parentId;
    }

    public CreateCompanyInfo setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public CreateCompanyInfo setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public CompanyDefine.Type getType() {
        return type;
    }

    public CreateCompanyInfo setType(CompanyDefine.Type type) {
        this.type = type;
        return this;
    }


    public String getName() {
        return name;
    }

    public CreateCompanyInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public CreateCompanyInfo setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CreateCompanyInfo setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public CreateCompanyInfo setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public Long getPartAId() {
        return partAId;
    }

    public CreateCompanyInfo setPartAId(Long partAId) {
        this.partAId = partAId;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public CreateCompanyInfo setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CreateCompanyInfo setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Integer getYears() {
        return years;
    }

    public CreateCompanyInfo setYears(Integer years) {
        this.years = years;
        return this;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public CreateCompanyInfo setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateCompanyInfo setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getBossName() {
        return bossName;
    }

    public CreateCompanyInfo setBossName(String bossName) {
        this.bossName = bossName;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public CreateCompanyInfo setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Company getCompanyINfo() {
        return new Company()
                .setName(name)
                .setAbbr(abbr)
                .setAddress(address)
                .setIntroduction(introduction)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setCityId(cityId)
                .setParentId(parentId)
                .setType(type)
                .setStatus(CompanyDefine.Status.ACTIVE);
    }

    public Employee getBossInfo() {
        return new Employee()
                .setBoss(true)
                .setCityId(cityId)
                .setName(bossName)
                .setMobile(mobile)
                .setEntryDate(new Date())
                .setStatus(WorkingStatus.WORKING);
    }

    public CompanySigning getSigningInfo() {
        return new CompanySigning()
                .setPartAId(partAId)
                .setStartDate(startDate)
                .setEndDate(endDate)
                .setYears(years)
                .setStoreCount(storeCount)
                .setPrice(price);
    }
}
