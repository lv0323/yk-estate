package com.lyun.estate.biz.user.resources;

import org.springframework.web.bind.annotation.RequestParam;

public class RegisterResource {
    private String mobile;
    private String password;
    private boolean login;

    public String getMobile() {
        return mobile;
    }

    public RegisterResource setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLogin() {
        return login;
    }

    public RegisterResource setLogin(boolean login) {
        this.login = login;
        return this;
    }
}
