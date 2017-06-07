package com.lyun.estate.op.dianping.corp.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by localuser on 2017/5/16.
 */
public class WxLoginResponse {

    @SerializedName("openid")
    String openId;
    @SerializedName("session_key")
    String sessionKey;
    String errcode;
    String errmsg;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openid) {
        this.openId = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String session_key) {
        this.sessionKey = session_key;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
