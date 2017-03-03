package com.lyun.estate.biz.fang.domian;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.core.supports.types.YN;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-24.
 */
public class MgtFangSelector extends TagSelector {
    private Long fangId;
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Long subDistrictId;
    private HouseType houseType;
    private Integer minArea;
    private Integer maxArea;
    private Integer sCounts;
    private List<Long> departmentIds;
    private Long employeeId;
    private DelegateType delegateType;
    private PropertyType propertyType;
    private CertifType certifType;
    private YN resident;
    private HouseProcess process;
    private Integer minPrice;
    private Integer maxPrice;
    private Date minCreateTime;
    private Date maxCreateTime;
    private Date minDelegateTime;
    private Date maxDelegateTime;
    private Date minPublishTime;
    private Date maxPublishTime;
    private Date minFollowTime;
    private Date maxFollowTime;

    public Long getCityId() {
        return cityId;
    }

    public MgtFangSelector setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public MgtFangSelector setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtFangSelector setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtFangSelector setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public MgtFangSelector setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public MgtFangSelector setMinArea(Integer minArea) {
        this.minArea = minArea;
        return this;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public MgtFangSelector setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public MgtFangSelector setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }


    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public MgtFangSelector setDepartmentIds(List<Long> departmentIds) {
        this.departmentIds = departmentIds;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public MgtFangSelector setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public DelegateType getDelegateType() {
        return delegateType;
    }

    public MgtFangSelector setDelegateType(DelegateType delegateType) {
        this.delegateType = delegateType;
        return this;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public MgtFangSelector setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public CertifType getCertifType() {
        return certifType;
    }

    public MgtFangSelector setCertifType(CertifType certifType) {
        this.certifType = certifType;
        return this;
    }

    public YN getResident() {
        return resident;
    }

    public MgtFangSelector setResident(YN resident) {
        this.resident = resident;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public MgtFangSelector setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public MgtFangSelector setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public Date getMinCreateTime() {
        return minCreateTime;
    }

    public MgtFangSelector setMinCreateTime(Date minCreateTime) {
        this.minCreateTime = minCreateTime;
        return this;
    }

    public Date getMaxCreateTime() {
        return maxCreateTime;
    }

    public MgtFangSelector setMaxCreateTime(Date maxCreateTime) {
        this.maxCreateTime = maxCreateTime;
        return this;
    }

    public Date getMinDelegateTime() {
        return minDelegateTime;
    }

    public MgtFangSelector setMinDelegateTime(Date minDelegateTime) {
        this.minDelegateTime = minDelegateTime;
        return this;
    }

    public Date getMaxDelegateTime() {
        return maxDelegateTime;
    }

    public MgtFangSelector setMaxDelegateTime(Date maxDelegateTime) {
        this.maxDelegateTime = maxDelegateTime;
        return this;
    }

    public Date getMinPublishTime() {
        return minPublishTime;
    }

    public MgtFangSelector setMinPublishTime(Date minPublishTime) {
        this.minPublishTime = minPublishTime;
        return this;
    }

    public Date getMaxPublishTime() {
        return maxPublishTime;
    }

    public MgtFangSelector setMaxPublishTime(Date maxPublishTime) {
        this.maxPublishTime = maxPublishTime;
        return this;
    }

    public Date getMinFollowTime() {
        return minFollowTime;
    }

    public MgtFangSelector setMinFollowTime(Date minFollowTime) {
        this.minFollowTime = minFollowTime;
        return this;
    }

    public Date getMaxFollowTime() {
        return maxFollowTime;
    }

    public MgtFangSelector setMaxFollowTime(Date maxFollowTime) {
        this.maxFollowTime = maxFollowTime;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public MgtFangSelector setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public MgtFangSelector setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }
}
