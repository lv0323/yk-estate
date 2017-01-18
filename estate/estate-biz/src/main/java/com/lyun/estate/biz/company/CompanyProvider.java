package com.lyun.estate.biz.company;


import com.lyun.estate.core.repo.SQL;

public class CompanyProvider {

    public String insert(Company company) {
        return new SQL().INSERT_INTO("t_company")
                .VALUES("name", "#{name}")
                .VALUES("short_name", "#{shortName}")
                .VALUES("license", "#{license}")
                .VALUES("address", "#{address}")
                .VALUES("introduction", "#{introduction}")
                .VALUES("secret_key", "#{secretKey}")
                .VALUES("start_date", "#{startDate}")
                .VALUES("end_date", "#{endDate}")
                .VALUES_IF("locked", "#{locked}", company.getLocked() != null).toString();
    }
}
