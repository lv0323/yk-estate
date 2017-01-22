package com.lyun.estate.biz.information.repository.provider;

import com.lyun.estate.biz.information.entity.Information;
import com.lyun.estate.biz.information.entity.InformationCounter;
import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by jesse on 2017/1/20.
 */
public class InformationSqlProvider {

    public String createInfo(Information information) {
        return new SQL(){{
            INSERT_INTO("t_information");
            VALUES_IF("info_title", "#{infoTitle}", !StringUtils.isEmpty(information.getInfoTitle()));
            VALUES_IF("info_summary", "#{infoSummary}", !StringUtils.isEmpty(information.getInfoSummary()));
            VALUES_IF("info_content", "#{infoContent}", !StringUtils.isEmpty(information.getInfoContent()));
            VALUES_IF("content_type", "#{contentType}", information.getContentType() != null);
            VALUES_IF("business_type", "#{businessType}", information.getBusinessType() != null);
            VALUES_IF("sender", "#{sender}", information.getSender() != null);
            VALUES_IF("receiver", "#{receiver}", information.getReceiver() != null);
        }}.toString();
    }

    public String createInfoCounter(InformationCounter informationCounter) {
        return new SQL(){{
            INSERT_INTO("t_information_counter");
            VALUES_IF("owner_id", "#{ownerId}", informationCounter.getOwnerId() != null);
            VALUES_IF("c_info_index", "#{CInfoIndex}", informationCounter.getCInfoIndex() != null);
            VALUES_IF("c_monthly_report_index", "#{CMonthlyReportIndex}", informationCounter.getCMonthlyReportIndex() != null);
            VALUES_IF("notice_index", "#{noticeIndex}", informationCounter.getNoticeIndex() != null);
        }}.toString();
    }

    public String updateInfoCounter(InformationCounter informationCounter) {
        return new SQL(){{
            UPDATE("t_information_counter");
            SET_IF("owner_id=#{ownerId}",  informationCounter.getOwnerId() != null);
            SET_IF("c_info_index=#{CInfoIndex}",  informationCounter.getCInfoIndex() != null);
            SET_IF("c_monthly_report_index=#{CMonthlyReportIndex}", informationCounter.getCMonthlyReportIndex() != null);
            SET_IF("notice_index=#{noticeIndex}", informationCounter.getNoticeIndex() != null);
            SET("update_time=now()");
            WHERE("id=#{id}");
        }}.toString();
    }
}
