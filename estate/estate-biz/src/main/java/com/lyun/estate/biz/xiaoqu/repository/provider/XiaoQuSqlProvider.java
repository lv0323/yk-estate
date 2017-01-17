package com.lyun.estate.biz.xiaoqu.repository.provider;

import com.google.common.base.Joiner;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.core.repo.SQL;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class XiaoQuSqlProvider {
    public String findSummary(XiaoQuSelector selector) {
        return new SQL() {{
            SELECT("xq.id, xq.ranking, xq.avg_price, c.name, dr.district_id, c.sub_district_id, c.builded_year, c.structure_type");
            SELECT("CASE WHEN xq.avg_price ISNULL THEN 1 ELSE 0 END AS ap_null");
            FROM("t_xiao_qu xq ");
            LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id");
            if (selector.getDistrictId() == null) {
                LEFT_OUTER_JOIN(
                        "t_district_rel dr ON c.sub_district_id = dr.sub_district_id AND dr.is_primary = 'Y'");
            } else {
                LEFT_OUTER_JOIN(
                        "t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
            }
            WHERE_IF("dr.district_id = #{districtId}", selector.getDistrictId() != null);
            WHERE_IF("c.sub_district_id = #{subDistrictId}", selector.getSubDistrictId() != null);
            WHERE_IF("xq.avg_price >= #{minPrice}", selector.getMinPrice() != null);
            WHERE_IF("xq.avg_price < #{maxPrice}", selector.getMaxPrice() != null);
            WHERE_IF("c.builded_year >= #{minYear}", selector.getMinYear() != null);
            WHERE_IF("c.builded_year < #{maxYear}", selector.getMaxYear() != null);
            if (selector.getStructureTypes() != null) {
                WHERE("c.structure_type in (" + Joiner.on(",").skipNulls().join(selector.getStructureTypes()) + ")");
            }
            if (selector.getXiaoQuIds() != null && !selector.getXiaoQuIds().isEmpty()) {
                WHERE("xq.id in (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")");
            }
            if (selector.getExcludeIds() != null && !selector.getExcludeIds().isEmpty()) {
                WHERE("xq.id not in (" + Joiner.on(",").skipNulls().join(selector.getExcludeIds()) + ")");
            }
        }}.toString();
    }


    public String findDetail() {
        return new SQL() {{
            SELECT("xq.id,\n" +
                    "  xq.ranking,\n" +
                    "  xq.avg_price,\n" +
                    "  c.name,\n" +
                    "  c.alias,\n" +
                    "  c.city_id,\n" +
                    "  c.sub_district_id,\n" +
                    "  dr.district_id,\n" +
                    "  c.near_line,\n" +
                    "  c.longitude,\n" +
                    "  c.latitude,\n" +
                    "  c.address,\n" +
                    "  c.developers,\n" +
                    "  c.structure_type,\n" +
                    "  c.builded_year,\n" +
                    "  c.develop_year,\n" +
                    "  c.property_company,\n" +
                    "  c.property_company_phone,\n" +
                    "  c.property_fee,\n" +
                    "  c.parking_space,\n" +
                    "  c.parking_rate,\n" +
                    "  c.parking_fee,\n" +
                    "  c.buildings,\n" +
                    "  c.houses,\n" +
                    "  c.container_rate,\n" +
                    "  c.green_rate");
            FROM("t_xiao_qu xq");
            LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id");
            LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id AND dr.is_primary = 'Y'");
            WHERE("xq.id = #{id}");
        }}.toString();
    }
}
