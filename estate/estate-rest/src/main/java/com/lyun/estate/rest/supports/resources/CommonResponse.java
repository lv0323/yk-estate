package com.lyun.estate.rest.supports.resources;


public class CommonResponse {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public CommonResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
