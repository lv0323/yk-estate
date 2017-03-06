package com.lyun.estate.biz.fang.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-01-18.
 */
public enum HouseTag implements LabelEnum {
    ONLY("唯一", false),
    OVER_2("满二年", false),
    OVER_5("满五年", false),
    DECORATE_JING("精装修", false),
    HAS_KEY("有钥匙", false),
    ANY_TIME("随时看房", false),
    NEAR_LINE("近地铁", false);

    private final String label;
    private final boolean needLog;

    HouseTag(String label, boolean needLog) {
        this.label = label;
        this.needLog = needLog;
    }

    HouseTag(String label) {
        this.label = label;
        this.needLog = true;
    }

    public String getLabel() {
        return label;
    }

    public boolean isNeedLog() {
        return needLog;
    }
}
