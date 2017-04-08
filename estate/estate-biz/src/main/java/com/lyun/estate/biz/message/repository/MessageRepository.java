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

    @SelectProvider(type = MessageSqlProvider.class, method = "getMessage")
    PageList<MessageResource> getMessage(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId,
                                         @Param("lastMessageId") Long lastMessageId, PageBounds pageBounds);

    @UpdateProvider(type = MessageSqlProvider.class, method = "updateToRead")
    int updateToRead(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId,
                     @Param("lastMessageId") Long lastMessageId);

    @InsertProvider(type = MessageSqlProvider.class, method = "createMessage")
    @Options(useGeneratedKeys = true)
    int createMessage(Message message);

    @Select("SELECT * FROM t_message WHERE id = #{id}")
    Message findOne(Long id);

    @Select("SELECT  ms.receiver_id,  ms.sender_id,  sender.real_name AS sender_name\n" +
            "FROM ( SELECT DISTINCT sender_id, message.receiver_id FROM t_message message WHERE message.receiver_id = #{receiverId}) ms\n" +
            "LEFT JOIN t_user sender ON sender.id = ms.sender_id")
    List<MessageSummaryResource> getDistinctSender(Long receiverId);

    @Select("SELECT * FROM t_message WHERE receiver_id = #{receiverId} AND sender_id = #{senderId} ORDER BY id DESC LIMIT 1")
    Message getLastMessage(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId);

    @Select("SELECT count(1) FROM t_message WHERE receiver_id =#{receiverId} AND sender_id = #{senderId} AND status = 'UNREAD';")
    int getUnReadCount(@Param("receiverId") Long receiverId, @Param("senderId") Long senderId);
}
