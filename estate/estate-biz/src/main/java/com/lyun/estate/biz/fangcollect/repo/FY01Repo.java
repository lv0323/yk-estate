package com.lyun.estate.biz.fangcollect.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fangcollect.entity.FY01Fang;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.entity.FangPoolDistrict;
import com.lyun.estate.biz.fangcollect.repo.provider.FangPoolSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by robin on 17/5/5.
 */

@Repository
public interface FY01Repo {

    @Select("SELECT * FROM t_fang_o1fy WHERE id = #{id}")
    PageList<Fang> findUnProcess();

    @Select("SELECT * FROM t_fang_o1fy WHERE map_process is NULL or map_process!='processed'")
    List<FY01Fang> selectUnProcessItems();

    @Update("UPDATE t_fang_o1fy SET map_process = 'processed' where third_party_id = #{thirdPartyId}")
    int updateProcess(String thirdPartId);

    @Select("SELECT * FROM t_fang_pool_district where name = #{name}")
    PageList<FangPoolDistrict> getDistrictByName(String name);

    @InsertProvider(type = FangPoolSqlProvider.class, method = "createFangPool")
    PageList<FangPool> createFongPool(FangPool fangPool);

}
