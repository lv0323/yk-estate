package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-02-22.
 */
public enum InfoOwnerReason implements LabelEnum {
    CREATE("录入"),
    KEY("钥匙"),
    DELEGATE_ONLY("独家"),
    CHECK("勘查"),
    FOLLOW("维护"),
    OTHER("其他"),;
    private final String label;

    InfoOwnerReason(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
