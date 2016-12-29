package com.lyun.estate.biz.user.resources;

public class RegisterResponse {
    private boolean registered;
    private String jwt;

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

}
