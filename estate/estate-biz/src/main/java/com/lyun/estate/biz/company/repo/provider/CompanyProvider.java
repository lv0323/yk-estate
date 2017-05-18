package com.lyun.estate.biz.company.repo.provider;

import com.lyun.estate.biz.company.domain.Company;
import com.lyun.estate.core.repo.SQL;

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
}
