package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseLevel implements LabelEnum {
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
