package com.lyun.estate.biz.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Null;
import java.sql.Date;

public class Company {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String license;
    @NotEmpty
    private String address;
    private String introduction;
    @Null
    private String secretKey;
    @Future
    private Date startDate;
    @Future
    private Date endDate;
    private Boolean locked;
    private Boolean ipCheck;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public Company setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getLicense() {
        return license;
    }

    public Company setLicense(String license) {
        this.license = license;
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

    public Boolean getLocked() {
        return locked;
    }

    public Company setLocked(Boolean locked) {
        this.locked = locked;
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

    public Boolean getIpCheck() {
        return ipCheck;
    }

    public Company setIpCheck(Boolean ipCheck) {
        this.ipCheck = ipCheck;
        return this;
    }
}
