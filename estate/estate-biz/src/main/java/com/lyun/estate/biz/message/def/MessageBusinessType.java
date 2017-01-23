package com.lyun.estate.biz.message.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum MessageBusinessType {
    C_MS("小区动态"),
    C_M_REPORT("小区月报"),
    NOTICE("公告"),
    ;

    private final String label;

    MessageBusinessType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
