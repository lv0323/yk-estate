package com.lyun.estate.biz.filesystem;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

public abstract class FileSystem {

    protected final FsRepository repository;

    @Autowired
    public FileSystem(FsRepository repository) {
        this.repository = repository;
    }

    public abstract FsEntity create(OwnerType ownerType, long ownerId, String customType, FileType fileType, InputStream inputStream);

    public abstract FsEntity watermark(String customType, String path);

    public List<FsEntity> find(OwnerType ownerType, long ownerId, String customType) {
        return repository.select(ownerType, ownerId, customType);
    }

    public boolean delete(long id) {
        return repository.delete(id) > 0;
    }
}
