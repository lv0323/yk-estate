package com.lyun.estate.biz.information.def;

/**
 * Created by jesse on 2017/1/20.
 */
public enum InfoContentType {
    COMMUNITY("小区信息"),
    PHOTO_ARTICLE("图文"),
    ;

    private final String label;

    InfoContentType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
