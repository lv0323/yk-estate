package com.lyun.estate.biz.file.service;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.biz.file.spec.FileService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractFileService implements FileService {

    protected final FileRepository repository;

    protected AbstractFileService(FileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Boolean setFirst(Long id) {
        FileDescription entity = repository.selectOne(id);
        List<FileDescription> fileDescriptionList = repository.select(entity.getOwnerId(), entity.getOwnerType(), entity.getCustomType(), entity.getFileProcess());
        for (FileDescription fd : fileDescriptionList)
            repository.updatePriority(fd.getId(), null);
        return repository.updatePriority(id, 0) > 0;
    }

    @Override
    public List<FileDescription> find(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess) {
        ExceptionUtil.checkNotNull("ownerId", ownerId);
        ExceptionUtil.checkNotNull("ownerType", ownerType);
        return repository.select(ownerId, ownerType, customType, fileProcess != null ? fileProcess.getFlag() : null);
    }

    @Override
    public Boolean delete(Long id) {
        ExceptionUtil.checkNotNull("id", id);
        return repository.delete(id) > 0;
    }
}
