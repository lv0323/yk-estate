package com.lyun.estate.biz.auth.token;

public class JWTToken {

    private String token;
    private String refreshToken;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
