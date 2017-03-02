package com.lyun.estate.biz.fang.entity;

import com.lyun.estate.biz.fang.def.InfoOwnerReason;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class FangInfoOwnerDTO {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private InfoOwnerReason reason;
    private String departmentName;
    private String employeeName;

    public Long getId() {
        return id;
    }

    public FangInfoOwnerDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangInfoOwnerDTO setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangInfoOwnerDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangInfoOwnerDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangInfoOwnerDTO setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public InfoOwnerReason getReason() {
        return reason;
    }

    public FangInfoOwnerDTO setReason(InfoOwnerReason reason) {
        this.reason = reason;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public FangInfoOwnerDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public FangInfoOwnerDTO setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }
}
