package com.lyun.estate.biz.file.repository;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.repository.provider.FileSqlProvider;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileEntity;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    @InsertProvider(type = FileSqlProvider.class, method = "insert")
    int insert(FileEntity entity);

    @SelectProvider(type = FileSqlProvider.class, method = "select")
    List<FileEntity> select(Long ownerId, OwnerType ownerType, CustomType customType);

    @InsertProvider(type = FileSqlProvider.class, method = "delete")
    int delete(Long id);
}
