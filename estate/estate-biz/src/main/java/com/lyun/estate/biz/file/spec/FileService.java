package com.lyun.estate.biz.file.spec;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;

import java.io.InputStream;
import java.util.List;

public interface FileService {

    FileDescription save(FileDescription entity, InputStream inputStream, String suffix);

    Boolean setFirst(Long id);

    List<FileDescription> find(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess);

    Boolean delete(Long id);
}
