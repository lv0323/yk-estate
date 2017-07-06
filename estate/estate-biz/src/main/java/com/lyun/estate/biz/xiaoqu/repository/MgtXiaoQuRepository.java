package com.lyun.estate.biz.xiaoqu.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.xiaoqu.entity.CommunityEntity;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuEntity;
import com.lyun.estate.biz.xiaoqu.repository.provider.MgtXiaoQuSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Repository
public interface MgtXiaoQuRepository {
    @Select("select xq.*,c.address, c.name from t_xiao_qu xq left join t_community c on xq.community_id = c.id where xq.id =#{id}")
    XiaoQu findOne(Long id);

    @SelectProvider(type = MgtXiaoQuSqlProvider.class, method = "list")
    PageList<MgtXiaoQuSummary> list(MgtXiaoQuFilter filter, PageBounds pageBounds);


    @SelectProvider(type = MgtXiaoQuSqlProvider.class, method = "detail")
    MgtXiaoQuDetail detail(Long xiaoQuId);


    @Insert("INSERT INTO T_XIAO_QU(COMMUNITY_ID, RANKING, AVG_PRICE, SELL_HOUSE_COUNT, RENT_HOUSE_COUNT) " +
            " VALUES (#{communityId}, #{ranking}, #{avgPrice}, #{sellHouseCount}, #{rentHouseCount})")
    @Options(useGeneratedKeys = true)
    int createXiaoQu(XiaoQuEntity xiaoQuEntity);

    @Insert("INSERT INTO T_COMMUNITY(NAME, ALIAS, CITY_ID, SUB_DISTRICT_ID, NEAR_LINE, LONGITUDE, LATITUDE, ADDRESS, DEVELOPERS, STRUCTURE_TYPE, BUILDED_YEAR, DEVELOP_YEAR, PROPERTY_COMPANY, PROPERTY_COMPANY_PHONE, PROPERTY_FEE, PARKING_SPACE, PARKING_FEE, PARKING_RATE, BUILDINGS, HOUSES, CONTAINER_RATE, GREEN_RATE, LJ_ID, CREATE_BY_ID, UPDATE_BY_ID, IS_DELETED, VERSION, NAME_KW, ALIAS_KW) " +
            "VALUES (#{name}, #{alias}, #{cityId}, #{subDistrictId}, #{nearLine}, #{longitude}, #{latitude}, #{address}, #{developers}, #{structureType}, #{buildedYear}, #{developYear}, #{propertyCompany}, #{propertyCompanyPhone}, #{propertyFee}, #{parkingSpace}, #{parkingFee}, #{parkingRate}, #{buildings}, #{houses}, #{containerRate}, #{greenRate}, #{ljId}, #{createById}, #{updateById}, #{isDeleted}, #{version}, #{nameKw}, #{aliasKw})")
    @Options(useGeneratedKeys = true)
    int createCommunity(CommunityEntity communityEntity);

    @UpdateProvider(type = MgtXiaoQuSqlProvider.class, method = "updateCommunity")
    int updateCommunity(CommunityEntity communityEntity);

    @Select("SELECT * FROM T_COMMUNITY WHERE ID = #{id}")
    CommunityEntity findOneById(Long id);
}
