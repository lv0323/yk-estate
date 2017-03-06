package com.lyun.estate.biz.spec.fang.mgt.entity;

import com.lyun.estate.biz.fang.def.FollowType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class FangFollowFilter {
    private Long fangId;
    private FollowType followType;
    private Date minFollowTime;
    private Date maxFollowTime;
    private Long companyId;
    private Long departmentId;
    private Boolean children;
    private Long employeeId;
    private Long ioDepartmentId;
    private Boolean ioChildren;
    private Long ioEmployeeId;

    public Long getFangId() {
        return fangId;
    }

    public FangFollowFilter setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public FollowType getFollowType() {
        return followType;
    }

    public FangFollowFilter setFollowType(FollowType followType) {
        this.followType = followType;
        return this;
    }

    public Date getMinFollowTime() {
        return minFollowTime;
    }

    public FangFollowFilter setMinFollowTime(Date minFollowTime) {
        this.minFollowTime = minFollowTime;
        return this;
    }

    public Date getMaxFollowTime() {
        return maxFollowTime;
    }

    public FangFollowFilter setMaxFollowTime(Date maxFollowTime) {
        this.maxFollowTime = maxFollowTime;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangFollowFilter setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangFollowFilter setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Boolean getChildren() {
        return children;
    }

    public FangFollowFilter setChildren(Boolean children) {
        this.children = children;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangFollowFilter setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Long getIoDepartmentId() {
        return ioDepartmentId;
    }

    public FangFollowFilter setIoDepartmentId(Long ioDepartmentId) {
        this.ioDepartmentId = ioDepartmentId;
        return this;
    }

    public Boolean getIoChildren() {
        return ioChildren;
    }

    public FangFollowFilter setIoChildren(Boolean ioChildren) {
        this.ioChildren = ioChildren;
        return this;
    }

    public Long getIoEmployeeId() {
        return ioEmployeeId;
    }

    public FangFollowFilter setIoEmployeeId(Long ioEmployeeId) {
        this.ioEmployeeId = ioEmployeeId;
        return this;
    }
}
