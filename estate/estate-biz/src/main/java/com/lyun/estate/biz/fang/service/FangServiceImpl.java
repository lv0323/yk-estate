package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.FangSelector;
import com.lyun.estate.biz.fang.entity.FangTag;
import com.lyun.estate.biz.fang.repo.FangRepository;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.supports.types.YN;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-01-20.
 */
@Service
public class FangServiceImpl implements FangService {


    @Autowired
    private FangRepository fangRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Autowired
    private KeywordService keywordService;

    @Override
    public PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, FangSummaryOrder order,
                                                          PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("过滤条件", filter);
        ExceptionUtil.checkNotNull("城市", filter.getCityId());
        ExceptionUtil.checkNotNull("业务", filter.getBizType());
        ExceptionUtil.checkNotNull("排序条件", order);
        ExceptionUtil.checkNotNull("pageBounds", pageBounds);

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(order.getOrders());


        PageList<FangSummary> empty = new PageList<>(Lists.newArrayList(),
                new Paginator(pageBounds.getPage(), pageBounds.getLimit(), 0));

        FangSelector selector = new FangSelector();
        BeanUtils.copyProperties(filter, selector);
        selector.setProcess(HouseProcess.PUBLISH);

        //shiCountsFilter
        if (filter.getShiCountsFilters() != null && !filter.getShiCountsFilters().isEmpty()) {
            List<Integer> sCounts = new ArrayList<>();
            filter.getShiCountsFilters().forEach(shiCountsFilter -> sCounts.addAll(shiCountsFilter.getCounts()));
            selector.setsCounts(sCounts);
        }

        //ElevatorFilter
        if (filter.getElevatorFilters() != null) {
            if (filter.getElevatorFilters().stream().anyMatch(ElevatorFilter.HAS::equals)
                    && filter.getElevatorFilters().stream().anyMatch(ElevatorFilter.HAS_NOT::equals)) {
                //doing nothing
            } else {
                if (filter.getElevatorFilters().stream().anyMatch(ElevatorFilter.HAS::equals)) {
                    selector.setHasElevator(YN.Y);
                } else if (filter.getElevatorFilters().stream().anyMatch(ElevatorFilter.HAS_NOT::equals)) {
                    selector.setHasElevator(YN.N);
                }
            }
        }
        //houseTags
        if (filter.getHouseTags() != null && !filter.getHouseTags().isEmpty()) {
            filter.getHouseTags().forEach(houseTag -> {
                switch (houseTag) {
                    case ONLY:
                        selector.setIsOnly(YN.Y);
                        break;
                    case OVER_2:
                        selector.setOverYears(Math.max(2, Optional.ofNullable(selector.getOverYears()).orElse(0)));
                        break;
                    case OVER_5:
                        selector.setOverYears(Math.max(5, Optional.ofNullable(selector.getOverYears()).orElse(0)));
                        break;
                    case DECORATE_JING:
                        selector.setDecorates(Lists.newArrayList(Decorate.JING, Decorate.HAO));
                        break;
                    case HAS_KEY:
                        if (selector.getShowings() == null) {
                            selector.setShowings(Lists.newArrayList(Showing.HAS_KEY));
                        } else {
                            selector.getShowings().add(Showing.HAS_KEY);
                        }
                        break;
                    case ANY_TIME:
                        if (selector.getShowings() == null) {
                            selector.setShowings(Lists.newArrayList(Showing.ANY_TIME));
                        } else {
                            selector.getShowings().add(Showing.ANY_TIME);
                        }
                        break;
                    case NEAR_LINE:
                        selector.setNearLine(YN.Y);
                        break;
                }
            });
        }


        // keywords
        if (!Strings.isNullOrEmpty(filter.getKeyword())) {
            Optional<KeywordBean> condition = keywordService.findNameMatch(filter.getKeyword(), filter.getCityId(),
                    Lists.newArrayList(DomainType.DISTRICT,
                            DomainType.SUB_DISTRICT,
                            DomainType.LINE,
                            DomainType.STATION), 1).stream().findAny();
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
                    case LINE:
                        if (selector.getLineId() != null && !Objects.equals(selector.getLineId(),
                                condition.get().getId())) {
                            return empty;
                        } else {
                            selector.setLineId(condition.get().getId());
                        }
                        break;
                    case STATION:
                        if (selector.getStationId() != null && !Objects.equals(selector.getStationId(),
                                condition.get().getId())) {
                            return empty;
                        } else {
                            selector.setStationId(condition.get().getId());
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

        return findFangSummaryBySelector(selector, pageBounds);
    }

    private PageList<FangSummary> findFangSummaryBySelector(FangSelector selector, PageBounds pageBounds) {
        PageList<FangSummary> summaries = fangRepository.findSummaryBySelector(selector, pageBounds);

        summaries.forEach(summary -> {
                    List<FangTag> fangTags = fangRepository.findTags(summary.getId());
                    summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));

                    decorateTags(summary);

                    summary.setImageURI(Optional.ofNullable(
                            fileService.findFirst(summary.getId(),
                                    DomainType.FANG,
                                    CustomType.SHIJING,
                                    FileProcess.WATERMARK)).
                            map(FileDescription::getFileURI).orElse(null));
                }
        );
        return summaries;
    }

    private void decorateTags(FangSummary summary) {
        Set<HouseTag> tags = new HashSet<>(Optional.ofNullable(summary.getTags()).orElse(new ArrayList<>()));

        if (summary.getIsOnly() == YN.Y) {
            tags.add(HouseTag.ONLY);
        }
        if (Optional.ofNullable(summary.getOverYears()).orElse(0) >= 5) {
            tags.add(HouseTag.OVER_5);
        } else if (Optional.ofNullable(summary.getOverYears()).orElse(0) >= 2) {
            tags.add(HouseTag.OVER_2);
        }
        if (summary.getDecorate() == Decorate.JING) {
            tags.add(HouseTag.DECORATE_JING);
        }
        if (summary.getShowing() == Showing.ANY_TIME) {
            tags.add(HouseTag.ANY_TIME);
        } else if (summary.getShowing() == Showing.HAS_KEY) {
            tags.add(HouseTag.HAS_KEY);
        }
        if (summary.getNearLine() == YN.Y) {
            tags.add(HouseTag.NEAR_LINE);
        }
        summary.setTags(Lists.newArrayList(tags));
    }

    @Override
    public FangDetail getDetail(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangDetail fangDetail = fangRepository.findDetail(id);
        if (fangDetail != null) {
            fangDetail.setDescr(fangRepository.findDescr(id));
            XiaoQuDetail detail = xiaoQuService.getDetail(fangDetail.getXiaoQuId());
            fangDetail.setSubDistrict(detail.getSubDistrict());
            fangDetail.setDistrict(detail.getDistrict());
        }
        return fangDetail;
    }

    @Override
    public FangSummary getSummary(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangSummary summary = fangRepository.findSummary(id);
        List<FangTag> fangTags = fangRepository.findTags(id);
        summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));
        decorateTags(summary);
        summary.setImageURI(Optional.ofNullable(fileService.findFirst(id,
                DomainType.FANG,
                CustomType.SHIJING,
                FileProcess.WATERMARK))
                .map(FileDescription::getFileURI).orElse(null));
        return summary;
    }

    @Override
    public List<FangSummary> findSummaryByXiaoQuId(Long cityId, BizType bizType, Long xiaoQuId) {
        ExceptionUtil.checkNotNull("城市", cityId);
        ExceptionUtil.checkNotNull("业务", bizType);
        ExceptionUtil.checkNotNull("小区", xiaoQuId);
        FangSelector selector = new FangSelector();
        selector.setCityId(cityId);
        selector.setBizType(bizType);
        selector.setXiaoQuIds(Lists.newArrayList(xiaoQuId));
        selector.setProcess(HouseProcess.PUBLISH);

        return findFangSummaryBySelector(selector, null);
    }

}
