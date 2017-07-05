package com.lyun.estate.biz.xiaoqu.repository.provider;

import com.google.common.base.Strings;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.xiaoqu.entity.CommunityEntity;
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


    public String detail(Long xiaoQuId) {
        SQL sql = new SQL();
        sql.SELECT(
                "xq.*, c.name, c.alias, c.city_id, c.sub_district_id, c.near_line, c.longitude, c.latitude,c.address, c.developers, c.structure_type, c.builded_year, c.develop_year, c.property_company, c.property_company_phone," +
                        "  c.property_fee, c.parking_fee ,c.parking_space, c.parking_rate,  c.buildings ,c.houses,c.container_rate,c.green_rate, dr.district_id, sd.name as sub_district, d.name as district")
                .FROM("t_xiao_qu xq ")
                .LEFT_OUTER_JOIN(" t_community c on xq.community_id = c.id ")
                .LEFT_OUTER_JOIN(" t_sub_district sd on c.sub_district_id = sd.id")
                .LEFT_OUTER_JOIN("t_district_rel dr on dr.sub_district_id = c.sub_district_id AND dr.is_primary = 'Y' ")
                .LEFT_OUTER_JOIN("t_district d on dr.district_id = d.id")
                .WHERE("xq.id = #{xiaoQuId}");
        return sql.toString();
    }


    public String updateCommunity(CommunityEntity communityEntity) {
        SQL sql = new SQL().UPDATE("T_COMMUNITY ");
        sql.SET_IF("NAME = #{name}", !Strings.isNullOrEmpty(communityEntity.getName()));
        sql.SET_IF("ALIAS = #{alias}", communityEntity.getAlias() != null);
        sql.SET_IF("CITY_ID = #{cityId}", communityEntity.getCityId() > 0);
        sql.SET_IF("SUB_DISTRICT_ID = #{subDistrictId}", communityEntity.getSubDistrictId() > 0);
        sql.SET_IF("DEVELOPERS = #{developers}", !Strings.isNullOrEmpty(communityEntity.getDevelopers()));
        sql.SET_IF("STRUCTURE_TYPE = #{structureType.bits}", communityEntity.getStructureType() != null);
        sql.SET_IF("BUILDED_YEAR = #{buildedYear}", communityEntity.getBuildedYear() >= 0);
        sql.SET_IF("DEVELOP_YEAR = #{developYear}", communityEntity.getDevelopYear() >= 0);
        sql.SET_IF("PROPERTY_COMPANY = #{propertyCompany}", communityEntity.getPropertyCompany() != null);
        sql.SET_IF("PROPERTY_COMPANY_PHONE = #{propertyCompanyPhone}", communityEntity.getPropertyCompanyPhone() != null);
        sql.SET_IF("PROPERTY_FEE = #{propertyFee}", communityEntity.getPropertyFee() != null);
        sql.SET_IF("PARKING_SPACE = #{parkingSpace}", communityEntity.getParkingSpace() != null);
        sql.SET_IF("PARKING_RATE = #{parkingRate}", communityEntity.getParkingRate() != null);
        sql.SET_IF("PARKING_FEE = #{parkingFee}", communityEntity.getParkingFee() != null);
        sql.SET_IF("BUILDINGS = #{buildings}", communityEntity.getBuildings() >= 0);
        sql.SET_IF("HOUSES = #{houses}", communityEntity.getHouses() >= 0);
        sql.SET_IF("CONTAINER_RATE = #{containerRate}", communityEntity.getContainerRate() != null);
        sql.SET_IF("GREEN_RATE = #{greenRate}", communityEntity.getGreenRate() != null);
        sql.SET_IF("ADDRESS = #{address}", communityEntity.getAddress() != null);
        sql.SET("UPDATE_BY_ID = #{updateById}");
        sql.SET("UPDATE_TIME = NOW()");
        sql.SET("VERSION = VERSION +1");
        sql.WHERE("ID = #{id}");

        return sql.toString();
    }
}
