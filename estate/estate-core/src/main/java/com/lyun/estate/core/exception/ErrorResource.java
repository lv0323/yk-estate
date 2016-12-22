package com.lyun.estate.core.exception;

public class ErrorResource {
    private final String logRef;
    private final String exCode;
    private final String message;

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

}
