package com.lyun.estate.mgt.employee.entity;

import com.lyun.estate.biz.auth.sms.SmsCode;

public class ActiveEntity {

    private SmsCode smsCode;
    private String password;

    public SmsCode getSmsCode() {
        return smsCode;
    }

    public ActiveEntity setSmsCode(SmsCode smsCode) {
        this.smsCode = smsCode;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ActiveEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
