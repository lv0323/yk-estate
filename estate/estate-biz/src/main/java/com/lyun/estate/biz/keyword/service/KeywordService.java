package com.lyun.estate.biz.keyword.service;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Strings;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.repository.KeywordRepository;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.Community;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private static final String PINYIN_SPLIT = ";";
    private static final Logger logger = LoggerFactory.getLogger(KeywordService.class);

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Autowired
    private FangService fangService;


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
            List<KeywordBean> keywords = getKeywordBean(cityId, domainType);
            for (KeywordBean keywordBean : keywords) {
                if (count >= limit) {
                    break;
                }
                if (keywordGroupMatch(keywordBean.getKeyword(), keyword, PINYIN_SEPARATOR, PINYIN_SPLIT)) {
                    results.add(keywordBean);
                    count++;
                }
            }
        }
        return results;
    }

    private List<KeywordBean> getKeywordBean(Long cityId, DomainType domainType) {
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
            List<KeywordBean> keywords = getKeywordBean(cityId, domainType);
            for (KeywordBean keywordBean : keywords) {
                if (count >= limit) {
                    break;
                }
                if (keywordGroupMatch(keywordBean.getKeyword(), keyword, PINYIN_SEPARATOR, PINYIN_SPLIT)) {
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
     * 关键字匹配
     * @param src 原始字段(都好分隔的拼音)
     * @param target 关键字，有可能是汉字，有可能是拼音
     * @param separator
     * @param split
     * @return
     */
    private boolean keywordGroupMatch(String src, String target, String separator, String split) {
        String convertedSrc = null;
        try {
            convertedSrc = PinyinHelper.convertToPinyinString(src.replace(" ", ""), "", PinyinFormat.WITHOUT_TONE);
            if (StringUtils.isEmpty(convertedSrc)) {
                logger.warn("查询关键词转换后为空,忽略");
                return false;
            }
        } catch (PinyinException e) {
            logger.warn("查询关键词[{}]转换发生错误[{}],忽略", src, e.getMessage());
            return false;
        }
        if (convertedSrc.toLowerCase().contains(target.toLowerCase())) {
            return true;
        }
        if (convertedSrc.contains(split)) {
            String[] groups = convertedSrc.split(split);
            for (String s : groups) {
                if (keywordMatch(s.toLowerCase(), null, separator).contains(target.toLowerCase())) {
                    return true;
                }
            }
            return false;
        } else {
           return keywordMatch(convertedSrc.toLowerCase(), null, separator).contains(target.toLowerCase());
        }
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
            System.err.println(">>>>>>>>>>>>>>>>sub src:>>>>" + src);
            return dis.append(src.charAt(0)).toString();
        } else {
            dis.append(src.charAt(0));
            System.err.println(">>>>>>>>>>>>>>>>src:>>>>>>>>>>>>>>>>>>>>>>>>" + src);
            return keywordMatch(src.substring(src.indexOf(separator) + 1), dis, separator);
        }
    }

    /**
     * 转换格式：汉字--> han,zi
     * @param src
     * @return
     * @throws PinyinException
     */
    public String convertToPinyinString(String src) throws PinyinException {
        return PinyinHelper.convertToPinyinString(src.replace(" ", ""), PINYIN_SEPARATOR, PinyinFormat.WITHOUT_TONE);
    }

    @Transactional
    public boolean updateAllKeyword() {
        List<City> cities = cityService.findCities();
        cities.forEach((City c) -> {
            if (!StringUtils.isEmpty(c.getName())) {
                try {
                    String cityKeyword = convertToPinyinString(c.getName());
                    cityService.updateCityKeyword(c.getId(), cityKeyword);
                } catch (PinyinException e) {
                    logger.warn("汉字[{}]转换成拼音发生异常[{}]", c.getName(), e.getMessage());
                }
            }
            List<District> districts = cityService.findOrderedDistricts(c.getId());
            districts.forEach(d -> {
                if (!StringUtils.isEmpty(d.getName())) {
                    try {
                        String disKeyword = convertToPinyinString(d.getName());
                        cityService.updateDistrictKeyword(d.getId(), disKeyword);
                    } catch (PinyinException e) {
                        logger.warn("汉字[{}]转换成拼音发生异常[{}]", d.getName(), e.getMessage());
                    }
                }
                List<SubDistrict> subDistricts = cityService.findOrderedSubDistricts(d.getId());
                subDistricts.forEach(s -> {
                    if (!StringUtils.isEmpty(s.getName())) {
                        try {
                            String subDisKeyword = convertToPinyinString(s.getName());
                            cityService.updateSubDistrictKeyword(s.getId(), subDisKeyword);
                        } catch (PinyinException e) {
                            logger.warn("汉字[{}]转换成拼音发生异常[{}]", s.getName(), e.getMessage());
                        }
                    }
                });
            });
        });
        List<Community> allCommunity = xiaoQuService.findAllCommunity();
        allCommunity.forEach(c -> {
            if (!StringUtils.isEmpty(c.getName())) {
                try {
                    String communityKeyword = convertToPinyinString(c.getName());
                    if (!StringUtils.isEmpty(c.getAlias().replace(",", "").replace(" ", ""))) {
                        communityKeyword = communityKeyword + PINYIN_SPLIT + convertToPinyinString(c.getAlias().replace(",", "").replace(" ", ""));
                    }
                    xiaoQuService.updateKeyword(c.getId(), communityKeyword);
                } catch (PinyinException e) {
                    logger.warn("汉字[{}]转换成拼音发生异常[{}]", c.getName(), e.getMessage());
                }
            }
        });
        return true;
    }
}
