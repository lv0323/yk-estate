package com.lyun.estate.biz.showing.entity;

import com.lyun.estate.biz.showing.def.ShowingDefine;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class ShowingSelector {
    private Long fangId;
    private Date minCreateTime;
    private Date maxCreateTime;
    private ShowingDefine.Process process;
    private Long companyId;
    private List<Long> departmentIds;
    private Long employeeId;

    public Long getFangId() {
        return fangId;
    }

    public ShowingSelector setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ShowingSelector setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public ShowingSelector setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public ShowingSelector setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public ShowingSelector setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public ShowingSelector setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }

    public ShowingDefine.Process getProcess() {
        return process;
    }

    public ShowingSelector setProcess(ShowingDefine.Process process) {
        this.process = process;
        return this;
    }
}
