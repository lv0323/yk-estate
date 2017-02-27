package com.lyun.estate.biz.auth.token;

import java.util.Date;

public class Token {

    private long id;
    private Long refreshFrom;
    private String userId;
    private int clientId;
    private String token;
    private Date expiredTime;
    private String refreshToken;
    private Date refreshExpiredTime;
    private Date refreshEndTime;

    public long getId() {
        return id;
    }

    public Token setId(long id) {
        this.id = id;
        return this;
    }

    public Long getRefreshFrom() {
        return refreshFrom;
    }

    public Token setRefreshFrom(Long refreshFrom) {
        this.refreshFrom = refreshFrom;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Token setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getClientId() {
        return clientId;
    }

    public Token setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public Token setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Token setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Date getRefreshExpiredTime() {
        return refreshExpiredTime;
    }

    public Token setRefreshExpiredTime(Date refreshExpiredTime) {
        this.refreshExpiredTime = refreshExpiredTime;
        return this;
    }

    public Date getRefreshEndTime() {
        return refreshEndTime;
    }

    public Token setRefreshEndTime(Date refreshEndTime) {
        this.refreshEndTime = refreshEndTime;
        return this;
    }
}
