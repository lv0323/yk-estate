package com.lyun.estate.biz.xiaoqu.service;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.service.HouseService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.def.BizType;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.biz.spec.xiaoqu.def.XQSummaryOrder;
import com.lyun.estate.biz.spec.xiaoqu.entity.*;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuDetailBean;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSelector;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuSummaryBean;
import com.lyun.estate.biz.xiaoqu.repository.XiaoQuRepository;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-01-06.
 */
@Service
public class XiaoQuServiceImpl implements XiaoQuService {

    @Autowired
    private XiaoQuRepository xiaoQuRepository;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private FileService fileService;

    @Autowired
    private HouseService houseService;


    /**
     * @param filter
     * @return 关键词不为空依次与区域，板块，小区名（含别名）相匹配，返回响应结果；
     */
    @Override
    public PageList<XiaoQuSummary> findXiaoQuSummaryByKeyword(XiaoQuFilter filter, XQSummaryOrder order,
                                                              PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("过滤条件", filter);
        ExceptionUtil.checkNotNull("城市", filter.getCityId());
        ExceptionUtil.checkNotNull("排序条件", order);
        ExceptionUtil.checkNotNull("pageBounds", pageBounds);

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(order.getOrders());

        PageList<XiaoQuSummary> empty = new PageList<>(Lists.newArrayList(),
                new Paginator(pageBounds.getPage(), pageBounds.getLimit(), 0));

        XiaoQuSelector selector = new XiaoQuSelector();
        BeanUtils.copyProperties(filter, selector);

        //structureType
        if (filter.getStructureType() != null) {
            selector.setStructureTypes(StructureType.possibleIntValues(filter.getStructureType()));
        }

        // keywords
        if (!Strings.isNullOrEmpty(filter.getKeyword())) {
            Optional<KeywordBean> condition = keywordService.findNameMatch(filter.getKeyword(), filter.getCityId(),
                    Lists.newArrayList(DomainType.DISTRICT, DomainType.SUB_DISTRICT), 1).stream().findAny();
            if (condition.isPresent()) {
                if (selector.getDistrictId() == null && condition.get().getDomainType() == DomainType.DISTRICT) {
                    selector.setDistrictId(condition.get().getId());
                } else if (selector.getSubDistrictId() == null && condition.get()
                        .getDomainType() == DomainType.SUB_DISTRICT) {
                    selector.setSubDistrictId(condition.get().getId());
                }
            } else {
                List<KeywordBean> xiaoQus = keywordService.findContain(filter.getKeyword(), filter.getCityId(),
                        Lists.newArrayList(DomainType.XIAO_QU), 100);
                if (xiaoQus.isEmpty()) {
                    return empty;
                } else {
                    List<Long> xqIds = new ArrayList<>();
                    xiaoQus.forEach(keywordBean -> xqIds.add(keywordBean.getId()));
                    selector.setXiaoQuIds(xqIds);
                }
            }
        }

        return findXiaoQuSummaryBySelector(selector, pageBounds);
    }

    private PageList<XiaoQuSummary> findXiaoQuSummaryBySelector(XiaoQuSelector selector, PageBounds pageBounds) {
        PageList<XiaoQuSummaryBean> result = xiaoQuRepository.findSummary(selector, pageBounds);

        PageList<XiaoQuSummary> summaries = new PageList<>(new ArrayList<>(), result.getPaginator());

        result.forEach(bean -> {
            XiaoQuSummary summary = new XiaoQuSummary();
            BeanUtils.copyProperties(bean, summary);
            summary.setDistrict(Optional.ofNullable(houseService.findDistrict(bean.getDistrictId()))
                    .map(District::getName)
                    .orElse(null));
            summary.setSubDistrict(Optional.ofNullable(houseService.findSubDistrict(bean.getSubDistrictId())).map(
                    SubDistrict::getName).orElse(null));
            summary.setStructure(StructureType.getTypeStr(bean.getStructureType()));
            FileDescription firstImg = fileService.findFirst(bean.getId(), DomainType.XIAO_QU, CustomType.SHIJING,
                    FileProcess.WATERMARK);
            summary.setImageURI(Optional.ofNullable(firstImg).map(FileDescription::getFileURI).orElse(null));
            summaries.add(summary);
        });

        return summaries;
    }

    @Override
    public List<KeywordResp> keywords(Long cityId, String keyword) {
        List<KeywordResp> result = new ArrayList<>();
        if (Strings.isNullOrEmpty(keyword)) {
            return result;
        }
        List<KeywordBean> keywordBeans = keywordService.findContain(keyword, cityId,
                Lists.newArrayList(DomainType.DISTRICT, DomainType.SUB_DISTRICT, DomainType.XIAO_QU), 10);

        keywordBeans.forEach(keywordBean -> {
            KeywordResp resp = new KeywordResp();
            resp.setType(keywordBean.getDomainType());
            resp.setResp(keywordBean.getName());

            StringBuilder noteBuilder = new StringBuilder();
            if (!Strings.isNullOrEmpty(keywordBean.getAlias())) {
                noteBuilder.append("(").append(keywordBean.getAlias()).append(") ");
            }

            if (keywordBean.getDomainType() == DomainType.SUB_DISTRICT) {
                noteBuilder.append(houseService.findPrimaryDistrict(keywordBean.getId()).getName());
            } else if (keywordBean.getDomainType() == DomainType.XIAO_QU) {
                XiaoQuDetailBean detail = xiaoQuRepository.findDetail(keywordBean.getId());
                noteBuilder.append(houseService.findDistrict(detail.getDistrictId()).getName()).append(" ")
                        .append(houseService.findSubDistrict(detail.getSubDistrictId()).getName());
                resp.setNote(noteBuilder.toString());
            }
            resp.setNote(noteBuilder.toString().trim());
            result.add(resp);
        });
        return result;
    }

    @Override
    public XiaoQuDetail getDetail(Long id) {
        ExceptionUtil.checkNotNull("小区编号", id);
        XiaoQuDetailBean bean = xiaoQuRepository.findDetail(id);
        XiaoQuDetail detail = new XiaoQuDetail();
        BeanUtils.copyProperties(bean, detail);
        detail.setCity(Optional.ofNullable(houseService.findCity(bean.getCityId())).map(City::getName).orElse(null));
        detail.setDistrict(Optional.ofNullable(houseService.findDistrict(bean.getDistrictId()))
                .map(District::getName)
                .orElse(null));
        detail.setSubDistrict(Optional.ofNullable(houseService.findSubDistrict(bean.getSubDistrictId()))
                .map(SubDistrict::getName)
                .orElse(null));
        detail.setStructure(StructureType.getTypeStr(bean.getStructureType()));
        FileDescription firstImg = fileService.findFirst(bean.getId(), DomainType.XIAO_QU, CustomType.SHIJING,
                FileProcess.WATERMARK);
        detail.setImageURI(Optional.ofNullable(firstImg).map(FileDescription::getFileURI).orElse(null));
        //todo: fix this
        detail.setFollows(RandomUtils.nextInt(9999));

        return detail;
    }

    @Override
    public PageList<XiaoQuSummary> findNearbyXiaoQu(Long id) {
        PageBounds pageBounds = new PageBounds(1, 3);
        pageBounds.setContainsTotalCount(false);

        XiaoQuDetailBean xiaoQuDetailBean = xiaoQuRepository.findDetail(id);
        if (xiaoQuDetailBean != null) {
            pageBounds.getOrders().clear();
            pageBounds.getOrders().addAll(XQSummaryOrder.DEFAULT.getOrders());
            XiaoQuSelector selector = new XiaoQuSelector();
            selector.setSubDistrictId(xiaoQuDetailBean.getSubDistrictId());
            selector.setExcludeIds(Lists.newArrayList(id));

            return findXiaoQuSummaryBySelector(selector, pageBounds);
        }
        return new PageList<>(Lists.newArrayList(),
                new Paginator(pageBounds.getPage(), pageBounds.getLimit(), 0));
    }

    @Override
    public List<EstateMapResource> findAllDistrictListByMap(int cityId, BizType bizType) {
        if (bizType == BizType.SELL) {
            return houseService.findAllSellDistrictListByMap(cityId);
        } else if (bizType == BizType.RENT) {
            return houseService.findAllRentDistrictListByMap(cityId);
        }
        return null;
    }

    @Override
    public List<EstateMapResource> findAllSubDistrictListByMap(int cityId, BizType bizType) {
        if (bizType == BizType.SELL) {
            return houseService.findAllSellSubDistrictListByMap(cityId);
        } else if (bizType == BizType.RENT) {
            return houseService.findAllRentSubDistrictListByMap(cityId);
        }
        return null;
    }

    @Override
    public List<EstateMapResource> findCommunityListByMap(BigDecimal minLongitude, BigDecimal maxLongitude,
                                                          BigDecimal minLatitude, BigDecimal maxLatitude,
                                                          BizType bizType) {
        //TODO 缓存处理
        if (bizType == BizType.SELL) {
            return xiaoQuRepository.findSellCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude);
        } else if (bizType == BizType.RENT) {
            return xiaoQuRepository.findRentCommunityListByMap(minLongitude, maxLongitude, minLatitude, maxLatitude);
        }
        return null;
    }


}
