package com.lyun.estate.biz.message.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by jesse on 2017/1/20.
 */
public enum MessageStatus implements LabelEnum {
    UNREAD("未读"),
    READ("已读"),;

    private final String label;

    MessageStatus(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
