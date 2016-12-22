package com.lyun.estate.core.supports.resources;

public class SmsCode {
    private String mobile;
    private String code;
    private String serial;

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
}
