package com.lyun.estate.biz.information.respository;

import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.entity.Information;
import com.lyun.estate.biz.information.entity.InformationCounter;
import com.lyun.estate.biz.information.entity.InformationCounterResource;
import com.lyun.estate.biz.information.entity.InformationResource;
import com.lyun.estate.biz.information.respository.provider.InformationSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jesse on 2017/1/20.
 */
@Repository
public interface InformationRepository {
    @Select("select infoCounter.id, infoCounter.owner_id, infoCounter.create_time, sum(case information.business_type when 'C_INFO' then 1 else 0 end) as unread_c_info_count\n" +
            "  , sum(case information.business_type when 'C_MONTHLY_REPORT' then 1 else 0 end) as unread_c_m_report_count\n" +
            "  , sum(case information.business_type when 'NOTICE' then 1 else 0 end) as unread_notice_count\n" +
            " from t_information_counter infoCounter\n" +
            " left join t_information information on information.receiver = infoCounter.ownerId\n" +
            " where (information.id > infoCounter.c_info_index or information.id > infoCounter.c_monthly_report_index or information.id > infoCounter.notice_index)\n" +
            " and infoCounter.ownerId=#{receiverId}\n" +
            " group by infoCounter.id, infoCounter.ownerId, infoCounter.create_time")
    InformationCounterResource getInfoCounter(@Param("receiverId") Long receiverId);

    @Select("SELECT * FROM t_information information where information.receiver=#{receiverId} and information.business_type=#{business_type}")
    List<InformationResource> getInfoByBusinessType(@Param("receiverId") Long receiverId, @Param("businessType") InfoBusinessType businessType);

    @InsertProvider(type = InformationSqlProvider.class, method = "createInfo")
    int createInfo(Information information);

    @InsertProvider(type = InformationSqlProvider.class, method = "createInfoCounter")
    int createInfoCounter(InformationCounter informationCounter);

    @Select("SELECT * FROM t_information_counter WHERE ownerId=#{receiverId}")
    InformationCounter findInfoCounter(@Param("receiverId") Long receiverId);

    @InsertProvider(type = InformationSqlProvider.class, method = "updateInfoCounter")
    int updateInfoCounter(InformationCounter informationCounter);
}
