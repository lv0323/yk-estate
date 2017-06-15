package com.lyun.estate.biz.approval.domain;

import com.lyun.estate.biz.approval.def.ApprovalDefine;

/**
 * Created by Jeffrey on 2017-06-15.
 */
public class ColdVisit {
    private String companyName;
    private String bossName;
    private ApprovalDefine.BossType bossType;
    private String contactInfo;
    private String address;
    private String visitTime1;
    private String report1;
    private String visitTime2;
    private String report2;
    private String visitTime3;
    private String report3;
    private String followers;

    public String getCompanyName() {
        return companyName;
    }

    public ColdVisit setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getBossName() {
        return bossName;
    }

    public ColdVisit setBossName(String bossName) {
        this.bossName = bossName;
        return this;
    }

    public ApprovalDefine.BossType getBossType() {
        return bossType;
    }

    public ColdVisit setBossType(ApprovalDefine.BossType bossType) {
        this.bossType = bossType;
        return this;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public ColdVisit setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ColdVisit setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getVisitTime1() {
        return visitTime1;
    }

    public ColdVisit setVisitTime1(String visitTime1) {
        this.visitTime1 = visitTime1;
        return this;
    }

    public String getReport1() {
        return report1;
    }

    public ColdVisit setReport1(String report1) {
        this.report1 = report1;
        return this;
    }

    public String getVisitTime2() {
        return visitTime2;
    }

    public ColdVisit setVisitTime2(String visitTime2) {
        this.visitTime2 = visitTime2;
        return this;
    }

    public String getReport2() {
        return report2;
    }

    public ColdVisit setReport2(String report2) {
        this.report2 = report2;
        return this;
    }

    public String getVisitTime3() {
        return visitTime3;
    }

    public ColdVisit setVisitTime3(String visitTime3) {
        this.visitTime3 = visitTime3;
        return this;
    }

    public String getReport3() {
        return report3;
    }

    public ColdVisit setReport3(String report3) {
        this.report3 = report3;
        return this;
    }

    public String getFollowers() {
        return followers;
    }

    public ColdVisit setFollowers(String followers) {
        this.followers = followers;
        return this;
    }
}
