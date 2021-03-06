package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum FloorType implements LabelEnum {
    LOW("低楼层"),
    MIDDLE("中楼层"),
    HIGH("高楼层"),;
    private final String label;

    FloorType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
