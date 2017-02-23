package com.lyun.estate.biz.keyword.service;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.base.Strings;
import com.lyun.estate.biz.housedict.entity.*;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.entity.KeywordResp;
import com.lyun.estate.biz.keyword.repository.KeywordRepository;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.xiaoqu.entity.Community;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Service
public class KeywordService {
    private static final char CHINESE_LING = '〇';
    private static final String PINYIN_SPLIT = ";";
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
                if (keywordMatch(keywordBean, keyword)) {
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
                if (keywordMatch(keywordBean, keyword)) {
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

    public boolean updateAllKeyword() {
        List<City> cities = cityService.findCities();
        cities.forEach((City c) -> {
            String cityName = stringFilter(c.getName());
            if (!StringUtils.isEmpty(cityName)) {
                try {
                    StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(cityName,
                            "",
                            PinyinFormat.WITHOUT_TONE))
                            .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(cityName));
                    cityService.updateCityKeyword(c.getId(), keyword.toString());
                } catch (PinyinException e) {
                    logger.warn("汉字[{}]转换成拼音发生异常[{}]", c.getName(), e.getMessage());
                }
            }
            List<District> districts = cityService.findOrderedDistricts(c.getId());
            districts.forEach(d -> {
                String disName = stringFilter(d.getName());
                if (!StringUtils.isEmpty(disName)) {
                    try {
                        StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(disName,
                                "",
                                PinyinFormat.WITHOUT_TONE))
                                .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(disName));
                        cityService.updateDistrictKeyword(d.getId(), keyword.toString());
                    } catch (PinyinException e) {
                        logger.warn("汉字[{}]转换成拼音发生异常[{}]", d.getName(), e.getMessage());
                    }
                }
                List<SubDistrict> subDistricts = cityService.findOrderedSubDistricts(d.getId());
                subDistricts.forEach(s -> {
                    String subDisName = stringFilter(s.getName());
                    if (!StringUtils.isEmpty(subDisName)) {
                        try {
                            StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(
                                    subDisName,
                                    "",
                                    PinyinFormat.WITHOUT_TONE))
                                    .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(subDisName));
                            cityService.updateSubDistrictKeyword(s.getId(), keyword.toString());
                        } catch (PinyinException e) {
                            logger.warn("汉字[{}]转换成拼音发生异常[{}]", s.getName(), e.getMessage());
                        }
                    }
                });
            });

            List<Line> lines = cityService.findOrderedLines(c.getId());
            lines.forEach(l -> {
                String lineName = stringFilter(l.getName());
                if (!StringUtils.isEmpty(lineName)) {
                    try {
                        StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(lineName,
                                "",
                                PinyinFormat.WITHOUT_TONE))
                                .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(lineName));
                        cityService.updateLineKeyword(l.getId(), keyword.toString());
                    } catch (PinyinException e) {
                        logger.warn("汉字[{}]转换成拼音发生异常[{}]", l.getName(), e.getMessage());
                    }
                }
                List<Station> stations = cityService.findOrderedStations(l.getId());
                stations.forEach(s -> {
                    String stationName = stringFilter(s.getName());
                    if (!StringUtils.isEmpty(stationName)) {
                        try {
                            StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(
                                    stationName,
                                    "",
                                    PinyinFormat.WITHOUT_TONE))
                                    .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(stationName));
                            cityService.updateStationKeyword(s.getId(), keyword.toString());
                        } catch (PinyinException e) {
                            logger.warn("汉字[{}]转换成拼音发生异常[{}]", s.getName(), e.getMessage());
                        }
                    }
                });
            });
        });
        List<Community> allCommunity = xiaoQuService.findAllCommunity();
        allCommunity.forEach(c -> {
            String communityName = stringFilter(c.getName());
            String communityAlias = stringFilter(c.getAlias());
            if (!StringUtils.isEmpty(communityName)) {
                try {
                    StringBuilder keyword = new StringBuilder().append(PinyinHelper.convertToPinyinString(communityName,
                            "",
                            PinyinFormat.WITHOUT_TONE))
                            .append(PINYIN_SPLIT).append(PinyinHelper.getShortPinyin(communityName));
                    if (!StringUtils.isEmpty(communityAlias)) {
                        keyword.append(PINYIN_SPLIT)
                                .append(PinyinHelper.convertToPinyinString(communityAlias,
                                        "",
                                        PinyinFormat.WITHOUT_TONE))
                                .append(PINYIN_SPLIT)
                                .append(PinyinHelper.getShortPinyin(communityAlias));
                    }
                    xiaoQuService.updateKeyword(c.getId(), keyword.toString());
                } catch (PinyinException e) {
                    logger.warn("汉字[{}]转换成拼音发生异常[{}]", c.getName(), e.getMessage());
                }
            }
        });
        return true;
    }

    /**
     * 关键查询匹配
     *
     * @param keywordBean
     * @param target
     * @return
     */
    private boolean keywordMatch(KeywordBean keywordBean, String target) {
        if (keywordBean == null || StringUtils.isEmpty(keywordBean.getKeyword()) || StringUtils.isEmpty(target)) {
            logger.warn("存储关键词[{}]不合法或者查询关键词[{}]为空,忽略", keywordBean, target);
            return false;
        }
        target = stringFilter(target);
        if (StringUtils.isEmpty(target)) {
            logger.warn("查询关键词[{}]为空,忽略", target);
            return false;
        }

        /* 名称包含 */
        if (contains(keywordBean.getName(), target)) {
            return true;
        }
        /* 别名包含 */
        if (contains(keywordBean.getAlias(), target)) {
            return true;
        }
        /* 关键词包含 */
        String pinyin = null;
        try {
            pinyin = PinyinHelper.convertToPinyinString(target, "", PinyinFormat.WITHOUT_TONE);
            if (StringUtils.isEmpty(pinyin)) {
                logger.warn("查询关键词[{}]转换后为空,忽略", target);
                return false;
            }
            if (contains(keywordBean.getKeyword(), pinyin)) {
                return true;
            }
        } catch (PinyinException e) {
            logger.warn("查询关键词[{}]转换发生错误[{}],忽略", target, e.getMessage());
            return false;
        }
        /* 不包含汉字进行首字母匹配 */
        List<String> chineseList = containsChinese(target);
        if (CollectionUtils.isEmpty(chineseList)) {
            /* 首字母匹配 */
            try {
                String shortPinyin = PinyinHelper.getShortPinyin(target);
                if (contains(keywordBean.getKeyword(), shortPinyin)) {
                    return true;
                }
            } catch (PinyinException e) {
                return false;
            }
        }

        return false;
    }

    /**
     * 是否包含指定字符串，不区分大小写
     *
     * @param src
     * @param target
     * @return
     */
    private boolean contains(String src, String target) {
        if (StringUtils.isEmpty(src) || StringUtils.isEmpty(target)) {
            return false;
        }
        Pattern p = Pattern.compile(target, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(src);
        return m.find();
    }

    /**
     * 过滤特殊字符
     *
     * @param str
     * @return
     */
    private String stringFilter(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    private List<String> containsChinese(String src) {
        List<String> chineseList = new ArrayList<String>();
        for (String s : src.split("")) {
            if (ChineseHelper.isChinese(s.charAt(0)) || s.charAt(0) == CHINESE_LING) {
                chineseList.add(s);
            }
        }
        return chineseList;
    }
}
