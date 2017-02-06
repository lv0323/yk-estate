package com.lyun.estate.biz.message.repository.provider;

import com.lyun.estate.biz.message.entity.Message;
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
            VALUES_IF("sender_id", "#{senderId}", message.getSenderId() != null);
            VALUES_IF("receiver_id", "#{receiverId}", message.getReceiverId() != null);
            VALUES_IF("status", "#{status}", message.getStatus() != null);
            VALUES("uuid", "#{uuid}");
        }}.toString();
    }
}
