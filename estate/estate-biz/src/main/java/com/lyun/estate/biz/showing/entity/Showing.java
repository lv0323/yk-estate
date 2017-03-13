package com.lyun.estate.biz.showing.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.showing.def.ShowingDefine;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class Showing {
    private Long id;
    private Long customerId;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private Date createTime;
    private Date closeTime;
    private Date updateTime;
    private ShowingDefine.Process process;
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public Showing setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Showing setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public Showing setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Showing setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Showing setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Showing setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Showing setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Showing setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ShowingDefine.Process getProcess() {
        return process;
    }

    public Showing setProcess(ShowingDefine.Process process) {
        this.process = process;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Showing setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public Showing setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("customerId", customerId)
                .add("fangId", fangId)
                .add("companyId", companyId)
                .add("departmentId", departmentId)
                .add("employeeId", employeeId)
                .add("createTime", createTime)
                .add("closeTime", closeTime)
                .add("updateTime", updateTime)
                .add("process", process)
                .add("isDeleted", isDeleted)
                .toString();
    }
}
