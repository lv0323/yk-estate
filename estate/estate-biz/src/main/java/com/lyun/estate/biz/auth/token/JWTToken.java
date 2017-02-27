package com.lyun.estate.biz.auth.token;

public class JWTToken {

    private String token;
    private String refreshToken;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public JWTToken setToken(String token) {
        this.token = token;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public JWTToken setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
