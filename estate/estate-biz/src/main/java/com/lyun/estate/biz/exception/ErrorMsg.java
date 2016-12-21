package com.lyun.estate.biz.exception;

import java.util.ArrayList;
import java.util.List;

public class ErrorMsg {

    private final String code;
    private final String msg;

    private List<String> fieldErrors;

    public ErrorMsg(String msg) {
        this(null, msg);
    }

    public ErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorMsg(String code, String msg, List<String> fieldErrors) {
        this.code = code;
        this.msg = msg;
        this.fieldErrors = fieldErrors;
    }

    public void add(String msg) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(msg);
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
