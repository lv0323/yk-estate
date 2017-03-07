package com.lyun.estate.biz.spec.fang.mgt.entity;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class FangCheckFilter {
    private Long fangId;
    private Date minCreateTime;
    private Date maxCreateTime;
    private Long companyId;
    private Long departmentId;
    private Boolean children;
    private Long employeeId;

    public Long getFangId() {
        return fangId;
    }

    public FangCheckFilter setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangCheckFilter setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangCheckFilter setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Boolean getChildren() {
        return children;
    }

    public FangCheckFilter setChildren(Boolean children) {
        this.children = children;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangCheckFilter setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public FangCheckFilter setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public FangCheckFilter setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }
}
