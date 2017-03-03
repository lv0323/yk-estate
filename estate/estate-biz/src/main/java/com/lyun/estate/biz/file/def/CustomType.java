package com.lyun.estate.biz.file.def;

public enum CustomType {
    SHI_JING("实景"),
    HU_XING("户型"),
    CERTIF("证件"),
    AVATAR("头像"),
    APK("APK"),;

    private String label;

    CustomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
