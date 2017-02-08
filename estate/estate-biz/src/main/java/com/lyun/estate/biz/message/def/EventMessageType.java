package com.lyun.estate.biz.message.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum EventMessageType {
    DIRECT("直接发送"),
    FANG_TRIGGER("房源触发"),
    XIAOQU_TRIGGER("小区触发"),
    REPORT_TRIGGER("月报触发"),
    NOTICE_TRIGGER("公告触发"),
    ;

    private final String label;

    EventMessageType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
