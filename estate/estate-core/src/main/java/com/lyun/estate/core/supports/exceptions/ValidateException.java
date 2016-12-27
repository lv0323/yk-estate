package com.lyun.estate.core.supports.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidateException extends EstateException {
    private String code;
    private List<ObjectError> objectErrors;

    public ValidateException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ValidateException(String code, List<ObjectError> objectErrors) {
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(String code, String message, List<ObjectError> objectErrors) {
        super(message);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(ExCode exCode, String code, List<ObjectError> objectErrors, Object... args) {
        super(exCode, args);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(Throwable cause, String code, List<ObjectError> objectErrors) {
        super(cause);
        this.code = code;
        this.objectErrors = objectErrors;
    }

    public ValidateException(Throwable cause, ExCode exCode, String code, List<ObjectError> objectErrors, Object... args) {
        super(cause, exCode, args);
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
