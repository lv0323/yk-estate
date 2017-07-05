package com.lyun.estate.biz.file.def;

public enum CustomType {
    SHI_JING("实景"),
    HU_XING("户型"),
    CERTIF("房产证"),
    ATTORNEY("委托书"),
    OWNER_ID_CARD("房东身份证"),
    AVATAR("头像"),
    APK("APK"),
    NEWS_IMG("新闻标题"),
    LP_VIDEO("楼盘视频"),;

    private String label;

    CustomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
