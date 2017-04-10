package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum GrantScope implements LabelEnum {
    SELF("本人"),
    DEPT("本部门"),
    COMPANY("本公司"),;

    private final String label;

    GrantScope(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
