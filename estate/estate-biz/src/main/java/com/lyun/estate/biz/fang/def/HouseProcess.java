package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-22.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum HouseProcess {
    DELEGATE("委托"),
    PUBLISH("上架"),
    UN_PUBLISH("下架"),
    SUCCESS("成交"),;
    private String label;

    HouseProcess(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public HouseProcess setLabel(String label) {
        this.label = label;
        return this;
    }
}
