package com.lyun.estate.biz.auth.sms;

import com.lyun.estate.core.supports.types.SmsType;

public class SmsCode {
    private String smsId;
    private String mobile;
    private String code;
    private String serial;
    private SmsType type;

    public String getSmsId() {
        return smsId;
    }

    public SmsCode setSmsId(String smsId) {
        this.smsId = smsId;
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
}
