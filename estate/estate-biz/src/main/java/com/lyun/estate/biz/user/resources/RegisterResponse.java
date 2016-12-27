package com.lyun.estate.biz.user.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lyun.estate.core.supports.types.Constant;

import java.util.Date;

public class RegisterResponse {
    private boolean registered;
    private String jwt;
    @JsonFormat(pattern = Constant.JSON_DATE_FORMAT)
    private Date jwtExpireTime;

    public boolean isRegistered() {
        return registered;
    }

    public RegisterResponse setRegistered(boolean registered) {
        this.registered = registered;
        return this;
    }

    public String getJwt() {
        return jwt;
    }

    public RegisterResponse setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public Date getJwtExpireTime() {
        return jwtExpireTime;
    }

    public RegisterResponse setJwtExpireTime(Date jwtExpireTime) {
        this.jwtExpireTime = jwtExpireTime;
        return this;
    }
}
