package com.lyun.estate.biz.fang.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-02-24.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum FollowType {
    CALL_IN("来电"),
    CALL_OUT("去电"),
    QQ("QQ"),
    WE_CHAT("微信"),
    EMAIL("邮件"),
    OTHER("其他"),;

    private final String label;

    FollowType(String label) {
        this.label = label;
    }
}
