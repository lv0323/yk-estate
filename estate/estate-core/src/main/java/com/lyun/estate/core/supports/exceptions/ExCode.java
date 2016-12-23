package com.lyun.estate.core.supports.exceptions;

/**
 * Created by Jeffrey on 16/5/6.
 */
public enum ExCode {
    DEFAULT_EXCEPTION("服务器错误"),
    PARAM_ILLEGAL("参数{}值为{}，不合法"),;


    private final String messageTemplate;

    ExCode(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }
}
