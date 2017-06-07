package com.lyun.estate.op.dianping.corp.entity;

/**
 * Created by localuser on 2017/5/17.
 */
public class ActionResultBean {
    public boolean isSuccess() {
        return success;
    }

    public ActionResultBean setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public ActionResultBean setReason(String reason) {
        this.reason = reason;
        return this;
    }

    boolean success;
    String reason;
}
