package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.InfoOwnerReason;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class FangInfoOwner {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private InfoOwnerReason reason;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public FangInfoOwner setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangInfoOwner setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangInfoOwner setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangInfoOwner setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangInfoOwner setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FangInfoOwner setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangInfoOwner setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FangInfoOwner setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public InfoOwnerReason getReason() {
        return reason;
    }

    public FangInfoOwner setReason(InfoOwnerReason reason) {
        this.reason = reason;
        return this;
    }
}
