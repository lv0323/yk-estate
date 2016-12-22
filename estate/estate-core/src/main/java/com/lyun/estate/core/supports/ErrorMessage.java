package com.lyun.estate.core.supports;

public class ErrorMessage {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public ErrorMessage setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
