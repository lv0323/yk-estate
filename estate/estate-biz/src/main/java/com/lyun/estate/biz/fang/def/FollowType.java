package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-02-24.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum FollowType {
    QQ("QQ");

    private final String label;

    FollowType(String label) {
        this.label = label;
    }
}
