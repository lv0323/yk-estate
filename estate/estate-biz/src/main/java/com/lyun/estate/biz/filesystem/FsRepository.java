package com.lyun.estate.biz.filesystem;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FsRepository {

    @InsertProvider(type = FsSqlProvider.class, method = "insert")
    int insert(FsEntity entity);

    @SelectProvider(type = FsSqlProvider.class, method = "select")
    List<FsEntity> select(OwnerType ownerType, long ownerId, String customType);

    @SelectProvider(type = FsSqlProvider.class, method = "selectByPath")
    FsEntity selectByPath(String path);

    @InsertProvider(type = FsSqlProvider.class, method = "delete")
    int delete(long id);
}
