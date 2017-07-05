package com.lyun.estate.biz.sms.def;

import java.util.ArrayList;
import java.util.List;

public enum SmsType {
    REGISTER("注册"),
    LOGIN("登陆"),
    FORGET_PASSWORD("忘记密码"),
    EMPLOYEE_ACTIVE("员工激活");

    private static List<String> keys = new ArrayList<>();

    static {
        keys.add(SmsType.REGISTER.name());
        keys.add(SmsType.LOGIN.name());
        keys.add(SmsType.FORGET_PASSWORD.name());
        keys.add(SmsType.EMPLOYEE_ACTIVE.name());
    }

    private String desc;

    SmsType(String desc) {
        this.desc = desc;
    }

    public static List<String> getKeys() {
        return keys;
    }
}
