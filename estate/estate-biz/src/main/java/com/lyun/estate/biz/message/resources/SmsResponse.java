package com.lyun.estate.biz.message.resources;

public class SmsResponse {
    private String mobile;
    private String smsId;
    private String serial;

    public String getMobile() {
        return mobile;
    }

    public SmsResponse setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getSmsId() {
        return smsId;
    }

    public SmsResponse setSmsId(String smsId) {
        this.smsId = smsId;
        return this;
    }

    public String getSerial() {
        return serial;
    }

    public SmsResponse setSerial(String serial) {
        this.serial = serial;
        return this;
    }
}
