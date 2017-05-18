package com.lyun.estate.biz.audit.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-02-15.
 */
public enum AuditSubject implements LabelEnum {
    LOGIN_OUT("登录/登出日志"),
    FANG_P("增/删房源"),
    FANG_M("修改房源信息"),
    FANG_OWNER("房东信息"),
    ORGANIZATION("组织架构"),
    PERMISSION("权限设置"),
    XIAO_QU_P("增/删小区"),
    XIAO_QU_M("修改小区信息"),
    BUILDING("楼座信息"),
    FRANCHISEE("加盟商");

    private final String label;

    AuditSubject(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
