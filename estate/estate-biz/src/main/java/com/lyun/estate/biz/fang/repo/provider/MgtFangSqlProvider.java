package com.lyun.estate.biz.fang.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.fang.domian.MgtFangSelector;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.core.repo.SQL;
import com.lyun.estate.core.repo.SqlSupport;

import static com.lyun.estate.core.repo.SqlSupport.buildQMEnumsStr;
import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class MgtFangSqlProvider {

    public String saveFang(Fang fang) {
        return new SQL().INSERT_INTO("t_fang")
                .VALUES("biz_type", "#{bizType}")
                .VALUES("house_type", "#{houseType}")
                .VALUES("house_sub_type", "#{houseSubType}")
                .VALUES("licence_id", "#{licenceId}")
                .VALUES("xiao_qu_id", "#{xiaoQuId}")
                .VALUES("s_counts", "#{sCounts}")
                .VALUES("t_counts", "#{tCounts}")
                .VALUES("c_counts", "#{cCounts}")
                .VALUES("w_counts", "#{wCounts}")
                .VALUES("yt_counts", "#{ytCounts}")
                .VALUES("orientation", "#{orientation}")
                .VALUES("decorate", "#{decorate}")
                .VALUES("estate_area", "#{estateArea}")
                .VALUES_IF("real_area", "#{realArea}", nonNull(fang.getRealArea()))
                .VALUES("publish_price", "#{publishPrice}")
                .VALUES("price_unit", "#{priceUnit}")
                .VALUES("unit_price", "#{unitPrice}")
                .VALUES_IF("transfer_price", "#{transferPrice}", nonNull(fang.getTransferPrice()))
                .VALUES_IF("bottom_price", "#{bottomPrice}", nonNull(fang.getBottomPrice()))
                .VALUES_IF("resident", "#{resident}", nonNull(fang.getResident()))
                .VALUES("process", "#{process}")
                .VALUES("floor", "#{floor}")
                .VALUES("floor_counts", "#{floorCounts}")
                .VALUES("floor_type", "#{floorType}")
                .VALUES("structure_type", "#{structureType}")
                .VALUES("build_year", "#{buildYear}")
                .VALUES("heating_type", "#{heatingType}")
                .VALUES("has_elevator", "#{hasElevator}")
                .toString();
    }

    public String saveFangExt(FangExt fangExt) {
        return new SQL().INSERT_INTO("t_fang_ext")
                .VALUES("fang_id", "#{fangId}")
                .VALUES("level", "#{level}")
                .VALUES("showing", "#{showing}")
                .VALUES("delegate_type", "#{delegateType}")
                .VALUES_IF("delegate_start", "#{delegateStart}", nonNull(fangExt.getDelegateStart()))
                .VALUES_IF("delegate_end", "#{delegateEnd}", nonNull(fangExt.getDelegateEnd()))
                .VALUES("status", "#{status}")
                .VALUES("source", "#{source}")
                .VALUES("certif_type", "#{certifType}")
                .VALUES("certif_address", "#{certifAddress}")
                .VALUES_IF("certif_no", "#{certifNo}", nonNull(fangExt.getCertifNo()))
                .VALUES("property_type", "#{propertyType}")
                .VALUES("taxes_willing", "#{taxesWilling}")
                .VALUES("commission_willing", "#{commissionWilling}")
                .VALUES_IF("purchase_price", "#{purchasePrice}", nonNull(fangExt.getPurchasePrice()))
                .VALUES_IF("purchase_date", "#{purchaseDate}", nonNull(fangExt.getPurchaseDate()))
                .VALUES("is_only", "#{isOnly}")
                .VALUES("over_years", "#{overYears}")
                .VALUES_IF("mortgage", "#{mortgage}", nonNull(fangExt.getMortgage()))
                .VALUES_IF("note", "#{note}", nonNull(fangExt.getNote()))
                .toString();
    }

    public String saveFangInfoOwner(FangInfoOwner fangInfoOwner) {
        return new SQL().INSERT_INTO("t_fang_info_owner")
                .VALUES("fang_id", "#{fangId}")
                .VALUES("company_id", "#{companyId}")
                .VALUES("department_id", "#{departmentId}")
                .VALUES("employee_id", "#{employeeId}")
                .VALUES("reason", "#{reason}")
                .toString();
    }

    public String listSummary(MgtFangSelector selector) {
        SQL sql = new SQL().SELECT(
                " f.id,  f.biz_type,  f.licence_id,  f.publish_price,  f.price_unit, f.unit_price, f.estate_area, f.real_area,  f.process, f.sub_process, f.publish_time")
                .SELECT("  f.s_counts,  f.t_counts,  f.c_counts,  f.w_counts,  f.yt_counts,  f.floor,  f.floor_counts, f.decorate, f.orientation,  f.create_time")
                .SELECT("  xq.id  AS xiao_qu_id,  c.sub_district_id,  c.name AS xiao_qu_name,  c.near_line,  fe.delegate_start,  fe.is_only,  fe.over_years,  fe.showing")
                .SELECT("  CASE WHEN t_ff.max_follow_time ISNULL THEN 1 ELSE 0 END AS mftn, t_ff.max_follow_time")
                .FROM("t_fang f ")
                .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_fang_ext fe ON fe.fang_id = f.id");
        if (selector.getDistrictId() != null) {
            sql.LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
        }
        if (SqlSupport.hasNotNullElement(selector.getDepartmentIds()) || nonNull(selector.getEmployeeId())) {
            sql.LEFT_OUTER_JOIN("t_fang_info_owner fio ON f.id = fio.fang_id AND fio.is_deleted = FALSE");
        }
        sql.LEFT_OUTER_JOIN("( SELECT ff.fang_id,  max(ff.create_time) AS max_follow_time\n" +
                " FROM t_fang_follow ff WHERE ff.is_deleted = FALSE GROUP BY ff.fang_id ) AS t_ff ON t_ff.fang_id = f.id");
        if (isNull(selector.getFangId()) && isNull(selector.getLicenceId())) {
            sql.WHERE("c.city_id = #{cityId}");
        }
        sql.WHERE_IF("f.biz_type = #{bizType}", nonNull(selector.getBizType()));
        sql.WHERE_IF("f.xiao_qu_id = #{xiaoQuId}", nonNull(selector.getXiaoQuId()));
        sql.WHERE_IF("dr.district_id = #{districtId}", nonNull(selector.getDistrictId()));
        sql.WHERE_IF("c.sub_district_id = #{subDistrictId}", nonNull(selector.getSubDistrictId()));
        sql.WHERE_IF("f.house_type = #{houseType}", nonNull(selector.getHouseType()));
        sql.WHERE_IF("f.resident = #{resident}", nonNull(selector.getResident()));
        sql.WHERE_IF("f.decorate = #{decorate}", nonNull(selector.getDecorate()));
        sql.WHERE_IF("f.estate_area >= #{minArea}", nonNull(selector.getMinArea()));
        sql.WHERE_IF("f.estate_area < #{maxArea}", nonNull(selector.getMaxArea()));
        sql.WHERE_IF("f.s_counts = #{sCounts}", nonNull(selector.getsCounts()));
        sql.WHERE_IF("f.publish_price >= #{minPrice}", nonNull(selector.getMinPrice()));
        sql.WHERE_IF("f.publish_price < #{maxPrice}", nonNull(selector.getMaxPrice()));
        sql.WHERE_IF("fe.is_only = #{isOnly}", nonNull(selector.getIsOnly()));
        sql.WHERE_IF("fe.over_years >= #{overYears}", nonNull(selector.getOverYears()));
        if (SqlSupport.hasNotNullElement(selector.getDepartmentIds())) {
            sql.WHERE("fio.department_id IN (" + Joiner.on(",").skipNulls().join(selector.getDepartmentIds()) + ")");
        }
        sql.WHERE_IF("fio.employee_id  = #{employeeId}", nonNull(selector.getEmployeeId()));
        sql.WHERE_IF("fe.delegate_type  = #{delegateType}", nonNull(selector.getDelegateType()));
        sql.WHERE_IF("fe.property_type  = #{propertyType}", nonNull(selector.getPropertyType()));
        sql.WHERE_IF("fe.certif_type  = #{certifType}", nonNull(selector.getCertifType()));
        sql.WHERE_IF("f.create_time >= #{minCreateTime}", nonNull(selector.getMinCreateTime()));
        sql.WHERE_IF("f.create_time < #{maxCreateTime}", nonNull(selector.getMaxCreateTime()));
        sql.WHERE_IF("fe.delegate_start >= #{minDelegateTime}", nonNull(selector.getMinDelegateTime()));
        sql.WHERE_IF("fe.delegate_end < #{maxDelegateTime}", nonNull(selector.getMaxDelegateTime()));
        sql.WHERE_IF("f.publish_time >= #{minPublishTime}", nonNull(selector.getMinPublishTime()));
        sql.WHERE_IF("f.publish_time < #{maxPublishTime}", nonNull(selector.getMaxPublishTime()));
        sql.WHERE_IF("t_ff.max_follow_time >= #{minFollowTime}", nonNull(selector.getMinFollowTime()));
        sql.WHERE_IF("t_ff.max_follow_time < #{maxFollowTime}", nonNull(selector.getMaxFollowTime()));
        sql.WHERE_IF("c.near_line = #{nearLine}", nonNull(selector.getNearLine()));
        if (hasNotNullElement(selector.getDecorates())) {
            sql.WHERE("f.decorate IN (" + buildQMEnumsStr(selector.getDecorates()) + ")");
        }
        if (hasNotNullElement(selector.getShowings())) {
            sql.WHERE("fe.showing IN (" + buildQMEnumsStr(selector.getShowings()) + ")");
        }
        sql.WHERE_IF("f.process =#{process}", nonNull(selector.getProcess()));
        sql.WHERE_IF("f.sub_process =#{subProcess}", nonNull(selector.getSubProcess()));
        sql.WHERE_IF("f.id =#{fangId}", nonNull(selector.getFangId()));
        sql.WHERE_IF("f.licence_id =#{licenceId}", nonNull(selector.getLicenceId()));

        if (isNull(selector.getProcess()) && isNull(selector.getFangId()) && isNull(selector.getLicenceId())) {
            //默认情况 delegate 和 publish
            sql.WHERE("f.process IN ('DELEGATE', 'PUBLISH')");
        }
        sql.WHERE("f.is_deleted =false");

        return sql.toString();
    }
}
