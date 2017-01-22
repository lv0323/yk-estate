package com.lyun.estate.biz.file.def;

public enum CustomType {
    SHIJING("实景"),
    HUXING("户型"),
    AVATAR("头像");

    private String label;

    CustomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
