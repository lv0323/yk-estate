package com.lyun.estate.biz.auth.token;

public class JWTToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
