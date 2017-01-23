package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum HouseSource {
    VISIT("来访"),
    CALLING("来电"),
    ONLINE("网络"),
    AGENCY("中介"),;
    private final String label;

    HouseSource(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
