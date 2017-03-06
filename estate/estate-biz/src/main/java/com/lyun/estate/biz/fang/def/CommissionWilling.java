package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum CommissionWilling implements LabelEnum {
    NEGOTIATE("商议"),
    C_10("全佣"),
    C_0("不给佣"),
    C_9("9折佣"),
    C_8("8折佣"),
    C_7("7折佣"),
    C_6("6折佣"),
    C_5("5折佣"),
    C_4("4折佣"),
    C_3("3折佣"),
    C_2("2折佣"),
    C_1("1折佣"),;
    private final String label;

    CommissionWilling(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
