package com.lyun.estate.biz.user.domain;

import com.lyun.estate.core.domain.BaseEntity;

import java.util.Date;

public class Token extends BaseEntity {
    private String hash;
    private Date expireTime;

    public String getHash() {
        return hash;
    }

    public Token setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public Token setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
        return this;
    }
}
