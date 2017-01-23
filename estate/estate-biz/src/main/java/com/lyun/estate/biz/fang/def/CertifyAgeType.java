package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public enum CertifyAgeType {
    CAT_2_M("未满两年"),
    CAT_2_5("满两年"),
    CAT_5_P("满五年"),
    ;

    private final String label;

    CertifyAgeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
