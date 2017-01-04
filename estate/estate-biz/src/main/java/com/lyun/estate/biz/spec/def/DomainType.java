package com.lyun.estate.biz.spec.def;

/**
 * Created by Jeffrey on 2017-01-03.
 */
public enum DomainType {
    XIAO_QU("小区"),
    FANG("房");

    private final String label;

    DomainType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
