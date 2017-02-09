package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-02-08.
 */
public enum HouseTypeFilter {
    ZZ("住宅"),
    GY("公寓"),
    BS("别墅"),
    QT("其他"),;

    private final String label;

    HouseTypeFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
