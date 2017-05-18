package com.lyun.estate.op.corp.entity;

/**
 * Created by localuser on 2017/5/17.
 */
public class WxLoginToken {
    public String getToken() {
        return token;
    }

    public WxLoginToken setToken(String token) {
        this.token = token;
        return this;
    }

    String token;
}
