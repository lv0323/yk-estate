package com.lyun.estate.rest.message;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.message.def.MessageContentType;
import com.lyun.estate.biz.message.entity.Message;
import com.lyun.estate.biz.message.entity.MessageResource;
import com.lyun.estate.biz.message.entity.MessageSummaryResource;
import com.lyun.estate.biz.message.service.MessageService;
import com.lyun.estate.biz.mq.consumer.MessageConsumer;
import com.lyun.estate.biz.mq.producer.MessageProducer;
import com.lyun.estate.biz.report.engine.ReportEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jesse on 2017/1/19.
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageConsumer messageConsumer;

    @Autowired
    private ReportEngine reportEngine;

    @GetMapping("/summary")
    List<MessageSummaryResource> getMessageCounter() {
        return messageService.getMessageSummary();
    }

    @GetMapping("/show")
    List<MessageResource> getMessage(@RequestParam(required = true) Long senderId,
                                     @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return messageService.getMessage(senderId, pageBounds);
    }

    //TODO 不在rest提供API
    @PostMapping("/produce")
    boolean produceMessage(@RequestParam(required = true) String title, @RequestParam(required = false) String summary, @RequestParam(required = true) String content,
                           @RequestParam(required = true) MessageContentType contentType, @RequestParam(required = true) Long senderId, @RequestParam(required = true) Long receiverId) {
        Message message = new Message();
        message.setTitle(title);
        message.setSummary(summary);
        message.setContent(content);
        message.setContentType(contentType);
        message.setSenderId(senderId);
        message.setReceiverId(receiverId);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @PostMapping("/produce/fang")
    boolean produceFangMessage(@RequestParam(required = true) Long senderId,
                               @RequestParam(required = true) Long receiverId,
                               @RequestParam(required = true) Long fangId,
                               @RequestParam(required = true) String title, @RequestParam(required = false) String summary) {
        Message message = messageService.generateSimpleFangMessage(receiverId, fangId, title, summary);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @PostMapping("/produce/report")
    boolean produceReportMessage(@RequestParam(required = true) Long senderId,
                                 @RequestParam(required = true) Long receiverId,
                                 @RequestParam(required = true) Long reportId,
                                 @RequestParam(required = true) String title, @RequestParam(required = false) String summary) {
        Message message = messageService.generateSimpleReportMessage(receiverId, reportId, title, summary);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @PostMapping("/produce/text")
    boolean produceTextMessage(@RequestParam(required = true) Long senderId,
                               @RequestParam(required = true) Long receiverId,
                               @RequestParam(required = true) String content,
                               @RequestParam(required = true) String title, @RequestParam(required = false) String summary) {
        Message message = messageService.generateSimplePhotoArticleMessage(receiverId, content, title, summary);
        return messageProducer.send(message);
    }

    //TODO 不在rest提供API
    @GetMapping("/consume")
    boolean consumeMessage() {
        return messageConsumer.receive();
    }

//    @GetMapping("/report/test")
//    String reportTest(@RequestParam(required = true) String reportName, Map param) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//        param.put("receiverId",3);
//        return reportEngine.report(reportName, null, param, MessageCounterResource.class);
//    }
//
//    @GetMapping("/report/test2")
//    void reportTest2(@RequestParam(required = true) String reportName, Map param, HttpServletResponse response) throws SQLException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//        param.put("receiverId",3);
//        reportEngine.report(reportName, null, param, response.getOutputStream(), MessageCounterResource.class);
//    }

    @GetMapping("/report/test")
    String reportTest(@RequestParam(required = true) String reportName, Map param) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        param.put("receiverId", 3);
        return reportEngine.report(reportName, null, param);
    }

    @GetMapping("/report/test2")
    void reportTest2(@RequestParam(required = true) String reportName, Map param, HttpServletResponse response) throws SQLException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        param.put("receiverId", 3);
        reportEngine.report(reportName, null, param, response.getOutputStream());
    }

    @GetMapping("/report/test3")
    void reportTest3(@RequestParam(required = true) String reportName, Map param, HttpServletResponse response) throws SQLException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
//        response.setContentType("text/csv");
//        response.setHeader("Content-Disposition", "attachment; filename=\""
//                + URLEncoder.encode("测试", "UTF-8") + ".csv\"");
        param.put("receiverId", 3);
        reportEngine.exportCsv(reportName, null, param, response.getOutputStream(), MessageSummaryResource.class);
    }
}
