package com.lyun.estate.biz.auth.sms;

import com.lyun.estate.biz.sms.def.SmsType;

public class SmsCode {
    private String id;
    private String mobile;
    private String code;
    private String serial;
    private SmsType type;
    private int clientId;

    public String getId() {
        return id;
    }

    public SmsCode setId(String id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public SmsCode setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getCode() {
        return code;
    }

    public SmsCode setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSerial() {
        return serial;
    }

    public SmsCode setSerial(String serial) {
        this.serial = serial;
        return this;
    }

    public SmsType getType() {
        return type;
    }

    public SmsCode setType(SmsType type) {
        this.type = type;
        return this;
    }

    public int getClientId() {
        return clientId;
    }

    public SmsCode setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }
}
