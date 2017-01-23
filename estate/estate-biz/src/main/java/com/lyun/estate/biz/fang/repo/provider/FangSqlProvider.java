package com.lyun.estate.biz.fang.repo.provider;

import com.lyun.estate.core.repo.SQL;

import java.util.Map;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class FangSqlProvider {
    public String findSummaryById(Map<String, Object> param) {
        return new SQL() {{
            SELECT("f.id, fd.title, f.biz_type, f.total_price, f.price_unit, f.s_counts,f.t_counts,f.estate_area,f.orientation,f.process, c.name as xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }

    public String findDetailById(Map<String, Object> param) {
        return new SQL() {{
            SELECT("  f.*,  fd.title,  fe.showing,  fe.purchase_date,  c.name AS xiao_qu_name")
                    .FROM("t_fang f ")
                    .LEFT_OUTER_JOIN("t_xiao_qu xq ON f.xiao_qu_id = xq.id")
                    .LEFT_OUTER_JOIN("t_community c ON xq.community_id = c.id")
                    .LEFT_OUTER_JOIN("t_fang_descr fd on f.id = fd.id")
                    .LEFT_OUTER_JOIN("t_fang_ext fe ON f.id = fe.fang_id")
                    .WHERE("f.id = #{fangId}");
        }}.toString();
    }
}
