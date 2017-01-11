package com.lyun.estate.biz.spec.common;

/**
 * Created by Jeffrey on 2017-01-03.
 */
public enum DomainType {
    DISTRICT("区域"),
    SUB_DISTRICT("板块"),
    LINE("地铁线"),
    STATION("地铁站"),
    XIAO_QU("小区"),
    FANG("房");

    private final String label;

    DomainType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
