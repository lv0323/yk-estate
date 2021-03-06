package com.lyun.estate.mgt.context;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.support.def.Gender;

public class Operator {

    private Long id;
    private Long companyId;
    private CompanyDefine.Type companyType;
    private Boolean companyIpCheck;
    private Boolean sysAdmin;
    private String deviceId;
    private Long cityId;
    private Long departmentId;
    private String departmentName;
    private Long positionId;
    private String positionName;
    private Boolean isBoss;
    private String mobile;
    private String name;
    private Gender gender;

    public Long getId() {
        return id;
    }

    public Operator setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Operator setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public Operator setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Operator setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public Operator setPositionId(Long positionId) {
        this.positionId = positionId;
        return this;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public Operator setBoss(Boolean boss) {
        isBoss = boss;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Operator setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public Operator setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Operator setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Operator setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public String getPositionName() {
        return positionName;
    }

    public Operator setPositionName(String positionName) {
        this.positionName = positionName;
        return this;
    }

    public Boolean getCompanyIpCheck() {
        return companyIpCheck;
    }

    public Operator setCompanyIpCheck(Boolean companyIpCheck) {
        this.companyIpCheck = companyIpCheck;
        return this;
    }

    public Boolean getSysAdmin() {
        return sysAdmin;
    }

    public Operator setSysAdmin(Boolean sysAdmin) {
        this.sysAdmin = sysAdmin;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Operator setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public CompanyDefine.Type getCompanyType() {
        return companyType;
    }

    public Operator setCompanyType(CompanyDefine.Type companyType) {
        this.companyType = companyType;
        return this;
    }
}
