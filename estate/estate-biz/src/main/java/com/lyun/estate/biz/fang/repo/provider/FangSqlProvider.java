package com.lyun.estate.biz.fang.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.fang.entity.FangSelector;
import com.lyun.estate.core.repo.SQL;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class FangSqlProvider {
    public String findSummaryById() {
        return new SQL() {{
            SELECT("f.id, fd.title, fe.showing, fe.is_only, fe.over_years, f.ranking, f.publish_time,  f.biz_type, f.publish_price,  f.price_unit, f.unit_price, f.s_counts,  f.t_counts,  f.estate_area,  f.orientation, f.decorate, f.process, c.near_line, c.name AS xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.fang_id")
                    .LEFT_OUTER_JOIN("t_fang_ext fe on f.id = fe.fang_id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }

    public String findDetailById() {
        return new SQL() {{
            SELECT("f.*, fe.*,c.near_line, c.name AS xiao_qu_name, sd.name AS sub_district, d.name AS district")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_sub_district sd on sd.id = c.sub_district_id")
                    .LEFT_OUTER_JOIN("t_district_rel dr on dr.sub_district_id = c.sub_district_id AND dr.is_primary = 'Y'")
                    .LEFT_OUTER_JOIN("t_district d on dr.district_id = d.id")
                    .LEFT_OUTER_JOIN("t_fang_ext fe ON f.id = fe.fang_id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }

    public String findSummaryBySelector(FangSelector selector) {
        return new SQL() {{
            SELECT("f.id, fd.title, fe.showing, fe.is_only, fe.over_years, f.ranking, f.publish_time,  f.biz_type, f.publish_price,  f.price_unit, f.unit_price, f.s_counts,  f.t_counts,  f.estate_area,  f.orientation, f.decorate, f.process, c.near_line, c.name AS xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_ext fe ON fe.fang_id = f.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd ON f.id = fd.fang_id");
            if (selector.getDistrictId() != null) {
                LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
            }
            if (selector.getStationId() != null || selector.getLineId() != null) {
                LEFT_OUTER_JOIN("t_community_station_rel csr ON c.id = csr.community_id");
            }
            if (selector.getLineId() != null) {
                LEFT_OUTER_JOIN("t_line_station_rel lsr ON lsr.station_id = csr.station_id");
            }
            WHERE("c.city_id = #{cityId}");
            WHERE("f.biz_type = #{bizType}");
            if (selector.getsCounts() != null && !selector.getsCounts().isEmpty()) {
                WHERE("f.s_counts IN (" + Joiner.on(",").skipNulls().join(selector.getsCounts()) + ")");
            }
            if (selector.getOrientations() != null && !selector.getOrientations().isEmpty()) {
                StringBuilder sqlBuilder = new StringBuilder();
                selector.getOrientations().forEach(t -> sqlBuilder.append("'").append(t).append("',"));
                String sql = sqlBuilder.toString();
                sql = sql.substring(0, sql.length() - 1);
                WHERE("f.orientation IN (" + sql + ")");
            }
            if (selector.getDecorates() != null && !selector.getDecorates().isEmpty()) {
                StringBuilder sqlBuilder = new StringBuilder();
                selector.getDecorates().forEach(t -> sqlBuilder.append("'").append(t).append("',"));
                String sql = sqlBuilder.toString();
                sql = sql.substring(0, sql.length() - 1);
                WHERE("f.decorate IN (" + sql + ")");
            }
            if (selector.getFloorTypes() != null && !selector.getFloorTypes().isEmpty()) {
                StringBuilder sqlBuilder = new StringBuilder();
                selector.getFloorTypes().forEach(t -> sqlBuilder.append("'").append(t).append("',"));
                String sql = sqlBuilder.toString();
                sql = sql.substring(0, sql.length() - 1);
                WHERE("f.floor_type IN (" + sql + ")");
            }
            if (selector.getStructureTypes() != null && !selector.getStructureTypes().isEmpty()) {
                StringBuilder sqlBuilder = new StringBuilder();
                selector.getStructureTypes().forEach(t -> sqlBuilder.append("'").append(t).append("',"));
                String sql = sqlBuilder.toString();
                sql = sql.substring(0, sql.length() - 1);
                WHERE("f.structure_type IN (" + sql + ")");
            }
            if (selector.getExcludeIds() != null && !selector.getExcludeIds().isEmpty()) {
                WHERE("f.id NOT IN (" + Joiner.on(",").skipNulls().join(selector.getExcludeIds()) + ")");
            }

            WHERE_IF("f.has_elevator = #{hasElevator}", selector.getHasElevator() != null);
            WHERE_IF("f.estate_area >= #{minArea}", selector.getMinArea() != null);
            WHERE_IF("f.estate_area < #{maxArea}", selector.getMaxArea() != null);
            WHERE_IF("f.publish_price >= #{minPrice}", selector.getMinPrice() != null);
            WHERE_IF("f.publish_price < #{maxPrice}", selector.getMaxPrice() != null);
            WHERE_IF("f.build_year >= #{minYear}", selector.getMinYear() != null);
            WHERE_IF("f.build_year < #{maxYear}", selector.getMaxYear() != null);
            WHERE_IF("fe.is_only = #{isOnly}", selector.getIsOnly() != null);
            WHERE_IF("fe.over_years >= #{overYears}", selector.getOverYears() != null);

            if (selector.getShowings() != null && !selector.getShowings().isEmpty()) {
                StringBuilder sqlBuilder = new StringBuilder();
                selector.getShowings().forEach(t -> sqlBuilder.append("'").append(t).append("',"));
                String sql = sqlBuilder.toString();
                sql = sql.substring(0, sql.length() - 1);
                WHERE("fe.showing IN (" + sql + ")");
            }
            if (selector.getXiaoQuIds() != null && !selector.getXiaoQuIds().isEmpty()) {
                WHERE("xq.id IN (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")");
            }

            WHERE_IF("c.near_line = #{nearLine}", selector.getNearLine() != null);
            WHERE_IF("c.sub_district_id = #{subDistrictId}", selector.getSubDistrictId() != null);
            WHERE_IF("dr.district_id = #{districtId}", selector.getDistrictId() != null);
            WHERE_IF("csr.station_id = #{stationId}", selector.getStationId() != null);
            WHERE_IF("lsr.line_id = #{lineId}", selector.getLineId() != null);
            if (selector.getProcess() != null) {
                WHERE("f.process =#{process}");
            } else {
                WHERE("f.process <> 'DELEGATE'");
            }

        }}.toString();
    }
}
