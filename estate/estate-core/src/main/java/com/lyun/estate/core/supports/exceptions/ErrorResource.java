package com.lyun.estate.core.supports.exceptions;

public class ErrorResource {
    private String logRef;
    private String exCode;
    private String message;

    public ErrorResource() {
    }

    public ErrorResource(String logRef, String exCode, String message) {
        this.logRef = logRef;
        this.exCode = exCode;
        this.message = message;
    }

    public String getLogRef() {
        return logRef;
    }

    public String getExCode() {
        return exCode;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResource setLogRef(String logRef) {
        this.logRef = logRef;
        return this;
    }

    public ErrorResource setExCode(String exCode) {
        this.exCode = exCode;
        return this;
    }

    public ErrorResource setMessage(String message) {
        this.message = message;
        return this;
    }
}
