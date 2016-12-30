package com.lyun.estate.core.supports.types;

import java.util.ArrayList;
import java.util.List;

public enum SmsType {
    REGISTER("注册"),
    LOGIN("登陆"),
    FORGET_PASSWORD("忘记密码");

    private String desc;
    private static List<String> keys = new ArrayList<>();

    SmsType(String desc) {
        this.desc = desc;
    }

    static {
        keys.add(SmsType.REGISTER.name());
        keys.add(SmsType.LOGIN.name());
        keys.add(SmsType.FORGET_PASSWORD.name());
    }

    public static List<String> getKeys() {
        return keys;
    }
}
