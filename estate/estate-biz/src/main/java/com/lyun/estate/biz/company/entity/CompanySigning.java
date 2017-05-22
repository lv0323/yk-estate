package com.lyun.estate.biz.company.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-05-15.
 */
public class CompanySigning {
    private Long id;
    private Long companyId;
    private Long partAId;
    private Integer years;
    private Integer storeCount;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
    private boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public CompanySigning setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public CompanySigning setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getPartAId() {
        return partAId;
    }

    public CompanySigning setPartAId(Long partAId) {
        this.partAId = partAId;
        return this;
    }

    public Integer getYears() {
        return years;
    }

    public CompanySigning setYears(Integer years) {
        this.years = years;
        return this;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public CompanySigning setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public CompanySigning setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CompanySigning setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CompanySigning setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public CompanySigning setDeleted(boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public CompanySigning setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public CompanySigning setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

}
