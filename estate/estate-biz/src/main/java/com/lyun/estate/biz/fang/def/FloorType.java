package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-01-18.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum FloorType {
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
