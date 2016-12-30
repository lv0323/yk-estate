package com.lyun.estate.biz.user.resources;

public class RegisterResource {
    private String userName;
    private String email;
    private String password;
    private boolean login;


    public String getUserName() {
        return userName;
    }

    public RegisterResource setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterResource setEmail(String email) {
        this.email = email;
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
