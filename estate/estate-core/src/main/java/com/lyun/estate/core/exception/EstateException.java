package com.lyun.estate.core.exception;

import java.util.UUID;

/**
 * Created by henry on 2016/5/5.
 */
public class EstateException extends RuntimeException {

    private ExCode exCode = ExCode.DEFAULT_EXCEPTION;

    private String id = UUID.randomUUID().toString();

    public EstateException() {
        super(ExCode.DEFAULT_EXCEPTION.getMessageTemplate());
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

    public ExCode getExCode() {
        return exCode;
    }

    public EstateException setExCode(ExCode exCode) {
        this.exCode = exCode;
        return this;
    }
}
