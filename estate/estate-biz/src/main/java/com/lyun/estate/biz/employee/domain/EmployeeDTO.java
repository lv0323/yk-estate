package com.lyun.estate.biz.employee.domain;

import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.support.def.Gender;

import java.util.Date;

public class EmployeeDTO {
    private Long id;
    private Long companyId;
    private Long cityId;
    private Long departmentId;
    private Long positionId;
    private Boolean isBoss;
    private Long avatarId;
    private String mobile;
    private String openContact;
    private String name;
    private Gender gender;
    private String idcardNumber;
    private String wechat;
    private WorkingStatus status;
    private Boolean quit;
    private String deviceId;
    private Date entryDate;

    private String companyAbbr;
    private String departmentName;
    private String positionName;


    public Long getId() {
        return id;
    }

    public EmployeeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public EmployeeDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public EmployeeDTO setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public EmployeeDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public EmployeeDTO setPositionId(Long positionId) {
        this.positionId = positionId;
        return this;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public EmployeeDTO setBoss(Boolean boss) {
        isBoss = boss;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public EmployeeDTO setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public EmployeeDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getOpenContact() {
        return openContact;
    }

    public EmployeeDTO setOpenContact(String openContact) {
        this.openContact = openContact;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public EmployeeDTO setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getIdcardNumber() {
        return idcardNumber;
    }

    public EmployeeDTO setIdcardNumber(String idcardNumber) {
        this.idcardNumber = idcardNumber;
        return this;
    }

    public String getWechat() {
        return wechat;
    }

    public EmployeeDTO setWechat(String wechat) {
        this.wechat = wechat;
        return this;
    }

    public WorkingStatus getStatus() {
        return status;
    }

    public EmployeeDTO setStatus(WorkingStatus status) {
        this.status = status;
        return this;
    }

    public Boolean getQuit() {
        return quit;
    }

    public EmployeeDTO setQuit(Boolean quit) {
        this.quit = quit;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public EmployeeDTO setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public EmployeeDTO setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
        return this;
    }

    public String getCompanyAbbr() {
        return companyAbbr;
    }

    public EmployeeDTO setCompanyAbbr(String companyAbbr) {
        this.companyAbbr = companyAbbr;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public EmployeeDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getPositionName() {
        return positionName;
    }

    public EmployeeDTO setPositionName(String positionName) {
        this.positionName = positionName;
        return this;
    }
}
