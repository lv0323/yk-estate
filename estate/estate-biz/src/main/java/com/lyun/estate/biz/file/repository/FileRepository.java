package com.lyun.estate.biz.file.repository;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.provider.FileSqlProvider;
import com.lyun.estate.biz.spec.def.DomainType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    @InsertProvider(type = FileSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(FileDescription entity);

    @SelectProvider(type = FileSqlProvider.class, method = "select")
    List<FileDescription> select(@Param("ownerId") Long ownerId, @Param("ownerType") DomainType ownerType, @Param("customType") CustomType customType, @Param("fileProcess") Integer fileProcess);

    @SelectProvider(type = FileSqlProvider.class, method = "selectOne")
    FileDescription selectOne(@Param("id") Long id);

    @UpdateProvider(type = FileSqlProvider.class, method = "delete")
    int delete(@Param("id") Long id);

    @UpdateProvider(type = FileSqlProvider.class, method = "setMinPriority")
    int setMinPriority(FileDescription entity);
}
