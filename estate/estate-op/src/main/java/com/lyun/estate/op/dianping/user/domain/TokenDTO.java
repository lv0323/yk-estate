package com.lyun.estate.op.dianping.user.domain;

/**
 * Created by localuser on 2017/5/17.
 */
public class TokenDTO {
    public String getToken() {
        return token;
    }

    public TokenDTO setToken(String token) {
        this.token = token;
        return this;
    }

    String token;
}
