package com.lyun.estate.biz.message.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum MessageStatus {
    UNREAD("未读"),
    READ("已读"),
    ;

    private final String label;

    MessageStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
