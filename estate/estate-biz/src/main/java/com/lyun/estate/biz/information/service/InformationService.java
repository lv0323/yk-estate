package com.lyun.estate.biz.information.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;
import com.lyun.estate.biz.information.entity.Information;
import com.lyun.estate.biz.information.entity.InformationCounter;
import com.lyun.estate.biz.information.entity.InformationCounterResource;
import com.lyun.estate.biz.information.entity.InformationResource;
import com.lyun.estate.biz.information.repository.InformationRepository;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.core.supports.ExecutionContext;
import com.lyun.estate.core.supports.exceptions.EstateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.lyun.estate.core.supports.exceptions.ExCode.*;

/**
 * Created by jesse on 2017/1/20.
 */
@Service
public class InformationService {
    private static final Logger logger = LoggerFactory.getLogger(InformationService.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private FangService fangService;

    @Autowired
    private ExecutionContext executionContext;


    public InformationCounterResource getInfoCounter(Long receiverId) {
        return informationRepository.getInfoCounter(receiverId);
    }

    public InformationCounterResource getCurrentUserInfoCounter() {
        return getInfoCounter(Long.valueOf(executionContext.getUserId()));
    }

    @Transactional
    public List<InformationResource> getInfoByBusinessType(Long receiverId, InfoBusinessType businessType, PageBounds pageBounds) {
         /*  获取消息 */
        List<InformationResource> informationResourceList = informationRepository.getInfoByBusinessType(receiverId, businessType, pageBounds);
        if (CollectionUtils.isEmpty(informationResourceList)) {
            return informationResourceList;
        }
        /*  JSON转换 */
        List<InformationResource> resultList = informationResourceList.stream().map(t -> {
            switch (t.getContentType()) {
                case COMMUNITY:
                    FangSummary fang = fangService.getSummary(Long.valueOf(t.getInfoContent()));
                    if (fang == null) {
                        logger.warn("根据信息中存放的content[{}]无法获取有效房源", t.getInfoContent());
                        break;
                    }
                    try {
                        t.setData(mapper.writeValueAsString(fang));
                    } catch (JsonProcessingException e) {
                        throw new EstateException(JSON_ERROR, fang.toString(), e.getMessage());
                    }
                    break;
                case PHOTO_ARTICLE:
                    //TODO 具体长什么样
                    t.setData(t.getInfoContent());
                    break;
                default:
                    throw new EstateException(PARAM_ILLEGAL, "InfoContentType", t.getContentType());
            }
            return t;
        }).collect(Collectors.toList());
         /* 更新消息计数器 */
        InformationResource lastInformationResource = informationResourceList.stream().reduce((x, y) -> {
            if (x.getId() >= y.getId()) {
                return x;
            }
            return y;
        }).get();
        InformationCounter informationCounter = informationRepository.findInfoCounter(receiverId);
        if (informationCounter == null) {
            throw new EstateException(NOT_FOUND, receiverId, "InformationCounter");
        }
        switch (businessType) {
            case C_INFO:
                informationCounter.setCInfoIndex(lastInformationResource.getId());
                break;
            case C_MONTHLY_REPORT:
                informationCounter.setCMonthlyReportIndex(lastInformationResource.getId());
                break;
            case NOTICE:
                informationCounter.setNoticeIndex(lastInformationResource.getId());
                break;
            default:
                throw new EstateException(PARAM_ILLEGAL, "InfoBusinessType", businessType);
        }
        if (informationRepository.updateInfoCounter(informationCounter) != 1) {
            throw new EstateException(UPDATE_FAIL, "InformationCounter", informationCounter.toString());
        }
        return resultList;
    }

    @Transactional
    public List<InformationResource> getCurrentUserInfoByBusinessType(InfoBusinessType businessType, PageBounds pageBounds) {
        return getInfoByBusinessType(Long.valueOf(executionContext.getUserId()), businessType, pageBounds);
    }

    @Transactional
    public boolean createInfo(String infoTitle, String infoSummary, String infoContent, InfoContentType contentType, InfoBusinessType businessType, Long sender, Long receiver) {
        /* 创建info */
        Information information = new Information();
        information.setInfoTitle(infoTitle);
        information.setInfoSummary(infoSummary);
        information.setInfoContent(infoContent);
        information.setContentType(contentType);
        information.setBusinessType(businessType);
        information.setSender(sender);
        information.setReceiver(receiver);
        if (informationRepository.createInfo(information) != 1) {
            throw new EstateException(CREATE_FAIL, "Information", information.toString());
        }
        /* 首次创建info counter */
        InformationCounter informationCounter = informationRepository.findInfoCounter(receiver);
        if (informationCounter == null) {
            informationCounter = new InformationCounter();
            informationCounter.setOwnerId(receiver);
            if (informationRepository.createInfoCounter(informationCounter) != 1) {
                throw new EstateException(CREATE_FAIL, "InformationCounter", informationCounter.toString());
            }
        }
        return true;
    }

    @Transactional
    public boolean createCurrentUserInfo(String infoTitle, String infoSummary, String infoContent, InfoContentType contentType, InfoBusinessType businessType) {
        //TODO 默认sender
        return createInfo(infoTitle, infoSummary, infoContent, contentType, businessType, 1L, Long.valueOf(executionContext.getUserId()));
    }

}
