package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum Permission implements LabelEnum {
    CREATE_FANG("新增房源", PermissionDefine.Category.FANG, PermissionDefine.Operation.CREATE),
    LIST_FANG_SELL("出售", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    LIST_FANG_RENT("出租", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    VIEW_FANG_CONTACT("查看业主信息", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    VIEW_SELL_CONTACT_LIMIT("出售房源每天看业主次数", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    VIEW_RENT_CONTACT_LIMIT("出租房源每天看业主次数", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    NOT_FOLLOW_SELL("出售看业主不必写跟进", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    NOT_FOLLOW_RENT("出租看业主不必写跟进", PermissionDefine.Category.FANG, PermissionDefine.Operation.QUERY),
    DEL_FANG_IMG_SHI_JING("删除实景图", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    DEL_FANG_IMG_HU_XING("删除户型图", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    DEL_FANG_IMG_CERTIF("删除房产证", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    DEL_FANG_IMG_ATTORNEY("删除委托书", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    DEL_FANG_IMG_ID_CARD("删除业主身份证", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    DEL_FANG_CONTACT("修改业主信息", PermissionDefine.Category.FANG, PermissionDefine.Operation.DELETE),
    FANG_PUBLISH("上架房源", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_PAUSE("暂缓房源", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_UN_PUBLISH("下架房源", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_RE_PUBLISH("上架已下架的房源", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_APPLY_PUBLIC("申请发布外网", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_CONFIRM_PUBLIC("确认发布外网", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_REJECT_PUBLIC("拒绝发布外网", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    FANG_UNDO_PUBLIC("撤销发布外网", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    UPDATE_FANG_BASE("修改基本信息", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    UPDATE_FANG_EXT("修改配套信息", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),
    MODIFY_FANG_CONTACT("修改业主信息", PermissionDefine.Category.FANG, PermissionDefine.Operation.UPDATE),

    CREATE_XIAO_QU("创建小区", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.CREATE),
    MODIFY_XIAO_QU("修改小区信息", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.UPDATE),
    DEL_XIAO_QU("删除小区", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.DELETE),
    CREATE_BUILDING("创建栋座", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.CREATE),
    MODIFY_BUILDING("修改栋座信息", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.UPDATE),
    DEL_BUILDING("删除栋座", PermissionDefine.Category.XIAO_QU, PermissionDefine.Operation.DELETE),

    UNBIND_DEVICE("解绑设备", PermissionDefine.Category.ORGANIZATION, PermissionDefine.Operation.DELETE),;


    private final String label;
    private final PermissionDefine.Category Category;
    private final PermissionDefine.Operation operation;

    Permission(String label, PermissionDefine.Category Category, PermissionDefine.Operation operation) {
        this.label = label;
        this.Category = Category;
        this.operation = operation;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public PermissionDefine.Category getCategory() {
        return Category;
    }

    public PermissionDefine.Operation getOperation() {
        return operation;
    }
}
