package com.lyun.estate.core.supports.exceptions;

/**
 * Created by Jeffrey on 16/5/6.
 */
public enum ExCode {
    DEFAULT_EXCEPTION("服务器错误"),
    PARAM_NULL("参数{}值为空"),
    PARAM_ILLEGAL("参数{}值为{}，不合法"),
    OSS_EXCEPTION("OSS错误"),
    NOT_ACTIVE_EMPLOYEE("未激活"),
    NO_SUGAR("找不到Sugar"),
    WRONG_PASSWORD("密码错误"),
    HAS_EMPLOYEE("该岗位下有员工，不能删除"),
    CREATE_FAIL("{}新增失败,{}"),
    UPDATE_FAIL("{}更新失败,{}"),
    NOT_FOUND("根据{}未找到{}"),
    NULL_PARENT("父部门不能为空"),
    INVALID_PARENT("父部门不能调整为自身子部门的下级部门，请重新选择调整的部门数据"),
    CANT_SALT("获取Salt失败"),
    LOGIN_FAIL("登录失败"),
    JSON_ERROR("JSON对象{},转换错误:{}"),
    COMPANY_LOCKED("公司被冻结"),
    COMPANY_EXPIRED("公司授权已到截止日期"),
    ;

    private final String messageTemplate;

    ExCode(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}
