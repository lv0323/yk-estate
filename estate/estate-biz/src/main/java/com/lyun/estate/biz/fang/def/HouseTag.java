package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseTag {
    ONLY("唯一"),
    OVER_2("满二年"),
    OVER_5("满五年"),
    HAS_KEY("有钥匙"),
    ANY_TIME("随时看房")
    ;

    private final String label;

    HouseTag(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
