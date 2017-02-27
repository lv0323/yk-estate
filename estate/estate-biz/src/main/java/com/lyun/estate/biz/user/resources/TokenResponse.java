package com.lyun.estate.biz.user.resources;

public class TokenResponse {
    private String token;
    private String refreshToken;

    public String getToken() {
        return token;
    }

    public TokenResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public TokenResponse setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
