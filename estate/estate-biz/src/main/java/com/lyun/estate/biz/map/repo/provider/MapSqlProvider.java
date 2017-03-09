package com.lyun.estate.biz.map.repo.provider;

import com.lyun.estate.core.repo.SQL;

/**
 * Created by Jeffrey on 2017-02-23.
 */
public class MapSqlProvider {
    public String findSellCommunityListByMap() {
        return new SQL()
                .SELECT("community.id,community.name,xiaoQu.avg_price,community.longitude,community.latitude,'XIAO_QU' as domain_type,xiaoQu.sell_house_count as building_counts")
                .FROM("t_community community")
                .LEFT_OUTER_JOIN("t_xiao_qu xiaoQu on xiaoQu.community_id = community.id")
                .WHERE("community.longitude >= #{minLongitude} and community.longitude <= #{maxLongitude} and community.latitude >= #{minLatitude} and community.latitude <= #{maxLatitude} and community.city_id=#{cityId} and xiaoQu.sell_house_count > 0")
                .toString();
    }

    public String findRentCommunityListByMap() {
        return new SQL()
                .SELECT("community.id,community.name,xiaoQu.avg_price,community.longitude,community.latitude,'XIAO_QU' as domain_type,xiaoQu.rent_house_count as building_counts")
                .FROM("t_community community")
                .LEFT_OUTER_JOIN("t_xiao_qu xiaoQu on xiaoQu.community_id = community.id")
                .WHERE("community.longitude >= #{minLongitude} and community.longitude <= #{maxLongitude} and community.latitude >= #{minLatitude} and community.latitude <= #{maxLatitude} and community.city_id=#{cityId} and xiaoQu.rent_house_count > 0")
                .toString();
    }
}
