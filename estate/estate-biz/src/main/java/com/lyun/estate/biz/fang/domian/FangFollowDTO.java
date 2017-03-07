package com.lyun.estate.biz.fang.domian;

import com.lyun.estate.biz.fang.def.FollowType;

import java.util.Date;

/**
 * Created by Jeffrey on 2017-02-28.
 */
public class FangFollowDTO {
    private Long id;
    private Long fangId;
    private Long companyId;
    private Long departmentId;
    private String departmentName;
    private Long employeeId;
    private String employeeName;
    private Long avatarId;
    private String avatarURI;
    private FollowType followType;
    private String content;
    private Date createTime;
    private MgtFangTiny fangTiny;

    public Long getId() {
        return id;
    }

    public FangFollowDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangFollowDTO setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public FangFollowDTO setCompanyId(Long companyId) {
        this.companyId = companyId;
        return this;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public FangFollowDTO setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        return this;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public FangFollowDTO setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public FangFollowDTO setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public FangFollowDTO setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
        return this;
    }

    public FollowType getFollowType() {
        return followType;
    }

    public FangFollowDTO setFollowType(FollowType followType) {
        this.followType = followType;
        return this;
    }

    public String getContent() {
        return content;
    }

    public FangFollowDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FangFollowDTO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public MgtFangTiny getFangTiny() {
        return fangTiny;
    }

    public FangFollowDTO setFangTiny(MgtFangTiny fangTiny) {
        this.fangTiny = fangTiny;
        return this;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public FangFollowDTO setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
        return this;
    }

    public String getAvatarURI() {
        return avatarURI;
    }

    public FangFollowDTO setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
        return this;
    }
}
