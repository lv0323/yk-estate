package com.lyun.estate.biz.auth.token;

import java.util.Date;

public class TokenEntity {

    private long id;
    private String userId;
    private long clientId;
    private String token;
    private Date expiredTime;
    private String refreshToken;

    public TokenEntity(String userId, long clientId, String token, Date expiredTime, String refreshToken) {
        this.userId = userId;
        this.clientId = clientId;
        this.token = token;
        this.expiredTime = expiredTime;
        this.refreshToken = refreshToken;
    }

    public TokenEntity(long id, String userId, long clientId, String token, Date expiredTime, String refreshToken) {
        this.id = id;
        this.userId = userId;
        this.clientId = clientId;
        this.token = token;
        this.expiredTime = expiredTime;
        this.refreshToken = refreshToken;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
