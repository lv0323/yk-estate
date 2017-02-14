package com.lyun.estate.biz.position.def;

/**
 * Created by Jeffrey on 2017-02-13.
 */
public enum PositionType {
    BUSINESS("业务"),
    ADMIN("管理员"),
    DEPT_M("店管"),
    REGION_M("区管"),
    OPERATION("运营"),
    FINANCIAL("财务"),
    FUNCTIONAL("职能部门"),;

    private final String label;

    PositionType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
