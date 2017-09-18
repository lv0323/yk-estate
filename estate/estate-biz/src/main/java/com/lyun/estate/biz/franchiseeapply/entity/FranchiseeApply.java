package com.lyun.estate.biz.franchiseeapply.entity;

import java.util.Date;

public class FranchiseeApply {
    private Long id;
    private String city;
    private String type;
    private String name;
    private String gender;
    private String mobile;
    private String qq;
    private String email;
    private String message;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public FranchiseeApply setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public FranchiseeApply setCity(String city) {
        this.city = city;
        return this;
    }

    public String getType() {
        return type;
    }

    public FranchiseeApply setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public FranchiseeApply setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public FranchiseeApply setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public FranchiseeApply setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getQq() {
        return qq;
    }

    public FranchiseeApply setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FranchiseeApply setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public FranchiseeApply setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FranchiseeApply setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
