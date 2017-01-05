package com.lyun.estate.biz.spec.service;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.def.DomainType;

import java.io.InputStream;
import java.util.List;

public interface FileService {

    FileDescription save(FileDescription entity, InputStream inputStream, String suffix);

    Boolean setFirst(Long id);

    List<FileDescription> find(Long ownerId, DomainType ownerType, CustomType customType, FileProcess fileProcess);

    Boolean delete(Long id);
}
