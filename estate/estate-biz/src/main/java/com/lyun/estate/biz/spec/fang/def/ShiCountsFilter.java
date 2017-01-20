package com.lyun.estate.biz.spec.fang.def;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum ShiCountsFilter {
    SHI_1("一室"),
    SHI_2("二室"),
    SHI_3("三室"),
    SHI_4("四室"),
    SHI_5("五室"),
    SHI_5_P("五室以上"),;

    private String label;

    ShiCountsFilter(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public ShiCountsFilter setLabel(String label) {
        this.label = label;
        return this;
    }
}
