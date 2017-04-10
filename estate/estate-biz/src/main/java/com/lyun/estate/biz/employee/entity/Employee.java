package com.lyun.estate.biz.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.support.def.Gender;

import java.util.Date;

public class Employee {

    private Long id;
    private Long companyId;
    private Boolean companyIpCheck;
    private Long cityId;
    private Long departmentId;
    private String departmentName;
    private Long positionId;
    private String positionName;
    private Boolean isBoss;
    @JsonIgnore
    private Boolean isAgent;
    private Long avatarId;
    private String mobile;
    private String openContact;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private String name;
    private Gender gender;
    private String idcardNumber;
    private String wechat;
    private WorkingStatus status;
    private Boolean quit;
    private String deviceId;
    @JsonIgnore
    private Boolean sysAdmin;
    private Long followFangId;
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

    public Boolean getCompanyIpCheck() {
        return companyIpCheck;
    }

    public Employee setCompanyIpCheck(Boolean companyIpCheck) {
        this.companyIpCheck = companyIpCheck;
        return this;
    }

    public Boolean getSysAdmin() {
        return sysAdmin;
    }

    public Employee setSysAdmin(Boolean sysAdmin) {
        this.sysAdmin = sysAdmin;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Employee setCityId(Long cityId) {
        this.cityId = cityId;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public Employee setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getPositionName() {
        return positionName;
    }

    public Employee setPositionName(String positionName) {
        this.positionName = positionName;
        return this;
    }

    public String getOpenContact() {
        return openContact;
    }

    public Employee setOpenContact(String openContact) {
        this.openContact = openContact;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Employee setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Long getFollowFangId() {
        return followFangId;
    }

    public Employee setFollowFangId(Long followFangId) {
        this.followFangId = followFangId;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .omitNullValues()
                .add("id", id)
                .add("companyId", companyId)
                .add("cityId", cityId)
                .add("departmentId", departmentId)
                .add("departmentName", departmentName)
                .add("positionId", positionId)
                .add("positionName", positionName)
                .add("isBoss", isBoss)
                .add("isAgent", isAgent)
                .add("avatarId", avatarId)
                .add("mobile", mobile)
                .add("openContact", openContact)
                .add("password", password)
                .add("salt", salt)
                .add("name", name)
                .add("gender", gender)
                .add("idcardNumber", idcardNumber)
                .add("wechat", wechat)
                .add("status", status)
                .add("quit", quit)
                .add("deviceId", deviceId)
                .add("entryDate", entryDate)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .add("followFangId", followFangId)
                .toString();
    }
}
