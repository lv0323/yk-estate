package com.lyun.estate.biz.fang.repo.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.fang.domian.FangSelector;
import com.lyun.estate.biz.spec.fang.rest.def.IntPair;
import com.lyun.estate.core.repo.SQL;

import java.util.List;
import java.util.Objects;

import static com.lyun.estate.core.repo.SqlSupport.buildQMEnumsStr;
import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class FangSqlProvider {
    public String findSummaryById() {
        return new SQL()
                .SELECT("f.id, fd.title, fe.showing, fe.is_only, fe.over_years, f.ranking, f.publish_time,  f.biz_type, f.publish_price,  f.price_unit, f.unit_price, f.s_counts,  f.t_counts,  f.estate_area,  f.orientation, f.decorate, f.process, c.near_line, c.city_id, c.sub_district_id, c.name AS xiao_qu_name")
                .FROM("t_fang f ")
                .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.fang_id")
                .LEFT_OUTER_JOIN("t_fang_ext fe on f.id = fe.fang_id")
                .WHERE("f.id = #{fangId}")
                .WHERE("f.is_deleted = false")
                .toString();
    }

    public String findDetailById() {
        return new SQL()
                .SELECT("f.*, fe.*,c.near_line, c.city_id, c.name AS xiao_qu_name, c.longitude, c.latitude, sd.name AS sub_district, d.name AS district")
                .FROM("t_fang f ")
                .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_sub_district sd on sd.id = c.sub_district_id")
                .LEFT_OUTER_JOIN("t_district_rel dr on dr.sub_district_id = c.sub_district_id AND dr.is_primary = 'Y'")
                .LEFT_OUTER_JOIN("t_district d on dr.district_id = d.id")
                .LEFT_OUTER_JOIN("t_fang_ext fe ON f.id = fe.fang_id")
                .WHERE("f.id = #{fangId}")
                .WHERE("f.is_deleted = false")
                .toString();
    }

    public String findSummaryBySelector(FangSelector selector) {
        SQL sql = new SQL()
                .SELECT("f.id, fd.title, fe.showing, fe.is_only, fe.over_years, f.ranking, f.publish_time,  f.biz_type, f.publish_price,  f.price_unit, f.unit_price, f.s_counts,  f.t_counts,  f.estate_area,  f.orientation, f.decorate, f.process, c.near_line, c.city_id, c.sub_district_id, c.name AS xiao_qu_name")
                .FROM("t_fang f ")
                .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_fang_ext fe ON fe.fang_id = f.id")
                .LEFT_OUTER_JOIN("t_fang_descr fd ON f.id = fd.fang_id");
        if (selector.getDistrictId() != null) {
            sql.LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
        }
        if (selector.getStationId() != null || selector.getLineId() != null) {
            sql.LEFT_OUTER_JOIN("t_community_station_rel csr ON c.id = csr.community_id");
        }
        if (selector.getLineId() != null) {
            sql.LEFT_OUTER_JOIN("t_line_station_rel lsr ON lsr.station_id = csr.station_id");
        }
        sql.WHERE("c.city_id = #{cityId}");
        sql.WHERE("f.biz_type = #{bizType}");
        if (hasNotNullElement(selector.getsCounts())) {
            sql.WHERE("f.s_counts IN (" + Joiner.on(",").skipNulls().join(selector.getsCounts()) + ")");
        }
        if (hasNotNullElement(selector.getOrientations())) {
            sql.WHERE("f.orientation IN (" + buildQMEnumsStr(selector.getOrientations()) + ")");
        }
        if (hasNotNullElement(selector.getDecorates())) {
            sql.WHERE("f.decorate IN (" + buildQMEnumsStr(selector.getDecorates()) + ")");
        }
        if (hasNotNullElement(selector.getFloorTypes())) {
            sql.WHERE("f.floor_type IN (" + buildQMEnumsStr(selector.getFloorTypes()) + ")");
        }
        if (hasNotNullElement(selector.getStructureTypes())) {
            sql.WHERE("f.structure_type IN (" + buildQMEnumsStr(selector.getStructureTypes()) + ")");
        }
        if (hasNotNullElement(selector.getExcludeIds())) {
            sql.WHERE("f.id NOT IN (" + Joiner.on(",").skipNulls().join(selector.getExcludeIds()) + ")");
        }

        sql.WHERE_IF("f.has_elevator = #{hasElevator}", selector.getHasElevator() != null);

        if (hasNotNullElement(selector.getAreas())) {
            sql.WHERE(buildIntPairWhere("f.estate_area", selector.getAreas()));
        }

        if (hasNotNullElement(selector.getYears())) {
            sql.WHERE(buildIntPairWhere("f.build_year", selector.getYears()));
        }

        if (hasNotNullElement(selector.getHouseTypes())) {
            sql.WHERE("f.house_type IN (" + buildQMEnumsStr(selector.getHouseTypes()) + ")");
        }

        sql.WHERE_IF("f.publish_price >= #{minPrice}", selector.getMinPrice() != null);
        sql.WHERE_IF("f.publish_price < #{maxPrice}", selector.getMaxPrice() != null);
        sql.WHERE_IF("fe.is_only = #{isOnly}", selector.getIsOnly() != null);
        sql.WHERE_IF("fe.over_years >= #{overYears}", selector.getOverYears() != null);

        if (hasNotNullElement(selector.getShowings())) {
            sql.WHERE("fe.showing IN (" + buildQMEnumsStr(selector.getShowings()) + ")");
        }
        if (hasNotNullElement(selector.getXiaoQuIds())) {
            sql.WHERE("xq.id IN (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")");
        }

        sql.WHERE_IF("c.near_line = #{nearLine}", selector.getNearLine() != null);
        sql.WHERE_IF("c.sub_district_id = #{subDistrictId}", selector.getSubDistrictId() != null);
        sql.WHERE_IF("dr.district_id = #{districtId}", selector.getDistrictId() != null);
        sql.WHERE_IF("csr.station_id = #{stationId}", selector.getStationId() != null);
        sql.WHERE_IF("lsr.line_id = #{lineId}", selector.getLineId() != null);

        if (selector.getProcess() != null) {
            sql.WHERE("f.process =#{process}");
        } else {
            sql.WHERE("f.process <> 'DELEGATE'");
        }
        sql.WHERE("f.is_deleted = false");

        return sql.toString();
    }

    private String buildIntPairWhere(String column, List<IntPair> intPairs) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("(");
        final boolean[] isFirst = {true};
        intPairs.stream().filter(Objects::nonNull).forEach(a -> {
            if (isFirst[0]) {
                isFirst[0] = false;
            } else {
                sqlBuilder.append(" OR ");
            }
            sqlBuilder.append("(");
            if (a.getMin() != null) {
                sqlBuilder.append(column).append(" >= ").append(a.getMin());
            }
            if (a.getMin() != null && a.getMax() != null) {
                sqlBuilder.append(" AND ");
            }
            if (a.getMax() != null) {
                sqlBuilder.append(column).append(" < ").append(a.getMax());
            }
            sqlBuilder.append(")");
        });
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }
}
