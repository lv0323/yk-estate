package com.lyun.estate.biz.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.employee.def.Gender;
import com.lyun.estate.biz.employee.def.Status;
import com.lyun.estate.biz.position.entity.Position;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

public class Employee {

    private Long id;
    private Long companyId;
    @NotNull
    @JsonIgnore
    private Long departmentId;
    @Null
    private Department department;
    @JsonIgnore
    private Long positionId;
    @Null
    private Position position;
    @Null
    private Boolean isBoss;
    private Boolean isAgent;
    @Null
    private Long avatarId;
    @Pattern(regexp = "^1\\d{10}$")
    private String mobile;
    @Null
    @JsonIgnore
    private String password;
    @Null
    @JsonIgnore
    private String salt;
    @NotEmpty
    private String name;
    @NotNull
    private Gender gender;
    @Pattern(regexp = "(\\d{14}[0-9Xx])|(\\d{17}[0-9Xx])")
    private String idcardNumber;
    private String wechat;
    @NotNull
    private Status status;
    @Null
    private Boolean quit;
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

    public Department getDepartment() {
        return department;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Employee setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public Employee setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public Boolean getQuit() {
        return quit;
    }

    public Employee setQuit(Boolean quit) {
        this.quit = quit;
        return this;
    }
}
