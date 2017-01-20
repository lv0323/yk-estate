package com.lyun.estate.biz.information.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum InfoBusinessType {
    COMMUNITY_INFO("小区动态"),
    COMMUNITY_MONTHLY_REPORT("小区月报"),
    NOTICE("公告"),
    ;

    private final String label;

    InfoBusinessType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
