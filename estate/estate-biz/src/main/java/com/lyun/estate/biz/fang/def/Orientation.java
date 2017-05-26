package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum Orientation implements LabelEnum {
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

    public static Orientation parse(String label) {
        return Arrays.stream(Orientation.values()).filter(t -> Objects.equals(t.getLabel(), label)).findFirst().orElse(null);
    }
}
