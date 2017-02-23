package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-22.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum HouseSource {
    VISIT("来访"),
    CALLING("来电"),
    ONLINE("网络"),
    AGENCY("中介"),;
    private final String label;

    HouseSource(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
