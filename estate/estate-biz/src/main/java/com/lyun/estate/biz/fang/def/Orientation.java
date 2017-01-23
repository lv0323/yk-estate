package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum Orientation {
    E("东"),
    S("南"),
    W("西"),
    N("北"),
    SN("南北"),
    EW("东西"),
    ES("东南"),
    WS("西南"),
    EN("东北"),
    WN("西北"),;
    private final String label;

    Orientation(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
