package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-18.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum HeatingType {
    NONE("无供暖"),
    PROVIDED("集体供暖"),
    SELF_HELP("自供暖"),;
    private final String label;

    HeatingType(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }
}
