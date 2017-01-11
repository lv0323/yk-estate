package com.lyun.estate.biz.file.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.settings.def.NameSpace;
import com.lyun.estate.biz.settings.entity.Setting;
import com.lyun.estate.biz.settings.SettingProvider;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.Target;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.biz.spec.def.DomainType;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractFileService implements FileService {

    protected final FileRepository repository;
    @Autowired
    private SettingProvider settingProvider;

    protected AbstractFileService(FileRepository repository) {
        this.repository = repository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public Boolean setFirst(Long id) {
        return repository.setMinPriority(repository.selectOne(id)) > 0;
    }

    @Override
    public List<FileDescription> find(Long ownerId, DomainType ownerType, CustomType customType,
                                      FileProcess fileProcess) {
        ExceptionUtil.checkNotNull("ownerId", ownerId);
        ExceptionUtil.checkNotNull("ownerType", ownerType);
        List<FileDescription> result = repository.select(ownerId,
                ownerType,
                customType,
                fileProcess != null ? fileProcess.getFlag() : null);
        result.forEach(fd -> fd.setFileURI(getFileURI(fd.getPath(), fd.getTarget())));
        return result;
    }

    @Override
    public Boolean delete(Long id) {
        ExceptionUtil.checkNotNull("id", id);
        return repository.delete(id) > 0;
    }

    protected String getFileURI(String path, Target target) {
        if (Strings.isNullOrEmpty(path) || target == null) {
            return "";
        }
        Setting uriPrefix = settingProvider.find(NameSpace.FILE, target.name() + "_URI_PREFIX");
        return uriPrefix.getValue() + path;
    }

    @Override
    public FileDescription findFirst(Long ownerId, DomainType ownerType, CustomType customType,
                                     FileProcess fileProcess) {
        ExceptionUtil.checkNotNull("ownerId", ownerId);
        ExceptionUtil.checkNotNull("ownerType", ownerType);
        FileDescription result = repository.findFirst(ownerId,
                ownerType,
                customType,
                fileProcess != null ? fileProcess.getFlag() : null);
        if (result != null) {
            result.setFileURI(getFileURI(result.getPath(), result.getTarget()));
        }
        return result;
    }

}
