package com.lyun.estate.amqp.spec.resources;

public class CommonResponse {
    private boolean success;
    private String code;
    private String message;
    // if success=false; service case by biz exception(false) or other unexpected exceptions(true)
    private boolean error;

    public boolean isSuccess() {
        return success;
    }

    public CommonResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getCode() {
        return code;
    }

    public CommonResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isError() {
        return error;
    }

    public CommonResponse setError(boolean error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
