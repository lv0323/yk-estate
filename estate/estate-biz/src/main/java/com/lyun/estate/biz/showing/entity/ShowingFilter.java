package com.lyun.estate.biz.showing.entity;

import com.lyun.estate.biz.showing.def.ShowingDefine;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class ShowingFilter {
    private Long fangId;
    private Date minCreateTime;
    private Date maxCreateTime;
    private ShowingDefine.Process process;
    private Long companyId;
    private Long departmentId;
    private Boolean children;
    private Long employeeId;

    public Long getFangId() {
        return fangId;
    }

    public ShowingFilter setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ShowingFilter setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public ShowingFilter setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Boolean getChildren() {
        return children;
    }

    public ShowingFilter setChildren(Boolean children) {
        this.children = children;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public ShowingFilter setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public ShowingFilter setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public ShowingFilter setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }

    public ShowingDefine.Process getProcess() {
        return process;
    }

    public ShowingFilter setProcess(ShowingDefine.Process process) {
        this.process = process;
        return this;
    }
}
