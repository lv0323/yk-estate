package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
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

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangExt")
    @Options(useGeneratedKeys = true)
    int saveFangExt(FangExt fangExt);

    @Select("select * from t_fang_ext where id =#{id}")
    FangExt findFangExt(Long id);


    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangContact")
    @Options(useGeneratedKeys = true)
    int saveFangContact(FangContact fangContact);

    @Select("select * from t_fang_contact where id = #{id}")
    FangContact findFangContact(Long id);

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangInfoOwner")
    @Options(useGeneratedKeys = true)
    int saveFangInfoOwner(FangInfoOwner fangInfoOwner);

    @Select("select * from t_fang_info_owner where id = #{id}")
    FangInfoOwner findFangInfoOwner(Long id);
}
