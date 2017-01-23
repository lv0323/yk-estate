package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseLevel {
    A("A"),
    B("B"),
    C("C"),
    D("D"),;

    private final String label;

    HouseLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
