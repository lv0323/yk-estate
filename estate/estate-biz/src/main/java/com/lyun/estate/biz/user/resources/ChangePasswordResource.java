package com.lyun.estate.biz.user.resources;

public class ChangePasswordResource {
    private String oldPassword;
    private String signature;// old (salt + password) hash + password + salt + hash (sha256-base64-replace "=")
    private String password;
    private String salt;
    private String hash;
    private long userId;
    private boolean login;

    public long getUserId() {
        return userId;
    }

    public ChangePasswordResource setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public ChangePasswordResource setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public String getSignature() {
        return signature;
    }

    public ChangePasswordResource setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public ChangePasswordResource setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public ChangePasswordResource setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ChangePasswordResource setPassword(String password) {
        this.password = password;
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
