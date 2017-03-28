package com.lyun.estate.biz.support.def;

/**
 * Created by Jeffrey on 2017-01-03.
 */
public enum DomainType {
    CITY("城市"),
    DISTRICT("区域"),
    SUB_DISTRICT("板块"),
    LINE("地铁线"),
    STATION("地铁站"),
    XIAO_QU("小区"),
    FANG("房"),
    EMPLOYEE("员工"),
    DEPARTMENT("部门"),
    POSITION("岗位"),
    MESSAGE("消息"),
    USER("用户"),
    NEWS("新闻"),;

    private final String label;

    DomainType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
