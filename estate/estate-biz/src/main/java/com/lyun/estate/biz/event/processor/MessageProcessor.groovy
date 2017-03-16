package com.lyun.estate.biz.event.processor

import com.google.common.base.Strings
import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.EventProcess
import com.lyun.estate.biz.event.service.EventService
import com.lyun.estate.biz.fang.domian.MgtFangTiny
import com.lyun.estate.biz.favorite.service.FavoriteService
import com.lyun.estate.biz.message.def.ContentType
import com.lyun.estate.biz.message.entity.Message
import com.lyun.estate.biz.message.service.AdminUserUtils
import com.lyun.estate.biz.message.service.MessageService
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.biz.support.def.DomainType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
class MessageProcessor implements EventProcessor {

    public static final String PROCESSOR_NAME = 'messageProcessor'

    @Autowired
    EventService eventService

    @Autowired
    MessageService messageService

    @Autowired
    FavoriteService favoriteService

    @Autowired
    MgtFangService mgtFangService


    @Override
    @Transactional
    EventProcess process(EventProcess process) {
        if (process.type == EventDefine.Type.FANG_PUBLISH) {
            processFangPublish(process)
        } else if (process.type == EventDefine.Type.FANG_PROCESS) {
            processFangProcess(process)
        }
    }

    EventProcess processFangPublish(EventProcess process) {
        eventService.lockProcess(process.getId())

        MgtFangTiny tiny = mgtFangService.getFangTiny(process.domainId)
        //只有售房才生成消息
        if (tiny.bizType == BizType.SELL) {
            List<Long> followerIds = favoriteService.getFollowerIds(tiny.getXiaoQuId(), DomainType.XIAO_QU)
            Message message = new Message()
                    .setContent(process.content)
                    .setContentType(ContentType.FANG_SUMMARY)
                    .setSenderId(AdminUserUtils.getSenderIdByContentType(ContentType.FANG_SUMMARY))
                    .setDomainId(process.domainId)
                    .setDomainType(process.domainType)
                    .setTitle("您关注的小区" + Strings.nullToEmpty(tiny.houseLicence?.xiaoQuName) + "新上了一套房源")
            followerIds.forEach({
                message.setReceiverId(it)
                messageService.createMessage(message)
            })
        }
        eventService.closeProcess(process.getId())
    }

    EventProcess processFangProcess(EventProcess process) {
        eventService.lockProcess(process.getId())
        List<Long> followerIds = favoriteService.getFollowerIds(process.getDomainId(), DomainType.FANG)
        MgtFangTiny tiny = mgtFangService.getFangTiny(process.domainId)
        Message message = new Message()
                .setContent(process.content)
                .setContentType(ContentType.FANG_PROCESS)
                .setSenderId(AdminUserUtils.getSenderIdByContentType(ContentType.FANG_PROCESS))
                .setDomainId(process.domainId)
                .setDomainType(process.domainType)
                .setTitle(
                "您关注的房源" + Strings.nullToEmpty(tiny.houseLicence?.xiaoQuName)
                        + tiny.sCounts + "室" + tiny.tCounts + "厅已" + tiny.process.label)
        followerIds.forEach({
            message.setReceiverId(it)
            messageService.createMessage(message)
        })
        eventService.closeProcess(process.getId())
    }


    @Override
    String processorName() {
        return PROCESSOR_NAME
    }
}
