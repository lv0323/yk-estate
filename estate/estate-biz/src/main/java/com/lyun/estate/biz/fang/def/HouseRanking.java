package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseRanking {
    A("A"),
    B("B"),
    C("C"),;

    private final String label;

    HouseRanking(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
