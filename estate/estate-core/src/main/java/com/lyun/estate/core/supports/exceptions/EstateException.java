package com.lyun.estate.core.supports.exceptions;

import java.util.UUID;

/**
 * Created by henry on 2016/5/5.
 */
public class EstateException extends RuntimeException {

    private ExCode exCode = ExCode.DEFAULT_EXCEPTION;

    private String id;

    public EstateException() {
        super(ExCode.DEFAULT_EXCEPTION.getMessageTemplate());
        id = UUID.randomUUID().toString();
    }

    public EstateException(String message) {
        super(message);
        id = UUID.randomUUID().toString();
    }

    public EstateException(ExCode exCode, Object... args) {
        super(ExceptionUtil.format(exCode, args));
        this.exCode = exCode;
    }

    public EstateException(Throwable cause) {
        super(ExCode.DEFAULT_EXCEPTION.getMessageTemplate(), cause);
    }

    public EstateException(Throwable cause, ExCode exCode, Object... args) {
        super(ExceptionUtil.format(exCode, args), cause);
        this.exCode = exCode;
    }

    public String getId() {
        return id;
    }

    public EstateException setId(String id) {
        this.id = id;
        return this;
    }

    public ExCode getExCode() {
        return exCode;
    }

    public EstateException setExCode(ExCode exCode) {
        this.exCode = exCode;
        return this;
    }

}
