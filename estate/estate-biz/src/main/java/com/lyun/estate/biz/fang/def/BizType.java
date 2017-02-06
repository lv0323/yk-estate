package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-18.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
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
