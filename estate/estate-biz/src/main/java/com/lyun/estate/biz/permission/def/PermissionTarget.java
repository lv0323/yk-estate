package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum PermissionTarget implements LabelEnum {
    FANG("房"),
    CUSTOMER("客户"),
    COMPANY("公司"),;

    private final String label;

    PermissionTarget(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
