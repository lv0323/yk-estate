package com.lyun.estate.core.supports.resources;

public class VerifyCode {
    private long clientId;
    private String verifyId;
    private String verifyCode;

    public long getClientId() {
        return clientId;
    }

    public VerifyCode setClientId(long clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getVerifyId() {
        return verifyId;
    }

    public VerifyCode setVerifyId(String verifyId) {
        this.verifyId = verifyId;
        return this;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public VerifyCode setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }
}
