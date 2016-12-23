package com.lyun.estate.biz.file.entity;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.def.Target;

public class FileEntity implements Cloneable {

    private Long id;
    private Long ownerId;
    private OwnerType ownerType;
    private FileType fileType;
    private CustomType customType;
    private Target target;
    private String path;

    public Long getId() {
        return id;
    }

    public FileEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public FileEntity setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public FileEntity setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public CustomType getCustomType() {
        return customType;
    }

    public FileEntity setCustomType(CustomType customType) {
        this.customType = customType;
        return this;
    }

    public FileType getFileType() {
        return fileType;
    }

    public FileEntity setFileType(FileType fileType) {
        this.fileType = fileType;
        return this;
    }

    public Target getTarget() {
        return target;
    }

    public FileEntity setTarget(Target target) {
        this.target = target;
        return this;
    }

    public String getPath() {
        return path;
    }

    public FileEntity setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
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
    public FileEntity clone() {
        try {
            return (FileEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
