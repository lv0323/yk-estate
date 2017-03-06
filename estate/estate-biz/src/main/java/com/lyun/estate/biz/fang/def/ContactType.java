package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public enum ContactType implements LabelEnum {
    MOBILE("手机"),
    QQ("QQ"),
    WECHAT("微信"),
    PHONE("座机"),
    EMAIL("邮箱"),;
    private final String label;

    ContactType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
