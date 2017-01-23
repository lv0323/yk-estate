package com.lyun.estate.biz.message.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum MessageContentType {
    FANG("房源"),
    REPORT("报表"),
    PHOTO_ARTICLE("图文"),
    ;

    private final String label;

    MessageContentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
