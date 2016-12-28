package com.lyun.estate.biz.auth.captcha;

public class Captcha {
    private int clientId;
    private String id;
    private String code;

    public int getClientId() {
        return clientId;
    }

    public Captcha setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getId() {
        return id;
    }

    public Captcha setId(String id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Captcha setCode(String code) {
        this.code = code;
        return this;
    }
}
