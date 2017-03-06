package com.lyun.estate.biz.favorite.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-02.
 */
public enum FavoriteType implements LabelEnum {
    XIAO_QU("小区"),
    FANG_SELL("二手房"),
    FANG_RENT("租房"),;
    private final String label;

    FavoriteType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
