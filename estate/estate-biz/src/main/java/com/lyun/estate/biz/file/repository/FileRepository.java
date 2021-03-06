package com.lyun.estate.biz.file.repository;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.provider.FileSqlProvider;
import com.lyun.estate.biz.support.def.DomainType;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    @InsertProvider(type = FileSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int insert(FileDescription entity);

    @SelectProvider(type = FileSqlProvider.class, method = "select")
    List<FileDescription> select(@Param("ownerId") Long ownerId, @Param("ownerType") DomainType ownerType,
                                 @Param("customType") CustomType customType, @Param("fileProcess") Integer fileProcess);

    @SelectProvider(type = FileSqlProvider.class, method = "selectOne")
    FileDescription selectOne(@Param("id") Long id);

    @UpdateProvider(type = FileSqlProvider.class, method = "delete")
    int delete(@Param("id") Long id);

    @SelectProvider(type = FileSqlProvider.class, method = "findFirst")
    FileDescription findFirst(@Param("ownerId") Long ownerId, @Param("ownerType") DomainType ownerType,
                              @Param("customType") CustomType customType, @Param("fileProcess") Integer fileProcess);

    @UpdateProvider(type = FileSqlProvider.class, method = "setMinPriority")
    int setMinPriority(FileDescription entity);

    @Select("SELECT * FROM t_file_description WHERE id =#{id}")
    FileDescription findOne(@Param("id") Long id);
}
