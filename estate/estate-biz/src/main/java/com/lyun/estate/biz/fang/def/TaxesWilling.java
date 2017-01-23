package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public enum TaxesWilling {
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
