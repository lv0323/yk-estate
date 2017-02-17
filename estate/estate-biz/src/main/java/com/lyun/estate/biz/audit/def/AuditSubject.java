package com.lyun.estate.biz.audit.def;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lyun.estate.core.supports.resolvers.LabelEnumSerializer;

/**
 * Created by Jeffrey on 2017-02-15.
 */
@JsonSerialize(using = LabelEnumSerializer.class)
public enum AuditSubject {
    LOGIN_OUT("登录/登出日志"),
    FANG_A_R("增加/删除房源"),
    FANG_M("修改房源信息"),
    FANG_OWNER("查询房主信息"),
    ORGANIZATION("组织架构"),
    PRIVILEGE("权限设置"),
    XIAO_QU_A_R("增加/删除小区"),
    XIAO_QU_M("修改小区信息"),;

    private final String label;

    AuditSubject(String label) {
        this.label = label;
    }
}
