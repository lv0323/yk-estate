package com.lyun.estate.biz.approval.domain;

/**
 * Created by Jeffrey on 2017-06-15.
 */
public class Leaving {
    private String startTime;
    private String endTime;
    private String location;
    private String reason;
    private String noClockReason;

    public String getStartTime() {
        return startTime;
    }

    public Leaving setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public Leaving setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Leaving setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public Leaving setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getNoClockReason() {
        return noClockReason;
    }

    public Leaving setNoClockReason(String noClockReason) {
        this.noClockReason = noClockReason;
        return this;
    }
}
