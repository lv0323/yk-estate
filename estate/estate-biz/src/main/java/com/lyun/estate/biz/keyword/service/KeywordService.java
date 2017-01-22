package com.lyun.estate.biz.keyword.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.repository.KeywordRepository;
import com.lyun.estate.biz.spec.common.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Service
public class KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;


    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<KeywordBean> findContain(String keyword, Long cityId, ArrayList<DomainType> scopes, Integer limit) {
        List<KeywordBean> results = new ArrayList<>();
        limit = Optional.ofNullable(limit).orElse(Integer.MAX_VALUE);
        if (scopes == null || Strings.isNullOrEmpty(keyword) || limit <= 0) {
            return results;
        }
        Integer count = 0;
        for (DomainType domainType : scopes) {
            if (count >= limit) {
                break;
            }
            List<KeywordBean> keywords = getCityDomainBean(cityId, domainType);
            for (KeywordBean keywordBean : keywords) {
                if (count >= limit) {
                    break;
                }
                if (keywordBean.getKeyword().contains(keyword)) {
                    results.add(keywordBean);
                    count++;
                }
            }
        }
        return results;
    }

    private List<KeywordBean> getCityDomainBean(Long cityId, DomainType domainType) {
        if (domainType == DomainType.DISTRICT) {
            return keywordRepository.loadDistrict(cityId);
        } else if (domainType == DomainType.SUB_DISTRICT) {
            return keywordRepository.loadSubDistrict(cityId);
        } else if (domainType == DomainType.LINE) {
            return keywordRepository.loadLine(cityId);
        } else if (domainType == DomainType.STATION) {
            return keywordRepository.loadStation(cityId);
        } else if (domainType == DomainType.XIAO_QU) {
            return keywordRepository.loadXiaoQu(cityId);
        } else {
            return new ArrayList<>();
        }
    }

    public List<KeywordBean> findNameMatch(String keyword, Long cityId, ArrayList<DomainType> scopes, Integer limit) {
        List<KeywordBean> results = new ArrayList<>();
        limit = Optional.ofNullable(limit).orElse(Integer.MAX_VALUE);
        if (scopes == null || Strings.isNullOrEmpty(keyword) || limit <= 0) {
            return results;
        }
        Integer count = 0;
        for (DomainType domainType : scopes) {
            if (count >= limit) {
                break;
            }
            List<KeywordBean> keywords = getCityDomainBean(cityId, domainType);
            for (KeywordBean keywordBean : keywords) {
                if (count >= limit) {
                    break;
                }
                if (keywordBean.getName().equals(keyword)) {
                    results.add(keywordBean);
                    count++;
                }
            }
        }
        return results;
    }
}
