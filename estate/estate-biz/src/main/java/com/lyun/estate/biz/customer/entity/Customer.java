package com.lyun.estate.biz.customer.entity;


import com.google.common.base.MoreObjects;
import com.lyun.estate.biz.support.def.Gender;
import com.lyun.estate.biz.support.def.IdentitySource;

import static com.lyun.estate.biz.customer.def.CustomerDefine.*;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class Customer {
    private Long id;
    private String name;
    private Source source;
    private String mobile;
    private Long companyId;
    private Long departmentId;
    private Long employeeId;
    private String aMobile;
    private String bMobile;
    private String qq;
    private String weChat;
    private String email;
    private IdentitySource identitySource;
    private String identityNo;
    private Gender gender;
    private String residentAddress;//户籍地址
    private Status status;
    private Level level;
    private Purpose purpose;
    private TimeLimits timeLimits;

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Customer setSource(Source source) {
        this.source = source;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public Customer setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public Customer setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Customer setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Customer setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getaMobile() {
        return aMobile;
    }

    public Customer setaMobile(String aMobile) {
        this.aMobile = aMobile;
        return this;
    }

    public String getbMobile() {
        return bMobile;
    }

    public Customer setbMobile(String bMobile) {
        this.bMobile = bMobile;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public Customer setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getWeChat() {
        return weChat;
    }

    public Customer setWeChat(String weChat) {
        this.weChat = weChat;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public IdentitySource getIdentitySource() {
        return identitySource;
    }

    public Customer setIdentitySource(IdentitySource identitySource) {
        this.identitySource = identitySource;
        return this;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public Customer setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Customer setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public Customer setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Customer setStatus(Status status) {
        this.status = status;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Customer setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public Customer setPurpose(Purpose purpose) {
        this.purpose = purpose;
        return this;
    }

    public TimeLimits getTimeLimits() {
        return timeLimits;
    }

    public Customer setTimeLimits(TimeLimits timeLimits) {
        this.timeLimits = timeLimits;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("source", source)
                .add("mobile", mobile)
                .add("companyId", companyId)
                .add("departmentId", departmentId)
                .add("employeeId", employeeId)
                .add("aMobile", aMobile)
                .add("bMobile", bMobile)
                .add("qq", qq)
                .add("weChat", weChat)
                .add("email", email)
                .add("identitySource", identitySource)
                .add("identityNo", identityNo)
                .add("gender", gender)
                .add("residentAddress", residentAddress)
                .add("status", status)
                .add("level", level)
                .add("purpose", purpose)
                .add("timeLimits", timeLimits)
                .toString();
    }
}
