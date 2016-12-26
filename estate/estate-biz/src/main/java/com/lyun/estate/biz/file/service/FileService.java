package com.lyun.estate.biz.file.service;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileEntity;

import java.io.InputStream;
import java.util.List;

public interface FileService {

    FileEntity save(FileEntity entity, InputStream inputStream, String suffix, FileProcess process);

    void sort(List<FileEntity> entities);

    List<FileEntity> find(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess);

    Boolean delete(Long id);
}
