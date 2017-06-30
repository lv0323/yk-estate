package com.lyun.estate.core.supports.exceptions;

/**
 * Created by Jeffrey on 16/5/6.
 */
public enum ExCode {
    //default
    DEFAULT_EXCEPTION("服务器错误"),
    //common
    PARAM_NULL("参数{}值为空"),
    PARAM_ILLEGAL("参数{}值为{}，不合法"),
    CREATE_FAIL("{}新增失败,{}"),
    UPDATE_FAIL("{}更新失败,{}"),
    NOT_FOUND("根据{}未找到{}"),
    EXISTED_ERROR("{}已经存在,{}"),
    //login
    LOGIN_NEED_CAPTCHA("登录需要图片验证码"),
    //token
    TOKEN_INVALID("令牌失效"),
    TOKEN_REFRESH_INVALID("无效的刷新令牌"),
    TOKEN_REFRESH_END("刷新令牌已到结束时间"),
    //oss
    OSS_EXCEPTION("OSS错误"),
    OSS_FILE_NOT_IMAGE("文件不是图片类型"),
    //employee
    EMPLOYEE_IS_BOSS("该用户为Boss,不能删除"),
    EMPLOYEE_NOT_FOUND("无效用户"),
    EMPLOYEE_ACTIVE("该用户已激活"),
    EMPLOYEE_NOT_ACTIVE("该用户未激活"),
    EMPLOYEE_NO_SUGAR("找不到Sugar"),
    EMPLOYEE_WRONG_PASSWORD("密码错误"),
    EMPLOYEE_LOGIN_FAIL("登录失败"),
    EMPLOYEE_MOBILE_EXIST("用户手机号已存在"),
    //company
    COMPANY_LOCKED("公司被冻结"),
    COMPANY_EXPIRED("公司授权已到截止日期"),
    COMPANY_ABBR_OVER_COUNT("公司简称不可用"),
    COMPANY_CREATE_PARENT_ERROR("您所在公司无法创建该类型加盟商"),
    COMPANY_BOSS_NOT_IN("负责人非公司成员"),
    //position
    POSITION_HAS_EMPLOYEE("该岗位下有员工，不能删除"),
    //department
    DEPT_IS_PRIMARY("该部门为公司总部，不能删除或调动"),
    DEPT_HAS_EMPLOYEE("该部门有员工，不能删除"),
    DEPT_HAS_CHILD("该部门有子部门，不能删除"),
    DEPT_NULL_PARENT("父部门不能为空"),
    DEPT_ILLEGAL_PARENT("父部门不合法"),
    DEPT_INVALID_PARENT("父部门不能调整为自身子部门的下级部门，请重新选择调整的部门数据"),
    //licence
    LICENCE_NULL("授权编号为空"),
    LICENCE_LOCATION_ERROR("楼盘信息错误"),
    LICENCE_HOUSE_EXISTED("该房源已存在,授权编号：{}"),
    //fang_mgt
    FANG_MGT_ERROR_DEPT("所选部门不合法"),
    FANG_MGT_ERROR_EMPLOYEE("所选员工不合法"),
    FANG_MGT_DELETED("所选房源不存在或者已删除"),
    //image
    CUSTOM_TYPE_NOT_SUPPORTED("业务类型:{} 不支持"),
    //favorite
    FAVORITE_TYPE_NOT_SUPPORTED("关注实体类型不支持"),
    //file
    FILE_NOT_EXIST("该文件不存在"),
    FILE_IMG_SIZE_ERROR("图片尺寸不是{}*{}"),
    //permission
    PERMISSION_ERROR("无权限进行该操作"),
    PERMISSION_OWNERSHIP_ERROR("没有操作对象的权限:{}.{}"),
    PERMISSION_NOT_FOUND("没有权限:{}"),
    PERMISSION_OUT_LIMIT("{},超过限制"),
    PERMISSION_CATEGORY_ERROR("权限类别错误"),
    PERMISSION_CATEGORY_NOT_SUPPORT("不支持设置权限类别:{}"),
    PERMISSION_EMPTY("没有需要校验的权限"),
    PERMISSION_COMPANY_ERROR("无权操作该商户"),
    PERMISSION_COMPANY_TYPE_NOT_SUPPORT("所属商户类型【{}】无权使用该权限【{}】"),

    //approval
    APPROVAL_APPROVED("该数据已被审批过，审批结果是：{}"),
    APPROVAL_NO_NEED_APPROVE("该数据无需审批"),

    //fang process
    NOT_PUBLISH("该房源不可见"),
    PROCESS_ILLEGAL("房源{}, 状态{}不能变成{}"),
    SUB_PROCESS_ILLEGAL("房源{}, 状态{}-{}不合法"),
    DESC_CORE_NULL("房源{}, 外网描述中核心卖点为空，不能发布"),
    REQUIRE_DOCS_NULL("未上传文件：{}, 不能发布"),
    //contract
    CONTRACT_FOR_FANG_EXIST("该房源的合同已存在"),
    CONTRACT_FOR_FANG_SUCCEED("该房源的合同已成交"),

    //event
    EVENT_PRODUCE_ERROR("事件发布错误"),
    //ip check
    IP_AND_DEVICE_ERROR("ip及设备校验错误"),

    //json
    JSON_ERROR("JSON对象{},转换错误:{}"),

    //op dianping
    OP_DIANPING_CORP_APPROVED("{}已被审批过,不能再次审批"),;

    private final String messageTemplate;

    ExCode(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}
