package com.lyun.estate.biz.xiaoqu.repository.provider;

import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.core.repo.SQL;

/**
 * Created by Jeffrey on 2017-01-09.
 */
public class MgtXiaoQuSqlProvider {

    public String list(MgtXiaoQuFilter filter) {
        SQL sql = new SQL();
        sql.SELECT(
                "xq.id,  c.city_id,  c.name,  c.sub_district_id,  sd.name AS sub_district,  d.id    AS district_id,  d.name  AS district,  c.builded_year,  c.structure_type,  c.address")
                .FROM("t_xiao_qu xq ")
                .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                .LEFT_OUTER_JOIN("t_sub_district sd ON c.sub_district_id = sd.id");
        if (filter.getDistrictId() != null) {
            sql.LEFT_OUTER_JOIN("t_district_rel dr ON c.sub_district_id = dr.sub_district_id");
        } else {
            sql.LEFT_OUTER_JOIN("t_district_rel dr ON dr.sub_district_id = c.sub_district_id AND dr.is_primary = 'Y'");
        }
        sql.LEFT_OUTER_JOIN("t_district d ON dr.district_id = d.id");
        if (filter.getXiaoQuId() != null) {
            sql.WHERE("xq.id = #{xiaoQuId}");
        } else {
            sql.WHERE("c.city_id = #{cityId}");
            sql.WHERE_IF("dr.district_id = #{districtId}", filter.getDistrictId() != null);
            sql.WHERE_IF("c.sub_district_id = #{subDistrictId}", filter.getSubDistrictId() != null);
        }
        sql.ORDER_BY("xq.id ASC");
        return sql.toString();
    }

}
