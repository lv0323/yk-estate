package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum ElevatorFilter {
    HAS_ELEVATOR("有电梯"),
    NO_ELEVATOR("无电梯"),;
    private final String label;

    ElevatorFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
