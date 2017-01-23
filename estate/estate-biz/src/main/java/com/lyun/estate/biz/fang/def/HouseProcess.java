package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum HouseProcess {
    DELEGATE("委托"),
    PUBLISH("上架"),
    UN_PUBLISH("下架"),
    SUCCESS("成交"),;
    private String label;

    HouseProcess(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public HouseProcess setLabel(String label) {
        this.label = label;
        return this;
    }
}
