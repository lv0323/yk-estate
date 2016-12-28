package com.lyun.estate.biz.file.repository;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.provider.FileSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    @InsertProvider(type = FileSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(FileDescription entity);

    @SelectProvider(type = FileSqlProvider.class, method = "select")
    List<FileDescription> select(@Param("ownerId") Long ownerId, @Param("ownerType") OwnerType ownerType, @Param("customType") CustomType customType, @Param("fileProcess") Integer fileProcess);

    @UpdateProvider(type = FileSqlProvider.class, method = "delete")
    int delete(@Param("id") Long id);

    @UpdateProvider(type = FileSqlProvider.class, method = "setPriority")
    int setPriority(FileDescription entity);
}
