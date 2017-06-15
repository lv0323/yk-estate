package com.lyun.estate.op.dianping.corp.domain;

/**
 * Created by localuser on 2017/5/17.
 */
public class ActionResultDTO {
    public boolean isSuccess() {
        return success;
    }

    public ActionResultDTO setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ActionResultDTO setReason(String reason) {
        this.reason = reason;
        return this;
    }

    boolean success;
    String reason;
}
