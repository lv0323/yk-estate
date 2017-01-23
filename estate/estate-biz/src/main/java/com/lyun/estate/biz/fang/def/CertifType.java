package com.lyun.estate.biz.fang.def;

/**
 * Created by Jeffrey on 2017-01-22.
 */
public enum CertifType {
    FCZ("房产证"),
    GFHT("购房合同"),
    GFFP("购房发票"),
    DYHT("抵押合同"),
    RGS("认购书"),
    YSHE("预售合同"),
    HQXY("回迁协议"),
    SJSJ("收件收据"),
    WCZ("未出证"),;
    private final String label;

    CertifType(String label) {
        this.label = label;
    }

    public String getLabel() {

        return label;
    }
}
