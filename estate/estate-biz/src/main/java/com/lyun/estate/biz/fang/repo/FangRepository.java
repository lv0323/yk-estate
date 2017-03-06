package com.lyun.estate.biz.fang.repo;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.FangSelector;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangDescr;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangTag;
import com.lyun.estate.biz.fang.repo.provider.FangSqlProvider;
import com.lyun.estate.biz.spec.fang.rest.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-23.
 */
@Repository
public interface FangRepository {

    @SelectProvider(type = FangSqlProvider.class, method = "findSummaryById")
    FangSummary findSummary(Long fangId);

    @Select("SELECT * FROM t_fang_tag WHERE fang_id = #{fangId}")
    List<FangTag> findTags(@Param("fangId") Long fangId);

    @Select("SELECT * FROM t_fang_descr WHERE fang_id = #{fangId}")
    FangDescr findDescr(@Param("fangId") Long fangId);

    @SelectProvider(type = FangSqlProvider.class, method = "findDetailById")
    FangDetail findDetail(Long fangId);

    @SelectProvider(type = FangSqlProvider.class, method = "findSummaryBySelector")
    PageList<FangSummary> findSummaryBySelector(FangSelector selector, PageBounds pageBounds);

    @Update("UPDATE T_FANG SET NAME_KW=#{keyword} WHERE id=#{fangId}")
    int updateKeyword(@Param("fangId") Long fangId, @Param("keyword") String keyword);


    @Update("UPDATE t_fang SET house_sub_type = #{houseSubType}, s_counts = #{sCounts}  , t_counts= #{tCounts}  ," +
            " c_counts = #{cCounts}  , w_counts = #{wCounts}  , yt_counts = #{ytCounts}  , orientation  = #{orientation}  , decorate = #{decorate}  ," +
            " estate_area = #{estateArea}  , real_area  = #{realArea}  , publish_price  = #{publishPrice}  , price_unit = #{priceUnit}  , " +
            " unit_price = #{unitPrice}  , transfer_price = #{transferPrice}  , bottom_price   = #{bottomPrice}  , resident = #{resident}  , " +
            " floor = #{floor}  , floor_counts   = #{floorCounts}  , structure_type = #{structureType}  , build_year = #{buildYear}  ," +
            " heating_type = #{heatingType}, has_elevator = #{hasElevator}, update_time = CURRENT_TIMESTAMP " +
            " WHERE id = #{id} and is_deleted =false")
    int updateFang(Fang fang);

    @Select("select * from t_fang where id = #{id}")
    Fang findFang(Long id);

    @Update("UPDATE t_fang_ext SET level = #{level}, showing = #{showing}, delegate_type = #{delegateType}, delegate_start = #{delegateStart},delegate_end = #{delegateEnd}, " +
            " status = #{status},source = #{source},certif_type = #{certifType},certif_no = #{certifNo},  certif_address = #{certifAddress}, taxes_willing = #{taxesWilling}, " +
            " commission_willing = #{commissionWilling}, purchase_price = #{purchasePrice}, purchase_date= #{purchaseDate}, is_only = #{isOnly},over_years= #{overYears}, mortgage =  #{mortgage}, note = #{note}  " +
            " WHERE fang_id = #{fangId}")
    int updateFangExtByFangId(FangExt fangExt);

    @Select("select * from t_fang_ext where fang_id = #{fangId}")
    FangExt findFangExtByFangId(Long fangId);

    @Update("UPDATE t_fang set update_time = CURRENT_TIMESTAMP where id = #{id} and is_deleted = false")
    int updateTime(Long id);

    @Select("select * from t_fang where licence_id = #{licenceId}")
    Fang findFangByLicenceId(Long licenceId);
}
