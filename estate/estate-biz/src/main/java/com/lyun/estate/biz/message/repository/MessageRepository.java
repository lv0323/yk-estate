package com.lyun.estate.biz.message.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.repository.provider.MessageSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jesse on 2017/1/20.
 */
@Repository
public interface MessageRepository {

    @Select("select message.receiver_id,message.sender_id,message.real_name,message.unread_count,message.max_id as last_message_id,lastMessage.title as last_message_title\n" +
            "from (\n" +
            "  select message.receiver_id,message.sender_id,sender.real_name,count(message.id) as unread_count,max(message.id) as max_id\n" +
            "  from t_message message\n" +
            "    left join t_user sender on sender.id = message.sender_id\n" +
            "  where message.receiver_id=#{receiverId} and message.status='UNREAD'\n" +
            "  group by message.receiver_id,message.sender_id,sender.real_name\n" +
            ") message\n" +
            "left join t_message lastMessage on lastMessage.id = message.max_id")
    List<MessageSummaryResource> getMessageSummaryResource(@Param("receiverId") Long receiverId);

    @Select("SELECT * FROM t_message message where message.receiver_id=#{receiverId} and message.sender_id=#{senderId} order by message.create_time desc")
    PageList<MessageResource> getMessage(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId, PageBounds pageBounds);

    @InsertProvider(type = MessageSqlProvider.class, method = "createMessage")
    @Options(useGeneratedKeys = true)
    int createMessage(Message message);

    @Select("SELECT * FROM t_message WHERE uuid=#{uuid}")
    Message getMessageByUUID(@Param("uuid") String uuid);
}
