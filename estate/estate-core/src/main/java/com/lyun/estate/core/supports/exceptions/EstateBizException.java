package com.lyun.estate.core.supports.exceptions;

public class EstateBizException extends EstateException {
    private String code;

    public EstateBizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public EstateBizException setCode(String code) {
        this.code = code;
        return this;
    }
}
