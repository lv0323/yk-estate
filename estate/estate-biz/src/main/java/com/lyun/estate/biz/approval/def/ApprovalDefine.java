package com.lyun.estate.biz.approval.def;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.math.BigDecimal;

/**
 * Created by Jeffrey on 2017-06-12.
 */
public class ApprovalDefine {
    public enum Type implements LabelEnum {
        LEAVING("外出", false),
        BIZ_TRIP("出差", false),
        COLD_VISIT("陌拜", false),
        SIGNING("签约", true),;

        private final String label;
        private final boolean needApproval;

        Type(String label, boolean needApproval) {
            this.label = label;
            this.needApproval = needApproval;
        }

        @Override
        public String getLabel() {
            return label;
        }

        public boolean isNeedApproval() {
            return needApproval;
        }
    }

    public enum Status implements LabelEnum {
        CREATED("待审核", false),
        APPROVED("审核通过", true),
        REJECTED("被拒绝", true),;

        private final String label;
        private final boolean end;

        Status(String label, boolean end) {
            this.label = label;
            this.end = end;
        }

        @Override
        public String getLabel() {
            return label;
        }

        public boolean isEnd() {
            return end;
        }
    }

    public enum BossType {
        DEPT_MANAGER,//店长
        MANAGER,//总经理
        AGENT,//经纪人
        BOSS;//老板
    }

    public static class Leaving {
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

    public static class BizTrip {
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

    public static class ColdVisit {
        private String companyName;
        private String bossName;
        private BossType bossType;
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

        public BossType getBossType() {
            return bossType;
        }

        public ColdVisit setBossType(BossType bossType) {
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

    public static class Signing {
        private CompanyDefine.Type companyType;
        private String companyName;
        private String companyAbbr;
        private String signingDate;
        private Long cityId;
        private Long cityName;
        private String address;
        private String bossName;
        private String bossMobile;
        private String partAInChargeId;//本公司负责人编号
        private String partAInChargeName;//本公司负责人姓名
        private String note;
        private Integer years;
        private Integer storeCount;
        private String startDate;
        private String endDate;
        private BigDecimal price;

        public CompanyDefine.Type getCompanyType() {
            return companyType;
        }

        public Signing setCompanyType(CompanyDefine.Type companyType) {
            this.companyType = companyType;
            return this;
        }

        public String getCompanyName() {
            return companyName;
        }

        public Signing setCompanyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public String getCompanyAbbr() {
            return companyAbbr;
        }

        public Signing setCompanyAbbr(String companyAbbr) {
            this.companyAbbr = companyAbbr;
            return this;
        }

        public String getSigningDate() {
            return signingDate;
        }

        public Signing setSigningDate(String signingDate) {
            this.signingDate = signingDate;
            return this;
        }

        public Long getCityId() {
            return cityId;
        }

        public Signing setCityId(Long cityId) {
            this.cityId = cityId;
            return this;
        }

        public Long getCityName() {
            return cityName;
        }

        public Signing setCityName(Long cityName) {
            this.cityName = cityName;
            return this;
        }

        public String getAddress() {
            return address;
        }

        public Signing setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getBossName() {
            return bossName;
        }

        public Signing setBossName(String bossName) {
            this.bossName = bossName;
            return this;
        }

        public String getBossMobile() {
            return bossMobile;
        }

        public Signing setBossMobile(String bossMobile) {
            this.bossMobile = bossMobile;
            return this;
        }

        public String getPartAInChargeId() {
            return partAInChargeId;
        }

        public Signing setPartAInChargeId(String partAInChargeId) {
            this.partAInChargeId = partAInChargeId;
            return this;
        }

        public String getPartAInChargeName() {
            return partAInChargeName;
        }

        public Signing setPartAInChargeName(String partAInChargeName) {
            this.partAInChargeName = partAInChargeName;
            return this;
        }

        public String getNote() {
            return note;
        }

        public Signing setNote(String note) {
            this.note = note;
            return this;
        }

        public Integer getYears() {
            return years;
        }

        public Signing setYears(Integer years) {
            this.years = years;
            return this;
        }

        public Integer getStoreCount() {
            return storeCount;
        }

        public Signing setStoreCount(Integer storeCount) {
            this.storeCount = storeCount;
            return this;
        }

        public String getStartDate() {
            return startDate;
        }

        public Signing setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public String getEndDate() {
            return endDate;
        }

        public Signing setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public Signing setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }
    }
}
