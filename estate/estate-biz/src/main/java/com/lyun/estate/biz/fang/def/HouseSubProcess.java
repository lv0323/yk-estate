package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum HouseSubProcess implements LabelEnum {
    PRE_PUBLIC("待发布外网"),
    PUBLIC("已发布外网");
    private final String label;

    HouseSubProcess(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
