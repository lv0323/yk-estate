package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-06.
 */
public enum Permission implements LabelEnum {
    //fang
    CREATE_FANG("新增房源", PermissionDefine.Category.FANG),
    LIST_FANG_SELL("出售", PermissionDefine.Category.FANG),
    LIST_FANG_RENT("出租", PermissionDefine.Category.FANG),
    VIEW_SELL_CONTACT_LIMIT("出售房源每天看业主次数", PermissionDefine.Category.FANG),
    VIEW_RENT_CONTACT_LIMIT("出租房源每天看业主次数", PermissionDefine.Category.FANG),
    NOT_FOLLOW_SELL("出售看业主不必写跟进", PermissionDefine.Category.FANG),
    NOT_FOLLOW_RENT("出租看业主不必写跟进", PermissionDefine.Category.FANG),
    DEL_FANG_IMG_SHI_JING("删除、调整实景图", PermissionDefine.Category.FANG),
    DEL_FANG_IMG_HU_XING("删除、调整户型图", PermissionDefine.Category.FANG),
    DEL_FANG_IMG_CERTIF("删除、调整房产证照", PermissionDefine.Category.FANG),
    DEL_FANG_IMG_ATTORNEY("删除、调整委托书照", PermissionDefine.Category.FANG),
    DEL_FANG_IMG_ID_CARD("删除、调整业主身份证照", PermissionDefine.Category.FANG),
    DEL_FANG_CONTACT("修改业主信息", PermissionDefine.Category.FANG),
    FANG_PUBLISH("上架房源", PermissionDefine.Category.FANG),
    FANG_PAUSE("暂缓房源", PermissionDefine.Category.FANG),
    FANG_UN_PUBLISH("下架房源", PermissionDefine.Category.FANG),
    FANG_RE_PUBLISH("上架已下架的房源", PermissionDefine.Category.FANG),
    FANG_APPLY_PUBLIC("申请发布外网", PermissionDefine.Category.FANG),
    FANG_CONFIRM_PUBLIC("确认发布外网", PermissionDefine.Category.FANG),
    FANG_REJECT_PUBLIC("拒绝发布外网", PermissionDefine.Category.FANG),
    FANG_UNDO_PUBLIC("撤销发布外网", PermissionDefine.Category.FANG),
    UPDATE_FANG_BASE("修改房源基本信息", PermissionDefine.Category.FANG),
    UPDATE_FANG_EXT("修改房源配套信息", PermissionDefine.Category.FANG),
    MODIFY_FANG_INFO("修改房源信息（图片、跟进、勘察、外网描述）", PermissionDefine.Category.FANG),
    VIEW_FANG_CONTACT("查看业主信息", PermissionDefine.Category.FANG),
    MODIFY_FANG_CONTACT("修改业主信息", PermissionDefine.Category.FANG),

    //xiaoqu
    CREATE_XIAO_QU("创建小区", PermissionDefine.Category.XIAO_QU),
    MODIFY_XIAO_QU("修改小区信息", PermissionDefine.Category.XIAO_QU),
    DEL_XIAO_QU("删除小区", PermissionDefine.Category.XIAO_QU),
    CREATE_BUILDING("创建栋座", PermissionDefine.Category.XIAO_QU),
    MODIFY_BUILDING("修改栋座信息", PermissionDefine.Category.XIAO_QU),
    DEL_BUILDING("删除栋座", PermissionDefine.Category.XIAO_QU),

    //组织架构
    ORG_MANAGEMENT("组织架构管理", PermissionDefine.Category.ORGANIZATION),
    UNBIND_DEVICE("解绑设备", PermissionDefine.Category.ORGANIZATION),

    //公司
    PERMISSION_MANAGEMENT("权限管理与模块设置", PermissionDefine.Category.COMPANY),
    VIEW_AUDIT_LOG("查看业务日志", PermissionDefine.Category.COMPANY),

    //加盟管理
    CREATE_FRANCHISEE("新增加盟信息", PermissionDefine.Category.FRANCHISEE),
    LIST_FRANCHISEE("查看加盟信息", PermissionDefine.Category.FRANCHISEE),
    MODIFY_FRANCHISEE("修改加盟信息", PermissionDefine.Category.FRANCHISEE),

    //运营管理
    OP_MANAGE_XY("信誉平台管理", PermissionDefine.Category.OPERATION),

    //页面模块
    P_FANG("房源管理", PermissionDefine.Category.PAGE),
    P_FANG_LIST("房源列表", PermissionDefine.Category.PAGE),
    P_FANG_NEW("新增房源", PermissionDefine.Category.PAGE),
    P_FANG_FOLLOW("房源跟进", PermissionDefine.Category.PAGE),
    P_FANG_CHECK("房源勘查", PermissionDefine.Category.PAGE),
    P_SHOWING("带看管理", PermissionDefine.Category.PAGE),
    P_SHOWING_LIST("房源带看", PermissionDefine.Category.PAGE),
    P_CONTRACT("合同管理", PermissionDefine.Category.PAGE),
    P_CONTRACT_LIST("成交管理", PermissionDefine.Category.PAGE),
    P_ORG("组织架构", PermissionDefine.Category.PAGE),
    P_ORG_DEPT("部门管理", PermissionDefine.Category.PAGE),
    P_ORG_POSITION("岗位管理", PermissionDefine.Category.PAGE),
    P_ORG_EMPLOYEE("员工管理", PermissionDefine.Category.PAGE),
    P_CONFIG("系统设置", PermissionDefine.Category.PAGE),
    P_CONFIG_AUDIT("操作日志", PermissionDefine.Category.PAGE),
    P_CONFIG_HOUSE_DICT("楼盘字典", PermissionDefine.Category.PAGE),
    P_CONFIG_PAGE("岗位模块", PermissionDefine.Category.PAGE),
    P_CONFIG_PERMISSION("权限设置", PermissionDefine.Category.PAGE),
    P_FRANCHISEE("加盟事业管理", PermissionDefine.Category.PAGE),
    P_FRANCHISEE_C("渠道加盟", PermissionDefine.Category.PAGE),
    P_FRANCHISEE_SS("单店加盟", PermissionDefine.Category.PAGE),
    P_FRANCHISEE_RA("区域代理加盟", PermissionDefine.Category.PAGE),
    P_FANG_COLLECTION("房源采集", PermissionDefine.Category.PAGE),
    P_FANG_COLLECTION_POOL("房源池", PermissionDefine.Category.PAGE),
    P_OPERATION("运营管理", PermissionDefine.Category.PAGE),
    P_OPERATION_XY("信誉平台", PermissionDefine.Category.PAGE),

    //公司性质
    CT_YK("盈科", PermissionDefine.Category.COMPANY_TYPE),
    CT_CHANNEL("渠道", PermissionDefine.Category.COMPANY_TYPE),
    CT_SS("单店", PermissionDefine.Category.COMPANY_TYPE),
    CT_RA("区域加盟", PermissionDefine.Category.COMPANY_TYPE),;


    private final String label;
    private final PermissionDefine.Category category;

    Permission(String label, PermissionDefine.Category category) {
        this.label = label;
        this.category = category;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public PermissionDefine.Category getCategory() {
        return category;
    }
}
