package com.lyun.estate.biz.fang.domian;

import com.lyun.estate.biz.fang.def.FollowType;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-24.
 */
public class FangFollowSelector {
    private Long fangId;
    private FollowType followType;
    private Date minFollowTime;
    private Date maxFollowTime;
    private Long companyId;
    private List<Long> departmentIds;
    private Long employeeId;
    private List<Long> ioDepartmentIds;
    private Long ioEmployeeId;

    public Long getFangId() {
        return fangId;
    }

    public FangFollowSelector setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public FollowType getFollowType() {
        return followType;
    }

    public FangFollowSelector setFollowType(FollowType followType) {
        this.followType = followType;
        return this;
    }

    public Date getMinFollowTime() {
        return minFollowTime;
    }

    public FangFollowSelector setMinFollowTime(Date minFollowTime) {
        this.minFollowTime = minFollowTime;
        return this;
    }

    public Date getMaxFollowTime() {
        return maxFollowTime;
    }

    public FangFollowSelector setMaxFollowTime(Date maxFollowTime) {
        this.maxFollowTime = maxFollowTime;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangFollowSelector setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public FangFollowSelector setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangFollowSelector setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public List<Long> getIoDepartmentIds() {
        return ioDepartmentIds;
    }

    public FangFollowSelector setIoDepartmentIds(List<Long> ioDepartmentIds) {
        this.ioDepartmentIds = ioDepartmentIds;
        return this;
    }

    public Long getIoEmployeeId() {
        return ioEmployeeId;
    }

    public FangFollowSelector setIoEmployeeId(Long ioEmployeeId) {
        this.ioEmployeeId = ioEmployeeId;
        return this;
    }
}
