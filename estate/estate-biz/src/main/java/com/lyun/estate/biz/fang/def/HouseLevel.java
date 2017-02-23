package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-18.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum HouseLevel {
    JU("聚焦房"),
    YOU("优质房"),
    PU("普通房");
    private final String label;

    HouseLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
