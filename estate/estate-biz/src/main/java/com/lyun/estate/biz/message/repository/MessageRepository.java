package com.lyun.estate.biz.message.repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.message.def.MessageBusinessType;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageCounter;
import com.lyun.estate.biz.message.entity.MessageCounterResource;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.repository.provider.MessageSqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by jesse on 2017/1/20.
 */
@Repository
public interface MessageRepository {
    @Select("select messageCounter.id, messageCounter.owner_id, messageCounter.create_time, sum(case when message.business_type='C_MS' then 1 else 0 end) as u_c_ms_count\n" +
            "  , sum(case when message.business_type='C_M_REPORT' then 1 else 0 end) as u_c_m_report_count\n" +
            "  , sum(case when message.business_type='NOTICE' then 1 else 0 end) as u_notice_count\n" +
            " from t_message_counter messageCounter\n" +
            " left join t_message message on message.receiver_id = messageCounter.owner_id\n" +
            " where ((message.business_type='C_MS' and message.id > messageCounter.c_ms_index) or (message.business_type='C_M_REPORT' and message.id > messageCounter.c_m_report_index) or\n" +
            "       (message.business_type='NOTICE' and message.id > messageCounter.notice_index))\n" +
            " and messageCounter.owner_id=#{receiverId}\n" +
            " group by messageCounter.id, messageCounter.owner_id, messageCounter.create_time")
    MessageCounterResource getMessageCounterResource(@Param("receiverId") Long receiverId);

    @Select("SELECT * FROM t_message_counter WHERE owner_id=#{receiverId}")
    MessageCounter getMessageCounter(@Param("receiverId") Long receiverId);

    @Select("SELECT * FROM t_message message where message.receiver_id=#{receiverId} and message.business_type=#{businessType} order by message.create_time desc")
    PageList<MessageResource> getMessage(@Param("receiverId") Long receiverId, @Param("businessType") MessageBusinessType businessType, PageBounds pageBounds);

    @InsertProvider(type = MessageSqlProvider.class, method = "createMessage")
    @Options(useGeneratedKeys = true)
    int createMessage(Message message);

    @InsertProvider(type = MessageSqlProvider.class, method = "createMessageCounter")
    @Options(useGeneratedKeys = true)
    int createMessageCounter(MessageCounter messageCounter);

    @InsertProvider(type = MessageSqlProvider.class, method = "updateMessageCounter")
    int updateMessageCounter(MessageCounter messageCounter);

    @Select("SELECT * FROM t_message WHERE uuid=#{uuid}")
    Message getMessageByUUID(@Param("uuid") String uuid);
}
