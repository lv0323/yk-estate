package com.lyun.estate.biz.fang.domian;

import com.google.common.base.MoreObjects;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-28.
 */
public class FangCheckDTO {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private String departmentName;
    private Long employeeId;
    private String employeeName;
    private Long avatarId;
    private String avatarURI;
    private String advantage;
    private String disAdvantage;
    private Boolean isDeleted;
    private Date createTime;
    private Date updateTime;
    private MgtFangTiny fangTiny;


    public Long getId() {
        return id;
    }

    public FangCheckDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangCheckDTO setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangCheckDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangCheckDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangCheckDTO setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getAdvantage() {
        return advantage;
    }

    public FangCheckDTO setAdvantage(String advantage) {
        this.advantage = advantage;
        return this;
    }

    public String getDisAdvantage() {
        return disAdvantage;
    }

    public FangCheckDTO setDisAdvantage(String disAdvantage) {
        this.disAdvantage = disAdvantage;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FangCheckDTO setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangCheckDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FangCheckDTO setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public FangCheckDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public FangCheckDTO setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public MgtFangTiny getFangTiny() {
        return fangTiny;
    }

    public FangCheckDTO setFangTiny(MgtFangTiny fangTiny) {
        this.fangTiny = fangTiny;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public FangCheckDTO setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public FangCheckDTO setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("fangId", fangId)
                .add("companyId", companyId)
                .add("departmentId", departmentId)
                .add("departmentName", departmentName)
                .add("employeeId", employeeId)
                .add("employeeName", employeeName)
                .add("avatarId", avatarId)
                .add("avatarURI", avatarURI)
                .add("advantage", advantage)
                .add("disAdvantage", disAdvantage)
                .add("isDeleted", isDeleted)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("fangTiny", fangTiny)
                .toString();
    }
}
