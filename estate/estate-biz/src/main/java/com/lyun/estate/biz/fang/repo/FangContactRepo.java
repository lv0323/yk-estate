package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.repo.provider.FangContactSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangContactRepo {

    @InsertProvider(type = FangContactSqlProvider.class, method = "save")
    @Options(useGeneratedKeys = true)
    int saveFangContact(FangContact fangContact);

    @Select("select * from t_fang_contact where id = #{id}")
    FangContact findFangContact(Long id);

    @Select("SELECT * FROM t_fang_contact where fang_id = #{fangId} LIMIT 1")
    FangContact findByFangId(Long fangId);

    @Update("UPDATE t_fang_contact SET name = #{name}, mobile = #{mobile}, a_mobile = #{aMobile}, b_mobile = #{bMobile}\n" +
            ", qq   = #{qq}, we_chat = #{weChat}, email = #{email}, update_time = CURRENT_TIMESTAMP WHERE fang_id = #{fangId}")
    int updateByFangId(FangContact fangContact);
}
