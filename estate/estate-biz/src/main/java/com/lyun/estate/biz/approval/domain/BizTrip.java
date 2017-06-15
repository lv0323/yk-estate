package com.lyun.estate.biz.approval.domain;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-06-15.
 */
public class BizTrip {
    private String startTime;
    private String endTime;
    private Integer days;
    private String reason;
    private String outcome;
    private String problem;
    private String resource;
    private BigDecimal costs;

    public String getStartTime() {
        return startTime;
    }

    public BizTrip setStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    public String getEndTime() {
        return endTime;
    }

    public BizTrip setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public Integer getDays() {
        return days;
    }

    public BizTrip setDays(Integer days) {
        this.days = days;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public BizTrip setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getOutcome() {
        return outcome;
    }

    public BizTrip setOutcome(String outcome) {
        this.outcome = outcome;
        return this;
    }

    public String getProblem() {
        return problem;
    }

    public BizTrip setProblem(String problem) {
        this.problem = problem;
        return this;
    }

    public String getResource() {
        return resource;
    }

    public BizTrip setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public BigDecimal getCosts() {
        return costs;
    }

    public BizTrip setCosts(BigDecimal costs) {
        this.costs = costs;
        return this;
    }
}
