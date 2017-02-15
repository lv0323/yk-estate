package com.lyun.estate.biz.employee.entity;

import com.lyun.estate.biz.employee.def.Gender;
import com.lyun.estate.biz.employee.def.WorkingStatus;

import java.util.Date;

public class Employee {

    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long positionId;
    private Boolean isBoss;
    private Boolean isAgent;
    private Long avatarId;
    private String mobile;
    private String password;
    private String salt;
    private String name;
    private Gender gender;
    private String idcardNumber;
    private String wechat;
    private WorkingStatus status;
    private Boolean quit;
    private Date entryDate;
    private Date createTime;
    private Date updateTime;

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

    public Boolean getBoss() {
        return isBoss;
    }

    public Employee setBoss(Boolean boss) {
        isBoss = boss;
        return this;
    }

    public Boolean getAgent() {
        return isAgent;
    }

    public Employee setAgent(Boolean agent) {
        isAgent = agent;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public Employee setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
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

    public WorkingStatus getStatus() {
        return status;
    }

    public Employee setStatus(WorkingStatus status) {
        this.status = status;
        return this;
    }

    public Boolean getQuit() {
        return quit;
    }

    public Employee setQuit(Boolean quit) {
        this.quit = quit;
        return this;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Employee setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Employee setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Employee setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
