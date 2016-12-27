package com.lyun.estate.biz.user.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyun.estate.core.utils.Constant;

import java.util.Date;

public class TokenResponse {
    private String jwt;
    @JsonFormat(pattern = Constant.JSON_DATE_FORMAT)
    private Date jwtExpireTime;

    public String getJwt() {
        return jwt;
    }

    public TokenResponse setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public Date getJwtExpireTime() {
        return jwtExpireTime;
    }

    public TokenResponse setJwtExpireTime(Date jwtExpireTime) {
        this.jwtExpireTime = jwtExpireTime;
        return this;
    }
}
