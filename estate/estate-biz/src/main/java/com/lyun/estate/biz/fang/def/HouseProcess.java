package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum HouseProcess implements LabelEnum {
    DELEGATE("审核中"),
    PUBLISH("上架"),
    PAUSE("暂缓"),
    UN_PUBLISH("下架"),
    SUCCESS("成交"),;
    private final String label;

    HouseProcess(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
