package com.lyun.estate.biz.message.repository.provider;

import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageCounter;
import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageSqlProvider {

    public String createMessage(Message message) {
        return new SQL(){{
            INSERT_INTO("t_message");
            VALUES_IF("title", "#{title}", !StringUtils.isEmpty(message.getTitle()));
            VALUES_IF("summary", "#{summary}", !StringUtils.isEmpty(message.getSummary()));
            VALUES_IF("content", "#{content}", !StringUtils.isEmpty(message.getContent()));
            VALUES_IF("content_type", "#{contentType}", message.getContentType() != null);
            VALUES_IF("business_type", "#{businessType}", message.getBusinessType() != null);
            VALUES_IF("sender_id", "#{senderId}", message.getSenderId() != null);
            VALUES_IF("receiver_id", "#{receiverId}", message.getReceiverId() != null);
            VALUES("uuid", "#{uuid}");
        }}.toString();
    }

    public String createMessageCounter(MessageCounter messageCounter) {
        return new SQL(){{
            INSERT_INTO("t_message_counter");
            VALUES_IF("owner_id", "#{ownerId}", messageCounter.getOwnerId() != null);
            VALUES_IF("c_ms_index", "#{CMsIndex}", messageCounter.getCMsIndex() != null);
            VALUES_IF("c_m_report_index", "#{CMReportIndex}", messageCounter.getCMReportIndex() != null);
            VALUES_IF("notice_index", "#{noticeIndex}", messageCounter.getNoticeIndex() != null);
        }}.toString();
    }

    public String updateMessageCounter(MessageCounter messageCounter) {
        return new SQL(){{
            UPDATE("t_message_counter");
            SET_IF("owner_id=#{ownerId}",  messageCounter.getOwnerId() != null);
            SET_IF("c_ms_index=#{CMsIndex}",  messageCounter.getCMsIndex() != null);
            SET_IF("c_m_report_index=#{CMReportIndex}", messageCounter.getCMReportIndex() != null);
            SET_IF("notice_index=#{noticeIndex}", messageCounter.getNoticeIndex() != null);
            SET("update_time=now()");
            WHERE("id=#{id}");
        }}.toString();
    }
}
