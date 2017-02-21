package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.repo.provider.MgtFangSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtFangRepository {
    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFang")
    @Options(useGeneratedKeys = true)
    int saveFang(Fang fang);

    @Select("select * from t_fang where id = #{id}")
    Fang findFang(Long id);
}
