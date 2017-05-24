package com.lyun.estate.biz.company.domain;

import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.employee.domain.EmployeeDTO;

import java.util.Date;


public class CompanyDTO {
    private Long id;
    private Long cityId;
    private String cityName;
    private Long parentId;
    private CompanyDefine.Type type;
    private CompanyDefine.Status status;
    private String name;
    private String abbr;
    private String secretKey;
    private Long bossId;
    private Date startDate;
    private Date endDate;

    private EmployeeDTO boss;
    private EmployeeDTO partA;

    private Integer deptsCount;
    private Integer employeeCount;
    private Integer ChannelCount;
    private Integer sigleStoreCount;

    public Long getId() {
        return id;
    }

    public CompanyDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCityId() {
        return cityId;
    }

    public CompanyDTO setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public CompanyDTO setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CompanyDTO setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public CompanyDefine.Type getType() {
        return type;
    }

    public CompanyDTO setType(CompanyDefine.Type type) {
        this.type = type;
        return this;
    }

    public CompanyDefine.Status getStatus() {
        return status;
    }

    public CompanyDTO setStatus(CompanyDefine.Status status) {
        this.status = status;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbr() {
        return abbr;
    }

    public CompanyDTO setAbbr(String abbr) {
        this.abbr = abbr;
        return this;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public CompanyDTO setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public Long getBossId() {
        return bossId;
    }

    public CompanyDTO setBossId(Long bossId) {
        this.bossId = bossId;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    public CompanyDTO setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public CompanyDTO setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public EmployeeDTO getBoss() {
        return boss;
    }

    public CompanyDTO setBoss(EmployeeDTO boss) {
        this.boss = boss;
        return this;
    }

    public Integer getDeptsCount() {
        return deptsCount;
    }

    public CompanyDTO setDeptsCount(Integer deptsCount) {
        this.deptsCount = deptsCount;
        return this;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public CompanyDTO setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    public Integer getChannelCount() {
        return ChannelCount;
    }

    public CompanyDTO setChannelCount(Integer channelCount) {
        ChannelCount = channelCount;
        return this;
    }

    public Integer getSigleStoreCount() {
        return sigleStoreCount;
    }

    public CompanyDTO setSigleStoreCount(Integer sigleStoreCount) {
        this.sigleStoreCount = sigleStoreCount;
        return this;
    }

    public EmployeeDTO getPartA() {
        return partA;
    }

    public CompanyDTO setPartA(EmployeeDTO partA) {
        this.partA = partA;
        return this;
    }
}
