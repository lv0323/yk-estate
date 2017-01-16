package com.lyun.estate.biz.user.resources;

public class SaltResponse {
    private String salt;

    public String getSalt() {
        return salt;
    }

    public SaltResponse setSalt(String salt) {
        this.salt = salt;
        return this;
    }
}
