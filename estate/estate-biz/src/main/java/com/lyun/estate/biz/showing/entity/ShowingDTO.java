package com.lyun.estate.biz.showing.entity;

import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.showing.def.ShowingDefine;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class ShowingDTO {
    private Long id;
    private Long customerId;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private String departmentName;
    private Long employeeId;
    private String employeeName;
    private Date createTime;
    private Date updateTime;
    private ShowingDefine.Process process;
    private Boolean isDeleted;
    private MgtFangTiny fangTiny;

    public Long getId() {
        return id;
    }

    public ShowingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public ShowingDTO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public ShowingDTO setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public ShowingDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public ShowingDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public ShowingDTO setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ShowingDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public ShowingDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public ShowingDefine.Process getProcess() {
        return process;
    }

    public ShowingDTO setProcess(ShowingDefine.Process process) {
        this.process = process;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public ShowingDTO setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public ShowingDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public ShowingDTO setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public MgtFangTiny getFangTiny() {
        return fangTiny;
    }

    public ShowingDTO setFangTiny(MgtFangTiny fangTiny) {
        this.fangTiny = fangTiny;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("customerId", customerId)
                .add("fangId", fangId)
                .add("companyId", companyId)
                .add("departmentId", departmentId)
                .add("departmentName", departmentName)
                .add("employeeId", employeeId)
                .add("employeeName", employeeName)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("process", process)
                .add("isDeleted", isDeleted)
                .add("fangTiny", fangTiny)
                .toString();
    }
}
