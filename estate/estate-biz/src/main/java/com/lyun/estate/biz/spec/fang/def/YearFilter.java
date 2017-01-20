package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum YearFilter {
    Y_5_M("5年以内"),
    Y_10_M("10年以内"),
    Y_15_M("15年以内"),
    Y_20_M("20年以内"),
    Y_20_P("20年以上"),;

    private String label;

    YearFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public YearFilter setLabel(String label) {
        this.label = label;
        return this;
    }
}
