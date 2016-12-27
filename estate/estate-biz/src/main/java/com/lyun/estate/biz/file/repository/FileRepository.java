package com.lyun.estate.biz.file.repository;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.provider.FileSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository {

    @InsertProvider(type = FileSqlProvider.class, method = "insert")
    int insert(FileDescription entity);

    @SelectProvider(type = FileSqlProvider.class, method = "select")
    List<FileDescription> select(Long ownerId, OwnerType ownerType, CustomType customType, FileProcess fileProcess);

    @InsertProvider(type = FileSqlProvider.class, method = "delete")
    int delete(Long id);
}
