package com.lyun.estate.biz.company.domain;

import com.lyun.estate.biz.company.def.CompanyDefine;

import java.util.Date;


public class Company {
    private Long id;
    private Long cityId;
    private Long parentId;
    private CompanyDefine.Type type;
    private CompanyDefine.Status status;
    private String name;
    private String abbr;
    private String address;
    private String introduction;
    private String secretKey;
    private Long bossId;
    private Date startDate;
    private Date endDate;
    private boolean ipCheck;
    private boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public Company setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Company setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public Company setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public CompanyDefine.Type getType() {
        return type;
    }

    public Company setType(CompanyDefine.Type type) {
        this.type = type;
        return this;
    }

    public CompanyDefine.Status getStatus() {
        return status;
    }

    public Company setStatus(CompanyDefine.Status status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public Company setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Company setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Company setIntroduction(String introduction) {
        this.introduction = introduction;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public Company setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public Long getBossId() {
        return bossId;
    }

    public Company setBossId(Long bossId) {
        this.bossId = bossId;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Company setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Company setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isIpCheck() {
        return ipCheck;
    }

    public Company setIpCheck(boolean ipCheck) {
        this.ipCheck = ipCheck;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public Company setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Company setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Company setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
