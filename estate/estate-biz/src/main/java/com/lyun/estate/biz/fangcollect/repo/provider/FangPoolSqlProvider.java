package com.lyun.estate.biz.fangcollect.repo.provider;

import com.lyun.estate.biz.fangcollect.domain.FangPoolSelector;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.core.repo.SQL;

import static java.util.Objects.nonNull;

public class FangPoolSqlProvider {

    public String createFangPool(FangPool fangPool) {
        return new SQL().INSERT_INTO("t_fang_pool")
                .VALUES("url", "#{url}")
                .VALUES("third_party_id", "#{thirdPartyId}")
                .VALUES("header", "#{header}")
                .VALUES("biz_type", "#{bizType}")
                .VALUES("house_sub_type", "#{houseSubType}")
                .VALUES("city_id", "#{cityId}")
                .VALUES("district_id", "#{districtId}")
                .VALUES("xiao_qu_name", "#{xiaoQuName}")
                .VALUES_IF("address", "#{address}", nonNull(fangPool.getAddress()))
                .VALUES_IF("house_type", "#{houseType}" ,nonNull(fangPool.getHouseType()))
                .VALUES_IF("house_sub_type", "#{houseSubType}" ,nonNull(fangPool.getHouseSubType()))
                .VALUES_IF("s_counts", "#{sCounts}", nonNull(fangPool.getsCounts()))
                .VALUES_IF("t_counts", "#{tCounts}", nonNull(fangPool.gettCounts()))
                .VALUES_IF("c_counts", "#{cCounts}", nonNull(fangPool.getcCounts()))
                .VALUES_IF("w_counts", "#{wCounts}", nonNull(fangPool.getwCounts()))
                .VALUES_IF("yt_counts", "#{ytCounts}", nonNull(fangPool.getYtCounts()))
                .VALUES_IF("orientation", "#{orientation}", nonNull(fangPool.getOrientation()))
                .VALUES_IF("decorate", "#{decorate}", nonNull(fangPool.getDecorate()))
                .VALUES_IF("estate_area", "#{estateArea}", nonNull(fangPool.getEstateArea()))
                .VALUES_IF("real_area", "#{realArea}", nonNull(fangPool.getRealArea()))
                .VALUES_IF("publish_price", "#{publishPrice}", nonNull(fangPool.getPublishPrice()))
                .VALUES_IF("price_unit", "#{priceUnit}", nonNull(fangPool.getPriceUnit()))
                .VALUES_IF("unit_price", "#{unitPrice}", nonNull(fangPool.getUnitPrice()))
                .VALUES_IF("floor", "#{floor}", nonNull(fangPool.getFloor()))
                .VALUES_IF("floor_counts", "#{floorCounts}", nonNull(fangPool.getFloorCounts()))
                .VALUES_IF("floor_type", "#{floorType}", nonNull(fangPool.getFloorType()))
                .VALUES_IF("structure_type", "#{structureType}", nonNull(fangPool.getStructureType()))
                .VALUES_IF("build_year", "#{buildYear}", nonNull(fangPool.getBuildYear()))
                .VALUES_IF("heating_type", "#{heatingType}", nonNull(fangPool.getHeatingType()))
                .VALUES_IF("has_elevator", "#{hasElevator}",nonNull(fangPool.getHasElevator()))
                .VALUES_IF("resident", "#{resident}",nonNull(fangPool.getResident()))
                .VALUES_IF("create_time", "#{createTime}",nonNull(fangPool.getCreateTime()))
                .VALUES_IF("contact_name", "#{contactName}",nonNull(fangPool.getContactName()))
                .VALUES_IF("contact_mobile", "#{contactMobile}",nonNull(fangPool.getContactMobile()))
                .VALUES_IF("description", "#{description}",nonNull(fangPool.getDescription()))
                .VALUES_IF("image_path", "#{imagePath}",nonNull(fangPool.getImagePath()))
                .VALUES_IF("update_time", "#{updateTime}",nonNull(fangPool.getUpdateTime()))
                .VALUES_IF("ext_info", "#{extInfo}",nonNull(fangPool.getExtInfo()))
                .VALUES("fang_origin", "#{fangOrigin}")
                .toString();
    }

    public String listSummary(FangPoolSelector selector) {
        SQL sql = new SQL().SELECT(
                " f.id,  f.url,  f.third_party_id,  f.header,  f.biz_type,  f.city_id,  f.xiao_qu_name,  f.publish_price,  f.address,   f.house_type,  f.house_sub_type,  f.s_counts,  f.t_counts,  f.w_counts,  f.c_counts,  yt_counts," +
                        "f.orientation,  f.decorate,  f.estate_area,  f.real_area,  f.price_unit,  f.unit_price,   f.floor_counts,  f.floor,  f.floor_type,  f.structure_type,   f.build_year,   f.heating_type,  f.create_time," +
                        "f.contact_name,  f.contact_mobile,  f.description,  f.image_path,  f.update_time,  f.ext_info,  f.fang_origin,  f.district_id,  f.resident,  f.has_elevator")
                .FROM("t_fang_pool f");
        sql.WHERE_IF("f.district_id = #{districtId}", nonNull(selector.getDistrictId()));
        sql.WHERE_IF("f.biz_type = #{bizType}", nonNull(selector.getBizType()));
        sql.WHERE_IF("f.house_type = #{houseType}", nonNull(selector.getHouseType()));
        sql.WHERE_IF("f.s_counts = #{sCounts}", nonNull(selector.getsCounts()));
        sql.WHERE("f.city_id = #{cityId}");
        sql.WHERE("f.is_deleted =false");

        return sql.toString();
    }

}
