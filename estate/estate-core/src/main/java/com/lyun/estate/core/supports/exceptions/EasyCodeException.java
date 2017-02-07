package com.lyun.estate.core.supports.exceptions;

public class EasyCodeException extends EstateException {
    private String code;

    public EasyCodeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public EasyCodeException setCode(String code) {
        this.code = code;
        return this;
    }
}
