package com.lyun.estate.biz.user.resources;

public class ChangePasswordResource {
    private String oldPassword;
    private String password;
    private boolean forget;
    private boolean login;

    public String getOldPassword() {
        return oldPassword;
    }

    public ChangePasswordResource setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ChangePasswordResource setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isForget() {
        return forget;
    }

    public ChangePasswordResource setForget(boolean forget) {
        this.forget = forget;
        return this;
    }

    public boolean isLogin() {
        return login;
    }

    public ChangePasswordResource setLogin(boolean login) {
        this.login = login;
        return this;
    }
}
