package com.lyun.estate.biz.support.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum BizType implements LabelEnum {
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
