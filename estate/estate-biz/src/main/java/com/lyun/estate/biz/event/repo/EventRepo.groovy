package com.lyun.estate.biz.event.repo

import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.entity.EventProcess
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

/**
 * Created by Jeffrey on 2017-03-14.
 */
interface EventRepo {

    @Insert('INSERT INTO t_event(uuid, type, domain_id, domain_type, content ) VALUES (#{uuid}, #{type}, #{domainId}, #{domainType}, #{content})')
    @Options(useGeneratedKeys = true)
    int saveEvent(Event event)

    @Select('SELECT * FROM t_event WHERE id =#{id}')
    Event findEvent(long id)

    @Insert('INSERT INTO t_event_process (uuid, type, domain_id, domain_type, content, processor, status ) VALUES (#{uuid}, #{type}, #{domainId}, #{domainType}, #{content}, #{processor}, #{status})')
    @Options(useGeneratedKeys = true)
    int saveProcess(EventProcess eventProcess)

    @Select('SELECT * FROM t_event_process WHERE id =#{processId}')
    EventProcess findProcess(long processId)

    @Update("UPDATE t_event_process SET status = 'CLOSED', update_time = now() WHERE id = #{processId}")
    int closeProcess(long processId)

    @Select('SELECT * FROM t_event_process WHERE id =#{processId} FOR UPDATE')
    EventProcess selectProcessForUpdate(long processId)
}