package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum Permission implements LabelEnum {
    VIEW_CONTACT("查看房东信息", PermissionTarget.FANG),
    MODIFY_CONTACT("修改房东信息", PermissionTarget.FANG),
    PUBLISH("上架房源", PermissionTarget.FANG),
    PAUSE("暂缓房源", PermissionTarget.FANG),
    UN_PUBLISH("下架房源", PermissionTarget.FANG),
    RE_PUBLISH("上架已下架的房源", PermissionTarget.FANG),
    APPLY_PUBLIC("申请发布外网", PermissionTarget.FANG),
    CONFIRM_PUBLIC("确认发布外网", PermissionTarget.FANG),
    REJECT_PUBLIC("拒绝发布外网", PermissionTarget.FANG),
    UNDO_PUBLIC("撤销发布外网", PermissionTarget.FANG),
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
