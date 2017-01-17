package com.lyun.estate.mgt.employee;

import com.lyun.estate.biz.employee.def.Gender;

public class LoginEmployee {

    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long positionId;
    private Boolean isBoss;
    private Boolean isAgent;
    private String mobile;
    private String name;
    private Gender gender;

    public LoginEmployee(Long id, Long companyId, Long departmentId, Long positionId, Boolean isBoss, Boolean isAgent, String mobile, String name, Gender gender) {
        this.id = id;
        this.companyId = companyId;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.isBoss = isBoss;
        this.isAgent = isAgent;
        this.mobile = mobile;
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public LoginEmployee setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public LoginEmployee setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public LoginEmployee setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getPositionId() {
        return positionId;
    }

    public LoginEmployee setPositionId(Long positionId) {
        this.positionId = positionId;
        return this;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public LoginEmployee setBoss(Boolean boss) {
        isBoss = boss;
        return this;
    }

    public Boolean getAgent() {
        return isAgent;
    }

    public LoginEmployee setAgent(Boolean agent) {
        isAgent = agent;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public LoginEmployee setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginEmployee setName(String name) {
        this.name = name;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public LoginEmployee setGender(Gender gender) {
        this.gender = gender;
        return this;
    }
}
