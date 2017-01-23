package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum PropertyType {
    SP("商品房"),
    JT("集体房"),
    FG("房改房"),
    XJ("限价房"),
    JJSY("经济适用房"),
    JC("军产房"),;
    private final String label;

    PropertyType(String label) {
        this.label = label;
    }


    public String getLabel() {
        return label;
    }


}
