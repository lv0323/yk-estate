package com.lyun.estate.biz.user.resources;

public class RegisterResponse {
    private boolean registered;
    private String token;

    public boolean isRegistered() {
        return registered;
    }

    public RegisterResponse setRegistered(boolean registered) {
        this.registered = registered;
        return this;
    }

    public String getToken() {
        return token;
    }

    public RegisterResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
