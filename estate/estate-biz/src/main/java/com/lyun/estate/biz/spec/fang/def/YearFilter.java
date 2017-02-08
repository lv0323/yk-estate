package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-02-08.
 */
public enum YearFilter {
    Y_5_M("5年以内"),
    A_10_M("10年以内"),
    A_15_M("15年以内"),
    A_20_M("20年以内"),
    A_20_P("20年以上"),;

    private final String label;

    YearFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
