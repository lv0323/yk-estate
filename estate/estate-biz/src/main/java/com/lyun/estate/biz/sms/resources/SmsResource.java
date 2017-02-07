package com.lyun.estate.biz.sms.resources;

import com.lyun.estate.biz.sms.def.SmsType;

public class SmsResource {
    private String mobile;
    private SmsType type;

    public String getMobile() {
        return mobile;
    }

    public SmsResource setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public SmsType getType() {
        return type;
    }

    public SmsResource setType(SmsType type) {
        this.type = type;
        return this;
    }
}
