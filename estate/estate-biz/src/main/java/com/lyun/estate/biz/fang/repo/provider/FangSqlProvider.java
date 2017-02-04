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
            SELECT("f.id, fd.title, f.biz_type, f.total_price, f.price_unit, f.s_counts,f.t_counts,f.estate_area,f.orientation,f.process, c.name as xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }

    public String findDetailById() {
        return new SQL() {{
            SELECT("f.*,  fd.title,  fe.showing,  fe.purchase_date,  c.name AS xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.id")
                    .LEFT_OUTER_JOIN("t_fang_ext fe ON f.id = fe.fang_id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }

    public String findSummaryBySelector(FangSelector selector) {
        return new SQL() {{
            SELECT("  f.id,  f.ranking,  fd.title,  f.biz_type,  f.publish_price,  f.price_unit,  f.s_counts,  f.t_counts,  f.estate_area,  f.orientation,  f.process,  c.name AS xiao_qu_name");

            FROM("t_fang f ")
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
                LEFT_OUTER_JOIN("t_line_station_rel lsr ON lsr.station_id = csr.id");
            }
            WHERE("c.city_id = #{cityId}");
            WHERE("f.biz_type = #{bizType}");

            WHERE_IF("f.s_counts IN (" + Joiner.on(",").skipNulls().join(selector.getsCounts()) + ")",
                    selector.getsCounts() != null && !selector.getsCounts().isEmpty());

            WHERE_IF("f.orientation IN (" + Joiner.on(",").skipNulls().join(selector.getOrientations()) + ")",
                    selector.getOrientations() != null && !selector.getOrientations().isEmpty());

            WHERE_IF("f.floor_type IN (" + Joiner.on(",").skipNulls().join(selector.getFloorTypes()) + ")",
                    selector.getFloorTypes() != null && !selector.getFloorTypes().isEmpty());

            WHERE_IF("f.structure_type IN (" + Joiner.on(",").skipNulls().join(selector.getStructureTypes()) + ")",
                    selector.getStructureTypes() != null && !selector.getStructureTypes().isEmpty());

            WHERE_IF("f.id NOT IN (" + Joiner.on(",").skipNulls().join(selector.getExcludeIds()) + ")",
                    selector.getExcludeIds() != null && !selector.getExcludeIds().isEmpty());

            WHERE_IF("f.has_elevator = #{hasElevator}", selector.getHasElevator() != null);
            WHERE_IF("f.estate_area >= #{minArea}", selector.getMinArea() != null);
            WHERE_IF("f.estate_area < #{maxArea}", selector.getMaxArea() != null);
            WHERE_IF("f.publish_price >= #{minPrice}", selector.getMinPrice() != null);
            WHERE_IF("f.publish_price < #{maxPrice}", selector.getMaxPrice() != null);
            WHERE_IF("f.build_year >= #{minYear}", selector.getMinYear() != null);
            WHERE_IF("f.build_year < #{maxYear}", selector.getMaxYear() != null);
            WHERE_IF("fe.is_only = #{isOnly}", selector.getIsOnly() != null);
            WHERE_IF("fe.over_years >= #{overYears}", selector.getOverYears() != null);

            WHERE_IF("fe.showing IN (" + Joiner.on(",").skipNulls().join(selector.getShowings()) + ")",
                    selector.getShowings() != null && !selector.getShowings().isEmpty());

            WHERE_IF("xq.id IN (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")",
                    selector.getXiaoQuIds() != null && !selector.getXiaoQuIds().isEmpty());

            WHERE_IF("c.near_line = #{nearLine}", selector.getNearLine() != null);
            WHERE_IF("c.sub_district_id = #{subDistrictId}", selector.getSubDistrictId() != null);
            WHERE_IF("dr.district_id = #{districtId}", selector.getDistrictId() != null);
            WHERE_IF("csr.station_id = #{stationId}", selector.getStationId() != null);
            WHERE_IF("lsr.line_id = #{lineId}", selector.getLineId() != null);

        }}.toString();
    }
}
