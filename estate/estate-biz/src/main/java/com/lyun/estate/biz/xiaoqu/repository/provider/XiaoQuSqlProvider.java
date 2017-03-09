package com.lyun.estate.biz.xiaoqu.repository.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.core.repo.SQL;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuSqlProvider {
    public String findSummaryBySelector(XiaoQuSelector selector) {
        SQL sql = new SQL().SELECT(
                "xq.id, xq.ranking, xq.avg_price, c.city_id, c.name, dr.district_id, c.sub_district_id, c.builded_year, c.structure_type")
                .SELECT("CASE WHEN xq.avg_price ISNULL THEN 1 ELSE 0 END AS ap_null")
                .FROM("t_xiao_qu xq ")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id");
        if (selector.getDistrictId() == null) {
            sql.LEFT_OUTER_JOIN(
                    "t_district_rel dr ON c.sub_district_id = dr.sub_district_id AND dr.is_primary = 'Y'");
        } else {
            sql.LEFT_OUTER_JOIN(
                    "t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
        }
        if (selector.getStationId() != null || selector.getLineId() != null) {
            sql.LEFT_OUTER_JOIN("t_community_station_rel csr ON c.id = csr.community_id");
        }
        if (selector.getLineId() != null) {
            sql.LEFT_OUTER_JOIN("t_line_station_rel lsr ON lsr.station_id = csr.station_id");
        }
        sql.WHERE_IF("dr.district_id = #{districtId}", selector.getDistrictId() != null);
        sql.WHERE_IF("c.sub_district_id = #{subDistrictId}", selector.getSubDistrictId() != null);
        sql.WHERE_IF("xq.avg_price >= #{minPrice}", selector.getMinPrice() != null);
        sql.WHERE_IF("xq.avg_price < #{maxPrice}", selector.getMaxPrice() != null);
        sql.WHERE_IF("c.builded_year >= #{minYear}", selector.getMinYear() != null);
        sql.WHERE_IF("c.builded_year < #{maxYear}", selector.getMaxYear() != null);
        if (selector.getStructureTypes() != null) {
            sql.WHERE("c.structure_type in (" + Joiner.on(",").skipNulls().join(selector.getStructureTypes()) + ")");
        }
        if (selector.getXiaoQuIds() != null && !selector.getXiaoQuIds().isEmpty()) {
            sql.WHERE("xq.id in (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")");
        }
        if (selector.getExcludeIds() != null && !selector.getExcludeIds().isEmpty()) {
            sql.WHERE("xq.id not in (" + Joiner.on(",").skipNulls().join(selector.getExcludeIds()) + ")");
        }
        sql.WHERE_IF("csr.station_id = #{stationId}", selector.getStationId() != null);
        sql.WHERE_IF("lsr.line_id = #{lineId}", selector.getLineId() != null);
        sql.WHERE("c.city_id = #{cityId}");
        return sql.toString();
    }


    public String findDetail() {
        return new SQL().SELECT("xq.id, xq.ranking, xq.avg_price, c.name, c.alias,  c.city_id,  c.sub_district_id,  " +
                "dr.district_id,  c.near_line,  c.longitude,  c.latitude,  c.address,  c.developers,  c.structure_type,  " +
                "c.builded_year,  c.develop_year,  c.property_company,  c.property_company_phone,  c.property_fee,  c.parking_space, " +
                " c.parking_rate,  c.parking_fee,  c.buildings,  c.houses,  c.container_rate,  c.green_rate")
                .FROM("t_xiao_qu xq")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id AND dr.is_primary = 'Y'")
                .WHERE("xq.id = #{id}")
                .toString();
    }
}
