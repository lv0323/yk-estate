package com.lyun.estate.biz.fangcollect.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by robin on 17/5/5.
 */
public enum  FangOrigin implements LabelEnum {
    FY01("第一时间网"),;

    private final String label;
    FangOrigin(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
