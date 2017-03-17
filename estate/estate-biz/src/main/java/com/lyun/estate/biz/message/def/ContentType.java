package com.lyun.estate.biz.message.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-16.
 */
public enum ContentType implements LabelEnum {
    FANG_SUMMARY("房源概况"),
    FANG_PROCESS("房源动态"),
    NOTICE("公告"),
    XIAO_QU_REPORT("小区月报"),;

    private final String label;

    ContentType(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
