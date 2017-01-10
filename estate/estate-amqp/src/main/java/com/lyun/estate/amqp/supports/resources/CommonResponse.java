package com.lyun.estate.amqp.supports.resources;

/**
 * Created by stone on 17-1-10.
 */
public class CommonResponse {
    public boolean success;

    public boolean isSuccess() {
        return success;
    }

    public CommonResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
