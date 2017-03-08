package com.lyun.estate.biz.file.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.def.Target;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;

import java.util.Date;

public class FileDescription implements Cloneable {

    private Long id;
    private Long ownerId;
    private DomainType ownerType;
    private FileType fileType;
    private CustomType customType;
    private Integer fileProcess;
    private Target target;
    private String path;
    private Integer priority;
    @JsonIgnore
    private String ext;
    @JsonIgnore
    private Boolean isDeleted;
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private String fileURI;

    public Long getId() {
        return id;
    }

    public FileDescription setId(Long id) {
        this.id = id;
        return this;
    }

    public DomainType getOwnerType() {
        return ownerType;
    }

    public FileDescription setOwnerType(DomainType ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public FileDescription setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public FileDescription setCustomType(CustomType customType) {
        this.customType = customType;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public FileDescription setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public Target getTarget() {
        return target;
    }

    public FileDescription setTarget(Target target) {
        this.target = target;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileDescription setPath(String path) {
        this.path = path;
        return this;
    }

    public Integer getFileProcess() {
        return fileProcess;
    }

    public FileDescription setFileProcess(Integer fileProcess) {
        this.fileProcess = fileProcess;
        return this;
    }

    @Override
    public String toString() {
        return "FileDescription{" +
                "id=" + id +
                ", ownerType=" + ownerType +
                ", ownerId=" + ownerId +
                ", customType=" + customType +
                ", fileType=" + fileType +
                ", target='" + target + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public FileDescription clone() {
        try {
            return (FileDescription) super.clone();
        } catch (CloneNotSupportedException e) {
            throw ExceptionUtil.wrap(e);
        }
    }

    public Integer getPriority() {
        return priority;
    }

    public FileDescription setPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public String getFileURI() {
        return fileURI;
    }

    public FileDescription setFileURI(String fileURI) {
        this.fileURI = fileURI;
        return this;
    }

    public String getExt() {
        return ext;
    }

    public FileDescription setExt(String ext) {
        this.ext = ext;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public FileDescription setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public FileDescription setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public FileDescription setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
