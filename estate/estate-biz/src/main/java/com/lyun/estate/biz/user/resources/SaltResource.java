package com.lyun.estate.biz.user.resources;


public class SaltResource {
    private String mobile;
    private String userName;
    private String email;

    public String getMobile() {
        return mobile;
    }

    public SaltResource setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public SaltResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SaltResource setEmail(String email) {
        this.email = email;
        return this;
    }
}
