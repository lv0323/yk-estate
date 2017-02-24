package com.lyun.estate.biz.spec.fang.mgt.entity;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.spec.fang.mgt.def.TimeType;
import com.lyun.estate.core.supports.types.YN;

import java.util.Date;
import java.util.List;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtFangFilter {
    private Long cityId;
    private BizType bizType;
    private Long districtId;
    private Long subDistrictId;
    private HouseType houseType;
    private Integer minArea;
    private Integer maxArea;
    private Integer sCounts;
    private List<HouseTag> houseTags;
    private Long departmentId;
    private Long employeeId;
    private Boolean includeChildren;
    private TimeType timeType;
    private Date startTime;
    private Date endTime;
    private DelegateType delegateType;
    private Decorate decorate;
    private PropertyType propertyType;
    private CertifType certifType;
    private YN resident;
    private Integer minPrice;
    private Integer maxPrice;
    private HouseProcess process;

    public Long getCityId() {
        return cityId;
    }

    public MgtFangFilter setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public BizType getBizType() {
        return bizType;
    }

    public MgtFangFilter setBizType(BizType bizType) {
        this.bizType = bizType;
        return this;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public MgtFangFilter setDistrictId(Long districtId) {
        this.districtId = districtId;
        return this;
    }

    public Long getSubDistrictId() {
        return subDistrictId;
    }

    public MgtFangFilter setSubDistrictId(Long subDistrictId) {
        this.subDistrictId = subDistrictId;
        return this;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public MgtFangFilter setHouseType(HouseType houseType) {
        this.houseType = houseType;
        return this;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public MgtFangFilter setMinArea(Integer minArea) {
        this.minArea = minArea;
        return this;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public MgtFangFilter setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
        return this;
    }

    public Integer getsCounts() {
        return sCounts;
    }

    public MgtFangFilter setsCounts(Integer sCounts) {
        this.sCounts = sCounts;
        return this;
    }

    public List<HouseTag> getHouseTags() {
        return houseTags;
    }

    public MgtFangFilter setHouseTags(List<HouseTag> houseTags) {
        this.houseTags = houseTags;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public MgtFangFilter setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public MgtFangFilter setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Boolean getIncludeChildren() {
        return includeChildren;
    }

    public MgtFangFilter setIncludeChildren(Boolean includeChildren) {
        this.includeChildren = includeChildren;
        return this;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public MgtFangFilter setTimeType(TimeType timeType) {
        this.timeType = timeType;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public MgtFangFilter setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Date getEndTime() {
        return endTime;
    }

    public MgtFangFilter setEndTime(Date endTime) {
        this.endTime = endTime;
        return this;
    }

    public DelegateType getDelegateType() {
        return delegateType;
    }

    public MgtFangFilter setDelegateType(DelegateType delegateType) {
        this.delegateType = delegateType;
        return this;
    }

    public Decorate getDecorate() {
        return decorate;
    }

    public MgtFangFilter setDecorate(Decorate decorate) {
        this.decorate = decorate;
        return this;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public MgtFangFilter setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
        return this;
    }

    public CertifType getCertifType() {
        return certifType;
    }

    public MgtFangFilter setCertifType(CertifType certifType) {
        this.certifType = certifType;
        return this;
    }

    public YN getResident() {
        return resident;
    }

    public MgtFangFilter setResident(YN resident) {
        this.resident = resident;
        return this;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public MgtFangFilter setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public MgtFangFilter setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

    public HouseProcess getProcess() {
        return process;
    }

    public MgtFangFilter setProcess(HouseProcess process) {
        this.process = process;
        return this;
    }
}
