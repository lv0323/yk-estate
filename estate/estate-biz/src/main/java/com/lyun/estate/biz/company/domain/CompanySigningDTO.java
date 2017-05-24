package com.lyun.estate.biz.company.domain;

import com.lyun.estate.biz.employee.domain.EmployeeDTO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Jeffrey on 2017-05-15.
 */
public class CompanySigningDTO {
    private Long id;
    private Long companyId;
    private Long partAId;
    private Integer years;
    private Integer storeCount;
    private Date startDate;
    private Date endDate;
    private BigDecimal price;
    private EmployeeDTO partA;

    public Long getId() {
        return id;
    }

    public CompanySigningDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public CompanySigningDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getPartAId() {
        return partAId;
    }

    public CompanySigningDTO setPartAId(Long partAId) {
        this.partAId = partAId;
        return this;
    }

    public Integer getYears() {
        return years;
    }

    public CompanySigningDTO setYears(Integer years) {
        this.years = years;
        return this;
    }

    public Integer getStoreCount() {
        return storeCount;
    }

    public CompanySigningDTO setStoreCount(Integer storeCount) {
        this.storeCount = storeCount;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public CompanySigningDTO setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CompanySigningDTO setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CompanySigningDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EmployeeDTO getPartA() {
        return partA;
    }

    public CompanySigningDTO setPartA(EmployeeDTO partA) {
        this.partA = partA;
        return this;
    }
}
