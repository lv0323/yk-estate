package com.lyun.estate.biz.support.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-09.
 */
public enum IdentitySource implements LabelEnum {
    ID_CARD("身份证"),
    PASSPORT("护照"),;

    private final String label;

    IdentitySource(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
