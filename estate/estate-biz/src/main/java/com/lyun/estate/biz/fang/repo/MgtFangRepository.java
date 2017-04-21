package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.MgtFangSelector;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.repo.provider.MgtFangSqlProvider;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtFangRepository {

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFang")
    @Options(useGeneratedKeys = true)
    int saveFang(Fang fang);

    @Update("UPDATE t_fang SET house_sub_type = #{houseSubType}, s_counts = #{sCounts}  , t_counts= #{tCounts}  ," +
            " c_counts = #{cCounts}  , w_counts = #{wCounts}  , yt_counts = #{ytCounts}  , orientation  = #{orientation}  , decorate = #{decorate}  ," +
            " estate_area = #{estateArea}  , real_area  = #{realArea}  , publish_price  = #{publishPrice}  , price_unit = #{priceUnit}  , " +
            " unit_price = #{unitPrice}  , transfer_price = #{transferPrice}  , bottom_price   = #{bottomPrice}  , resident = #{resident}  , " +
            " floor = #{floor}  , floor_counts   = #{floorCounts}  , structure_type = #{structureType}  , build_year = #{buildYear}  ," +
            " heating_type = #{heatingType}, has_elevator = #{hasElevator}, update_time = CURRENT_TIMESTAMP " +
            " WHERE id = #{id} and is_deleted =false")
    int updateFang(Fang fang);

    @Select("select * from t_fang where id = #{id} and is_deleted = false")
    Fang findFang(Long id);

    @Select("select * from t_fang where licence_id = #{licenceId} and is_deleted = false")
    Fang findFangByLicenceId(Long licenceId);

    @InsertProvider(type = MgtFangSqlProvider.class, method = "saveFangExt")
    @Options(useGeneratedKeys = true)
    int saveFangExt(FangExt fangExt);

    @Update("UPDATE t_fang_ext SET level = #{level}, showing = #{showing}, delegate_type = #{delegateType}, delegate_start = #{delegateStart},delegate_end = #{delegateEnd}, " +
            " status = #{status},source = #{source},certif_type = #{certifType},certif_no = #{certifNo},  certif_address = #{certifAddress}, taxes_willing = #{taxesWilling}, " +
            " commission_willing = #{commissionWilling}, purchase_price = #{purchasePrice}, purchase_date= #{purchaseDate}, is_only = #{isOnly},over_years= #{overYears}, mortgage =  #{mortgage}, note = #{note}  " +
            " WHERE fang_id = #{fangId}")
    int updateFangExtByFangId(FangExt fangExt);

    @Select("select * from t_fang_ext where id =#{id}")
    FangExt findFangExt(Long id);

    @Select("select * from t_fang_ext where fang_id = #{fangId}")
    FangExt findFangExtByFangId(Long fangId);

    @SelectProvider(type = MgtFangSqlProvider.class, method = "listSummary")
    PageList<MgtFangSummary> listSummary(MgtFangSelector selector, PageBounds pageBounds);

    @Select("SELECT * FROM t_fang WHERE id = #{fangId} FOR UPDATE")
    Fang selectForUpdate(long fangId);

    @Update("UPDATE t_fang SET process = 'SUCCESS', sub_process = NULL, update_time = now() where id = #{fangId}")
    int deal(long fangId);

    @Update("UPDATE t_fang SET process = 'PUBLISH', update_time = now(), publish_time = now() where id = #{fangId}")
    int publish(long fangId);

    @Update("UPDATE t_fang SET process = 'UN_PUBLISH', sub_process = NULL, update_time = now(), publish_time = NULL where id = #{fangId}")
    int unPublish(long fangId);

    @Update("UPDATE t_fang SET is_deleted = TRUE, update_time = now() where id = #{fangId}")
    int delete(long fangId);

    @Update("UPDATE t_fang set update_time = CURRENT_TIMESTAMP where id = #{id} and is_deleted = false")
    int updateTime(Long id);

    @Update("UPDATE t_fang SET process = 'PAUSE', sub_process = NULL, update_time = now(), publish_time = NULL where id = #{fangId}")
    int pause(long fangId);

    @Update("UPDATE t_fang SET sub_process = 'PRE_PUBLIC', update_time = now() where id = #{fangId}")
    int applyPublic(long fangId);

    @Update("UPDATE t_fang SET sub_process = NULL, update_time = now() where id = #{fangId}")
    int rejectPublic(long fangId);

    @Update("UPDATE t_fang SET sub_process = 'PUBLIC', update_time = now() where id = #{fangId}")
    int confirmPublic(long fangId);

    @Update("UPDATE t_fang SET sub_process = NULL, update_time = now() where id = #{fangId}")
    int undoPublic(long fangId);

    @Select("SELECT f.*, c.name as xiao_qu_name FROM t_fang f LEFT JOIN t_xiao_qu xq on f.xiao_qu_id = xq.id LEFT JOIN t_community c on xq.community_id = c.id\n" +
            "WHERE f.id = #{fangId}")
    MgtFangTiny getMgtFangTiny(long fangId);

    @Select("SELECT f.*, c.name as xiao_qu_name FROM t_fang f LEFT JOIN t_xiao_qu xq on f.xiao_qu_id = xq.id LEFT JOIN t_community c on xq.community_id = c.id\n" +
            "WHERE f.licence_id = #{licenceId}")
    MgtFangTiny getMgtFangTinyByLicenceId(long licenceId);
}
