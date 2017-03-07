package com.lyun.estate.biz.fang.domian;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-24.
 */
public class FangCheckSelector {
    private Long fangId;
    private Date minCreateTime;
    private Date maxCreateTime;
    private Long companyId;
    private List<Long> departmentIds;
    private Long employeeId;

    public Long getFangId() {
        return fangId;
    }

    public FangCheckSelector setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public FangCheckSelector setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public FangCheckSelector setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangCheckSelector setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public FangCheckSelector setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangCheckSelector setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }
}
