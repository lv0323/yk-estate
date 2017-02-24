package com.lyun.estate.biz.spec.fang.rest.def;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum ElevatorFilter {
    HAS("有电梯"),
    HAS_NOT("无电梯"),;
    private final String label;

    ElevatorFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
