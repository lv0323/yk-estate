package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-23.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
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
