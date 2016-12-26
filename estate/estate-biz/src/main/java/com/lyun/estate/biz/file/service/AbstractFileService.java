package com.lyun.estate.biz.file.service;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileEntity;
import com.lyun.estate.biz.file.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractFileService implements FileService {

    protected final FileRepository repository;

    @Autowired
    public AbstractFileService(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FileEntity> find(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess) {
        return repository.select(ownerId, ownerType, customType, fileProcess);
    }

    @Override
    public Boolean delete(Long id) {
        return repository.delete(id) > 0;
    }
}
