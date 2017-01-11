package com.lyun.estate.biz.keyword.service;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.repository.KeywordRepository;
import com.lyun.estate.biz.spec.def.DomainType;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Service
public class KeywordService {

    private KeywordRepository keywordRepository;
    private LoadingCache<DomainType, List<KeywordBean>> domainKeywordsCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10,
                    TimeUnit.MINUTES)
            .build(new CacheLoader<DomainType, List<KeywordBean>>() {
                @Override
                public List<KeywordBean> load(DomainType key) throws Exception {
                    if (key == DomainType.XIAO_QU) {
                        return keywordRepository.loadXiaoQu();
                    } else if (key == DomainType.DISTRICT) {
                        return keywordRepository.loadDistrict();
                    } else if (key == DomainType.SUB_DISTRICT) {
                        return keywordRepository.loadSubDistrict();
                    } else if (key == DomainType.LINE) {
                        return keywordRepository.loadLine();
                    } else if (key == DomainType.STATION) {
                        return keywordRepository.loadStation();
                    } else {
                        return new ArrayList<>();
                    }
                }
            });

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<KeywordBean> findContain(String keyword, List<DomainType> scopes, Integer limit) {
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
            try {
                List<KeywordBean> keywords = domainKeywordsCache.get(domainType);
                for (KeywordBean keywordBean : keywords) {
                    if (count >= limit) {
                        break;
                    }
                    if (keywordBean.getKeyword().contains(keyword)) {
                        results.add(keywordBean);
                        count++;
                    }
                }
            } catch (ExecutionException e) {
                ExceptionUtil.catching(e);
            }
        }
        return results;
    }

    public List<KeywordBean> findNameMatch(String keyword, List<DomainType> scopes, Integer limit) {
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
            try {
                List<KeywordBean> keywords = domainKeywordsCache.get(domainType);
                for (KeywordBean keywordBean : keywords) {
                    if (count >= limit) {
                        break;
                    }
                    if (keywordBean.getName().equals(keyword)) {
                        results.add(keywordBean);
                        count++;
                    }
                }
            } catch (ExecutionException e) {
                ExceptionUtil.catching(e);
            }
        }
        return results;
    }
}
