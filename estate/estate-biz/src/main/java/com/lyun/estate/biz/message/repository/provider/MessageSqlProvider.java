package com.lyun.estate.biz.message.repository.provider;

import com.lyun.estate.biz.message.entity.EventMessage;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.core.repo.SQL;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * Created by jesse on 2017/1/20.
 */
public class MessageSqlProvider {

    public String createMessage(Message message) {
        return new SQL() {{
            INSERT_INTO("t_message");
            VALUES_IF("title", "#{title}", !StringUtils.isEmpty(message.getTitle()));
            VALUES_IF("summary", "#{summary}", !StringUtils.isEmpty(message.getSummary()));
            VALUES_IF("domain_id", "#{domainId}", !StringUtils.isEmpty(message.getDomainId()));
            VALUES_IF("domain_type", "#{domainType}", message.getDomainType() != null);
            VALUES_IF("sender_id", "#{senderId}", message.getSenderId() != null);
            VALUES_IF("receiver_id", "#{receiverId}", message.getReceiverId() != null);
            VALUES_IF("status", "#{status}", message.getStatus() != null);
        }}.toString();
    }

    public String createEventMessage(EventMessage eventMessage) {
        return new SQL() {{
            INSERT_INTO("t_event_message");
            VALUES_IF("title", "#{title}", !StringUtils.isEmpty(eventMessage.getTitle()));
            VALUES_IF("domain_id", "#{domainId}", !StringUtils.isEmpty(eventMessage.getDomainId()));
            VALUES_IF("domain_type", "#{domainType}", eventMessage.getDomainType() != null);
            VALUES_IF("uuid", "#{uuid}", eventMessage.getUuid() != null);
        }}.toString();
    }

    public String getMessage(Map<String, Object> params) {
        Long receiverId = (Long) params.get("receiverId");
        return new SQL() {{
            SELECT("*")
                    .FROM("t_message")
                    .WHERE("receiver_id = #{receiverId}")
                    .WHERE("sender_id = #{senderId}")
                    .WHERE_IF("id <= #{lastMessageId}", Objects.nonNull(params.get("lastMessageId")))
                    .ORDER_BY("create_time DESC");
        }}.toString();
    }

    public String updateToRead(Map<String, Object> params) {
        return new SQL() {{
            UPDATE("t_message")
                    .SET("status = 'READ'")
                    .SET("update_time = now()")
                    .WHERE("receiver_id = #{receiverId}")
                    .WHERE("sender_id = #{senderId}")
                    .WHERE("status <> 'READ'")
                    .WHERE_IF("id <= #{lastMessageId}", Objects.nonNull(params.get("lastMessageId")));
        }}.toString();

    }
}
