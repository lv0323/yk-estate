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
            if (selector.getXiaoQuIds() != null) {
                WHERE("xq.id in (" + Joiner.on(",").skipNulls().join(selector.getXiaoQuIds()) + ")");
            }
        }}.toString();
    }
}
