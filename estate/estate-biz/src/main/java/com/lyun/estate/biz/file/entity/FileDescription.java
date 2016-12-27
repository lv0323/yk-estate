package com.lyun.estate.biz.file.entity;

import com.lyun.estate.biz.file.def.*;

public class FileDescription implements Cloneable {

    private Long id;
    private Long ownerId;
    private OwnerType ownerType;
    private FileType fileType;
    private CustomType customType;
    private FileProcess fileProcess;
    private Target target;
    private String path;

    public Long getId() {
        return id;
    }

    public FileDescription setId(Long id) {
        this.id = id;
        return this;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public FileDescription setOwnerType(OwnerType ownerType) {
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

    public FileProcess getFileProcess() {
        return fileProcess ;
    }

    public FileDescription setFileProcess(FileProcess fileProcess) {
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
            throw new InternalError(e);
        }
    }
}
