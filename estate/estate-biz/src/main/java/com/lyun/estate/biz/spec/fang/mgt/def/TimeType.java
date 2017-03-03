package com.lyun.estate.biz.spec.fang.mgt.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-02-23.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum TimeType {
    CREATE_TIME("录入日期"),
    DELEGATE_TIME("委托日期"),
    PUBLISH_TIME("发布日期"),
    FOLLOW_TIME("最后跟进日期");

    private final String label;

    TimeType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
