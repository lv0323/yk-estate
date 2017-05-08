package com.lyun.estate.biz.event.processor;

import com.google.common.base.Strings;
import com.lyun.estate.biz.event.def.EventDefine;
import com.lyun.estate.biz.event.entity.EventProcess;
import com.lyun.estate.biz.event.service.EventService;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.favorite.service.FavoriteService;
import com.lyun.estate.biz.message.def.ContentType;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.service.AdminUserUtils;
import com.lyun.estate.biz.message.service.MessageService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeffrey on 2017-03-15.
 */
@Service
public class MessageProcessor implements EventProcessor {

    public static final String PROCESSOR_NAME = "messageProcessor";

    @Autowired
    private EventService eventService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private MgtFangService mgtFangService;


    @Override
    @Transactional
    public EventProcess process(EventProcess process) {
        if (process.getType() == EventDefine.Type.FANG_PUBLISH) {
            return processFangPublish(process);
        } else if (process.getType() == EventDefine.Type.FANG_PROCESS) {
            return processFangProcess(process);
        }
        return null;
    }

    EventProcess processFangPublish(EventProcess process) {
        eventService.lockProcess(process.getId());

        MgtFangTiny tiny = mgtFangService.getFangTiny(process.getDomainId());
        //只有售房才生成消息
        if (tiny.getBizType() == BizType.SELL) {
            List<Long> followerIds = favoriteService.getFollowerIds(tiny.getXiaoQuId(), DomainType.XIAO_QU);
            Message message = new Message()
                    .setContent(process.getContent())
                    .setContentType(ContentType.FANG_SUMMARY)
                    .setSenderId(AdminUserUtils.getSenderIdByContentType(ContentType.FANG_SUMMARY))
                    .setDomainId(process.getDomainId())
                    .setDomainType(process.getDomainType())
                    .setTitle("您关注的小区" + Strings.nullToEmpty(tiny.getXiaoQuName()) + "新上了一套房源");
            followerIds.forEach(it -> {
                        message.setReceiverId(it);
                        messageService.createMessage(message);
                    }
            );
        }
        return eventService.closeProcess(process.getId());
    }

    EventProcess processFangProcess(EventProcess process) {
        eventService.lockProcess(process.getId());
        List<Long> followerIds = favoriteService.getFollowerIds(process.getDomainId(), DomainType.FANG);
        MgtFangTiny tiny = mgtFangService.getFangTiny(process.getDomainId());
        Message message = new Message()
                .setContent(process.getContent())
                .setContentType(ContentType.FANG_PROCESS)
                .setSenderId(AdminUserUtils.getSenderIdByContentType(ContentType.FANG_PROCESS))
                .setDomainId(process.getDomainId())
                .setDomainType(process.getDomainType())
                .setTitle(
                        "您关注的房源" + Strings.nullToEmpty(tiny.getXiaoQuName())
                                + tiny.getsCounts() + "室" + tiny.gettCounts() + "厅已" + tiny.getProcess().getLabel());
        followerIds.forEach(it -> {
            message.setReceiverId(it);
            messageService.createMessage(message);
        });
        return eventService.closeProcess(process.getId());
    }


    @Override
    public String processorName() {
        return PROCESSOR_NAME;
    }
}
