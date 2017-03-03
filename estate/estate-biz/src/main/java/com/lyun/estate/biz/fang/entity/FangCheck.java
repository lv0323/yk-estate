package com.lyun.estate.biz.fang.entity;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-28.
 */
public class FangCheck {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private String advantage;
    private String disAdvantage;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public FangCheck setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangCheck setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangCheck setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangCheck setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangCheck setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getAdvantage() {
        return advantage;
    }

    public FangCheck setAdvantage(String advantage) {
        this.advantage = advantage;
        return this;
    }

    public String getDisAdvantage() {
        return disAdvantage;
    }

    public FangCheck setDisAdvantage(String disAdvantage) {
        this.disAdvantage = disAdvantage;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FangCheck setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangCheck setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FangCheck setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "FangCheck{" +
                "id=" + id +
                ", fangId=" + fangId +
                ", employeeId=" + employeeId +
                ", advantage='" + advantage + '\'' +
                ", disAdvantage='" + disAdvantage + '\'' +
                ", isDeleted=" + isDeleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
