package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum BizType {
    RENT("租"),
    SELL("售");

    private final String label;

    BizType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
