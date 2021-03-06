package com.lyun.estate.biz.fang.def;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum PriceUnit implements LabelEnum {
    YUAN("元"),
    WAN("万元"),
    YUAN_D("元/天"),
    YUAN_M("元/月"),
    YUAN_Q("元/季"),
    YUAN_Y("元/年"),;

    private final String label;

    PriceUnit(String label) {
        this.label = label;
    }

    public static List<PriceUnit> getByBizType(BizType bizType) {
        if (bizType == BizType.RENT) {
            return Lists.newArrayList(YUAN, YUAN_D, YUAN_M, YUAN_Q, YUAN_Y);
        } else if (bizType == BizType.SELL) {
            return Lists.newArrayList(WAN);
        } else {
            return Lists.newArrayList();
        }
    }

    public String getLabel() {
        return label;
    }
}
