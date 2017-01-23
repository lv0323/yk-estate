package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum HouseStatus {
    SELL_EMPTY("出售(空房)"),
    SELL_RESIDE("出售(业主住)"),
    SELL_RENT("出售(租客住)"),
    RENT_EMPTY("出租(空房)"),
    RENT_RESIDE("出租(业主住)"),
    RENT_RENT("出租(租客住)"),
    RESIDE("自住"),
    NEW("全新"),
    UNKNOWN("未知"),
    ;

    private final String label;

    HouseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }
}
