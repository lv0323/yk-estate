package com.lyun.estate.core.supports.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidateException extends RuntimeException {
    private String code;
    private List<ObjectError> objectErrors;

    public ValidateException(String code) {
        this.code = code;
    }

    public ValidateException(String message, String code) {
        super(message);
        this.code = code;
    }

    public ValidateException(String code, List<ObjectError> objectErrors) {
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(String message, String code, List<ObjectError> objectErrors) {
        super(message);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(String message, Throwable cause, String code, List<ObjectError> objectErrors) {
        super(message, cause);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(Throwable cause, String code, List<ObjectError> objectErrors) {
        super(cause);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code, List<ObjectError> objectErrors) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public String getCode() {
        return code;
    }

    public ValidateException setCode(String code) {
        this.code = code;
        return this;
    }

    public List<ObjectError> getObjectErrors() {
        return objectErrors;
    }

    public ValidateException setObjectErrors(List<ObjectError> objectErrors) {
        this.objectErrors = objectErrors;
        return this;
    }
}
