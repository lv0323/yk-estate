package com.lyun.estate.biz.file.def;

public enum OwnerType {
    XIAOQU("小区");

    private String label;

    OwnerType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
