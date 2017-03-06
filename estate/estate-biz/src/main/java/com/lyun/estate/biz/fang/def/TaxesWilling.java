package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public enum TaxesWilling implements LabelEnum {
    PAY_NONE("不付"),
    PAY_OWN("各付"),
    PAY_ALL("全付"),;

    private final String label;

    TaxesWilling(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
