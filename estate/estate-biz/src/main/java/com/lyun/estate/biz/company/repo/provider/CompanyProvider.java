package com.lyun.estate.biz.company.repo.provider;

import com.lyun.estate.biz.company.entity.Company;
import com.lyun.estate.core.repo.SQL;

import java.util.Map;
import java.util.Objects;

public class CompanyProvider {

    public String insert(Company company) {
        return new SQL()
                .INSERT_INTO("t_company")
                .VALUES("name", "#{name}")
                .VALUES("address", "#{address}")
                .VALUES("introduction", "#{introduction}")
                .VALUES("secret_key", "#{secretKey}")
                .VALUES("start_date", "#{startDate}")
                .VALUES("end_date", "#{endDate}")
                .VALUES("city_id", "#{cityId}")
                .VALUES("parent_id", "#{parentId}")
                .VALUES("abbr", "#{abbr}")
                .VALUES("type", "#{type}")
                .VALUES("status", "#{status}")
                .VALUES_IF("boss_id", "#{bossId}", company.getBossId() != null)
                .toString();
    }


    public String list(Map<String, Object> params) {
        return new SQL()
                .SELECT("c.*, c2.name as city_name")
                .FROM("t_company c")
                .LEFT_OUTER_JOIN("t_city c2  on c.city_id = c2.id")
                .WHERE_IF("c.city_id = #{cityId}", Objects.nonNull(params.get("cityId")))
                .WHERE_IF("c.parent_id = #{parentId}", Objects.nonNull(params.get("parentId")))
                .WHERE_IF("c.type = #{companyType}", Objects.nonNull(params.get("companyType")))
                .WHERE("c.status = 'ACTIVE'")
                .WHERE("c.is_deleted =FALSE ")
                .toString();
    }
}
