package com.lyun.estate.core.supports.types;

public enum SmsType {
    REGISTER("注册"),
    LOGIN("登陆"),
    FORGET_PASSWORD("忘记密码");

    private String desc;

    SmsType(String desc) {
        this.desc = desc;
    }
}
