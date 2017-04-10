package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum Permission implements LabelEnum {
    VIEW_CONTACT("查看房东信息", PermissionTarget.FANG),
    MODIFY_CONTACT("修改房东信息", PermissionTarget.FANG),
    UNBIND_DEVICE("解绑设备", PermissionTarget.COMPANY),;

    private final String label;
    private final PermissionTarget target;

    Permission(String label, PermissionTarget target) {
        this.label = label;
        this.target = target;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public PermissionTarget getTarget() {
        return target;
    }

}
