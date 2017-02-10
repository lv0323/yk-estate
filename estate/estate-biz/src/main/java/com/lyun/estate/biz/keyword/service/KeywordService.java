package com.lyun.estate.biz.keyword.service;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Strings;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.repository.KeywordRepository;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Service
public class KeywordService {
    private static final String PINYIN_SEPARATOR = ",";
    private static final Logger logger = LoggerFactory.getLogger(KeywordService.class);

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private XiaoQuService xiaoQuService;

    public KeywordService(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }

    public List<KeywordBean> findContain(String keyword, Long cityId, ArrayList<DomainType> scopes, Integer limit) {
        List<KeywordBean> results = new ArrayList<>();
        limit = Optional.ofNullable(limit).orElse(Integer.MAX_VALUE);
        if (scopes == null || Strings.isNullOrEmpty(keyword) || limit <= 0) {
            return results;
        }
        String convertedKeyword = null;
        try {
            convertedKeyword = PinyinHelper.convertToPinyinString(keyword, "", PinyinFormat.WITHOUT_TONE);
            if (StringUtils.isEmpty(convertedKeyword)) {
                logger.warn("查询关键词转换后为空,忽略");
                return results;
            }
        } catch (PinyinException e) {
            logger.warn("查询关键词[{}]转换发生错误[{}],忽略", keyword, e.getMessage());
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
                String kw = keywordBean.getKeyword().replace(",", "");
                assert kw != null;
                if (kw.toLowerCase().contains(keyword.toLowerCase()) || keywordMatch(keywordBean.getKeyword(), new StringBuilder(), PINYIN_SEPARATOR).toLowerCase().contains(convertedKeyword.toLowerCase())) {
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
        String convertedKeyword = null;
        try {
            convertedKeyword = PinyinHelper.convertToPinyinString(keyword, "", PinyinFormat.WITHOUT_TONE);
            if (StringUtils.isEmpty(convertedKeyword)) {
                logger.warn("查询关键词转换后为空,忽略");
                return results;
            }
        } catch (PinyinException e) {
            logger.warn("查询关键词[{}]转换发生错误[{}],忽略", keyword, e.getMessage());
            return results;
        }
        for (DomainType domainType : scopes) {
            if (count >= limit) {
                break;
            }
            List<KeywordBean> keywords = getCityDomainBean(cityId, domainType);
            for (KeywordBean keywordBean : keywords) {
                if (count >= limit) {
                    break;
                }
                String kw = keywordBean.getKeyword().replace(",", "");
                assert kw != null;
                if (kw.toLowerCase().contains(keyword.toLowerCase()) || keywordMatch(keywordBean.getKeyword(), new StringBuilder(), PINYIN_SEPARATOR).toLowerCase().contains(convertedKeyword.toLowerCase())) {
                    results.add(keywordBean);
                    count++;
                }
            }
        }
        return results;
    }

    public List<KeywordResp> decorate(List<KeywordBean> keywordBeans) {
        List<KeywordResp> result = new ArrayList<>();

        keywordBeans.forEach(keywordBean -> {
            KeywordResp resp = new KeywordResp();
            resp.setType(keywordBean.getDomainType());
            resp.setResp(keywordBean.getName());

            StringBuilder noteBuilder = new StringBuilder();
            if (!Strings.isNullOrEmpty(keywordBean.getAlias())) {
                noteBuilder.append("(").append(keywordBean.getAlias()).append(") ");
            }

            if (keywordBean.getDomainType() == DomainType.DISTRICT) {
                noteBuilder.append("区域");
            } else if (keywordBean.getDomainType() == DomainType.SUB_DISTRICT) {
                noteBuilder.append(cityService.findPrimaryDistrict(keywordBean.getId()).getName());
            } else if (keywordBean.getDomainType() == DomainType.LINE) {
                noteBuilder.append("地铁线");
            } else if (keywordBean.getDomainType() == DomainType.STATION) {
                noteBuilder.append("地铁站");
            } else if (keywordBean.getDomainType() == DomainType.XIAO_QU) {
                XiaoQuDetail detail = xiaoQuService.getDetail(keywordBean.getId());
                noteBuilder.append(detail.getDistrict()).append(" ")
                        .append(detail.getDistrict());
                resp.setNote(noteBuilder.toString());
            }
            resp.setNote(noteBuilder.toString().trim());
            result.add(resp);
        });

        return result;
    }

    /**
     * 将keyword转成首字母形式
     * @param src 源
     * @param dis 中间结果
     * @param separator 分隔符
     * @return 结果
     */
    private String keywordMatch(String src, StringBuilder dis, String separator) {
        if (dis == null) {
            dis = new StringBuilder();
        }
        if (!src.contains(separator)) {
            return dis.append(src.charAt(0)).toString();
        } else {
            dis.append(src.charAt(0));
            return keywordMatch(src.substring(src.indexOf(separator) + 1), dis, separator);
        }
    }
}
