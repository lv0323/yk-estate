package com.lyun.estate.biz.file.service;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.biz.spec.def.DomainType;
import com.lyun.estate.biz.spec.service.FileService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractFileService implements FileService {

    protected final FileRepository repository;

    protected AbstractFileService(FileRepository repository) {
        this.repository = repository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean setFirst(Long id) {
        return repository.updatePriority(repository.selectOne(id)) > 0;
    }

    @Override
    public List<FileDescription> find(Long ownerId, DomainType ownerType, CustomType customType, FileProcess fileProcess) {
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
