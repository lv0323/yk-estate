package com.lyun.estate.biz.support.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

public enum Gender implements LabelEnum {
    M("男"),
    F("女");

    private String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
