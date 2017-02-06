package com.lyun.estate.biz.message.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.repository.provider.MessageSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jesse on 2017/1/20.
 */
@Repository
public interface MessageRepository {

    @Select("select message.receiver_id,message.sender_id,message.real_name,message.unread_count,message.max_id as last_message_id,lastMessage.title as last_message_title\n" +
            "from (\n" +
            "       select ms.receiver_id,ms.sender_id,sender.real_name,sum(case when message.status='UNREAD' then 1 else 0 end) as unread_count,max(message.id) as max_id\n" +
            "       from (\n" +
            "              SELECT DISTINCT sender_id, message.receiver_id\n" +
            "              FROM t_message message\n" +
            "              WHERE message.receiver_id=#{receiverId}\n" +
            "            ) ms\n" +
            "         left join t_user sender on sender.id = ms.sender_id\n" +
            "         left join t_message message on message.sender_id = ms.sender_id\n" +
            "       group by ms.receiver_id,ms.sender_id,sender.real_name\n" +
            ") message\n" +
            "left join t_message lastMessage on lastMessage.id = message.max_id")
    List<MessageSummaryResource> getMessageSummaryResource(@Param("receiverId") Long receiverId);

    @Select("SELECT * FROM t_message message WHERE message.receiver_id=#{receiverId} and message.sender_id=#{senderId} order by message.create_time desc")
    PageList<MessageResource> getMessage(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId, PageBounds pageBounds);

    @Update("UPDATE t_message message SET status='READ' WHERE message.receiver_id=#{receiverId} and message.sender_id=#{senderId} and message.id <=#{lastMessageId}")
    int updateToRead(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId, @Param("lastMessageId") Long lastMessageId);

    @InsertProvider(type = MessageSqlProvider.class, method = "createMessage")
    @Options(useGeneratedKeys = true)
    int createMessage(Message message);

    @Select("SELECT * FROM t_message WHERE uuid=#{uuid}")
    Message getMessageByUUID(@Param("uuid") String uuid);
}
