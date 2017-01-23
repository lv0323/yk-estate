package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum DelegateType {
    ONLY("独家"),
    SIGNED("签约"),
    UNSIGNED("未签约"),
    IN_TIME("限时"),
    TRUST("托管"),;

    private final String label;

    DelegateType(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }
}
