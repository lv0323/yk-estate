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
    HAS_EMPLOYEE("该岗位下有员工，不能删除");


    private final String messageTemplate;

    ExCode(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}
