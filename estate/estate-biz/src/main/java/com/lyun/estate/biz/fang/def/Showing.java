package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum Showing implements LabelEnum {
    ASK("预约"),
    HAS_KEY("有钥"),
    BORROW_KEY("借钥"),
    ANY_TIME("随时"),;
    private final String label;

    Showing(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }
}
