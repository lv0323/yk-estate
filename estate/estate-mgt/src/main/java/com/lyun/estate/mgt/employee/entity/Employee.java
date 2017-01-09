package com.lyun.estate.mgt.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyun.estate.mgt.employee.def.Gender;
import com.lyun.estate.mgt.employee.def.Status;

public class Employee {

    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long positionId;
    private Boolean isBoss;
    private Boolean isAgent;
    private String mobile;
    private String password;
    private String salt;
    private String name;
    private Gender gender;
    private String idcardNumber;
    private String wechat;
    private Status status;
    private java.sql.Date entryDate;
    @JsonIgnore
    private java.sql.Timestamp createTime;
    @JsonIgnore
    private java.sql.Timestamp updateTime;

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Employee setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Employee setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public Employee setPositionId(Long positionId) {
        this.positionId = positionId;
        return this;
    }

    public Boolean getIsBoss() {
        return isBoss;
    }

    public Employee setIsBoss(Boolean isBoss) {
        this.isBoss = isBoss;
        return this;
    }

    public Boolean getIsAgent() {
        return isAgent;
    }

    public Employee setIsAgent(Boolean isAgent) {
        this.isAgent = isAgent;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Employee setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Employee setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public Employee setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Employee setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public Employee setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
        return this;
    }

    public String getWechat() {
        return wechat;
    }

    public Employee setWechat(String wechat) {
        this.wechat = wechat;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Employee setStatus(Status status) {
        this.status = status;
        return this;
    }

    public java.sql.Date getEntryDate() {
        return entryDate;
    }

    public Employee setEntryDate(java.sql.Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public Employee setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
        return this;
    }

    public java.sql.Timestamp getUpdateTime() {
        return updateTime;
    }

    public Employee setUpdateTime(java.sql.Timestamp updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
