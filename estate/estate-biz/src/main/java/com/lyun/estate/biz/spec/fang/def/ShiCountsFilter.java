package com.lyun.estate.biz.spec.fang.def;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-20.
 */
public enum ShiCountsFilter {
    SHI_1("一室", Lists.newArrayList(1)),
    SHI_2("二室", Lists.newArrayList(2)),
    SHI_3("三室", Lists.newArrayList(3)),
    SHI_4("四室", Lists.newArrayList(4)),
    SHI_5("五室", Lists.newArrayList(5)),
    SHI_5_P("五室以上", Lists.newArrayList(6, 7, 8, 9)),;

    private final String label;
    private final List<Integer> counts;

    ShiCountsFilter(String label, List<Integer> counts) {
        this.label = label;
        this.counts = counts;
    }

    public String getLabel() {
        return label;
    }

    public List<Integer> getCounts() {
        return counts;
    }
}
