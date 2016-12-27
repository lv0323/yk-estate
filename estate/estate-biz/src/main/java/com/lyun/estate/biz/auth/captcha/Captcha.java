package com.lyun.estate.biz.auth.captcha;

public class Captcha {
    private long clientId;
    private String id;
    private String code;

    public long getClientId() {
        return clientId;
    }

    public Captcha setClientId(long clientId) {
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
