package com.lyun.estate.biz.xiaoqu.service;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.BizType;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.housedict.entity.City;
import com.lyun.estate.biz.housedict.entity.District;
import com.lyun.estate.biz.housedict.entity.Line;
import com.lyun.estate.biz.housedict.entity.SubDistrict;
import com.lyun.estate.biz.housedict.service.CityService;
import com.lyun.estate.biz.housedict.service.HouseService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CityService cityService;


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
                switch (condition.get().getDomainType()) {
                    case DISTRICT:
                        if (selector.getDistrictId() != null && !Objects.equals(selector.getDistrictId(),
                                condition.get().getId())) {
                            return empty;
                        } else {
                            selector.setDistrictId(condition.get().getId());
                        }
                        break;
                    case SUB_DISTRICT:
                        if (selector.getSubDistrictId() != null && !Objects.equals(selector.getSubDistrictId(),
                                condition.get().getId())) {
                            return empty;
                        } else {
                            selector.setSubDistrictId(condition.get().getId());
                        }
                        break;
                }
            } else {
                List<KeywordBean> xiaoQus = keywordService.findContain(filter.getKeyword(), filter.getCityId(),
                        Lists.newArrayList(DomainType.XIAO_QU), 100);
                if (xiaoQus.isEmpty()) {
                    return empty;
                } else {
                    selector.setXiaoQuIds(xiaoQus.stream().map(KeywordBean::getId).collect(Collectors.toList()));
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
            summary.setDistrict(Optional.ofNullable(cityService.findDistrict(bean.getDistrictId()))
                    .map(District::getName)
                    .orElse(null));
            summary.setSubDistrict(Optional.ofNullable(cityService.findSubDistrict(bean.getSubDistrictId())).map(
                    SubDistrict::getName).orElse(null));
            summary.setStructure(StructureType.getTypeStr(bean.getStructureType()));
            FileDescription firstImg = fileService.findFirst(bean.getId(), DomainType.XIAO_QU, CustomType.SHI_JING,
                    FileProcess.WATERMARK);
            summary.setImageURI(Optional.ofNullable(firstImg).map(FileDescription::getFileURI).orElse(null));
            summaries.add(summary);
        });

        return summaries;
    }

    @Override
    public XiaoQuDetail getDetail(Long id) {
        ExceptionUtil.checkNotNull("小区编号", id);
        XiaoQuDetailBean bean = xiaoQuRepository.findDetail(id);
        XiaoQuDetail detail = new XiaoQuDetail();
        BeanUtils.copyProperties(bean, detail);
        detail.setCity(Optional.ofNullable(cityService.findCity(bean.getCityId())).map(City::getName).orElse(null));
        detail.setDistrict(Optional.ofNullable(cityService.findDistrict(bean.getDistrictId()))
                .map(District::getName)
                .orElse(null));
        detail.setSubDistrict(Optional.ofNullable(cityService.findSubDistrict(bean.getSubDistrictId()))
                .map(SubDistrict::getName)
                .orElse(null));
        detail.setStructure(StructureType.getTypeStr(bean.getStructureType()));
        FileDescription firstImg = fileService.findFirst(bean.getId(), DomainType.XIAO_QU, CustomType.SHI_JING,
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
            selector.setCityId(xiaoQuDetailBean.getCityId());
            selector.setExcludeIds(Lists.newArrayList(id));

            return findXiaoQuSummaryBySelector(selector, pageBounds);
        }
        return new PageList<>(Lists.newArrayList(),
                new Paginator(pageBounds.getPage(), pageBounds.getLimit(), 0));
    }

    @Override
    public List<XiaoQuStationRel> findStations(Long xiaoQuId) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        List<XiaoQuStationRel> stationRels = xiaoQuRepository.findStations(xiaoQuId);
        stationRels.forEach(r -> {
            r.setLineName(Optional.ofNullable(cityService.findPrimaryLine(r.getStationId()))
                    .map(Line::getName)
                    .orElse(""));
        });
        return stationRels;
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
                                                          BizType bizType, Integer cityId) {
        //TODO 缓存处理
        if (bizType == BizType.SELL) {
            return xiaoQuRepository.findSellCommunityListByMap(minLongitude,
                    maxLongitude,
                    minLatitude,
                    maxLatitude,
                    cityId);
        } else if (bizType == BizType.RENT) {
            return xiaoQuRepository.findRentCommunityListByMap(minLongitude,
                    maxLongitude,
                    minLatitude,
                    maxLatitude,
                    cityId);
        }
        return null;
    }


}
