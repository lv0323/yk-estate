package com.lyun.estate.biz.user.resources;

import com.lyun.estate.core.supports.types.UserType;

public class LoginResource {
    private String userName;
    private String email;
    private String mobile;
    private String password;
    private UserType type;
    private int validDays;

    public String getUserName() {
        return userName;
    }

    public LoginResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginResource setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public LoginResource setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserType getType() {
        return type;
    }

    public LoginResource setType(UserType type) {
        this.type = type;
        return this;
    }

    public int getValidDays() {
        return validDays;
    }

    public LoginResource setValidDays(int validDays) {
        this.validDays = validDays;
        return this;
    }
}
