package com.lyun.estate.biz.information.service;

import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;
import com.lyun.estate.biz.information.entity.InformationCounterResource;
import com.lyun.estate.biz.information.entity.InformationResource;
import com.lyun.estate.biz.information.respository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jesse on 2017/1/20.
 */
@Service
public class InformationService {
    @Autowired
    private InformationRepository informationRepository;

    public InformationCounterResource getInfoCounter(Long receiverId) {
        return null;
    }

    public InformationCounterResource getCurrentUserInfoCounter() {
        return getInfoCounter(null);
    }

    public List<InformationResource> getInfoByBusinessType(Long receiverId, InfoBusinessType businessType) {
        return null;
    }
    //TODO 会更新InfoCounter相应的值
    public List<InformationResource> getCurrentUserInfoByBusinessType(InfoBusinessType businessType) {
        return getInfoByBusinessType(null, businessType);
    }
    //TODO 如果用户没有InfoCounter会创建一个
    public boolean createInfo(String infoTitle, String infoSummary, String infoContent, InfoContentType contentType, InfoBusinessType businessType, Long sender, Long receiver) {
        return false;
    }

    public boolean createCurrentUserInfo(String infoTitle, String infoSummary, String infoContent, InfoContentType contentType, InfoBusinessType businessType, Long sender) {
        return createInfo(infoTitle, infoSummary, infoContent, contentType, businessType, sender, null);
    }
}
