package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.contract.def.ContractDefine;
import com.lyun.estate.biz.contract.entity.Contract;
import com.lyun.estate.biz.contract.service.ContractService;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.def.HouseType;
import com.lyun.estate.biz.fang.domian.FangSelector;
import com.lyun.estate.biz.fang.entity.FangTag;
import com.lyun.estate.biz.fang.repo.FangRepository;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.fang.rest.def.ElevatorFilter;
import com.lyun.estate.biz.spec.fang.rest.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.rest.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.rest.service.FangService;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.supports.types.YN;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    private KeywordService keywordService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Autowired
    private ContractService contractService;

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

        //process
        if (filter.getProcess() == HouseProcess.SUCCESS) {
            selector.setProcess(HouseProcess.SUCCESS);
        } else {
            selector.setProcess(HouseProcess.PUBLISH);
        }

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

        //HouseTypeFilter
        if (filter.getHouseTypeFilters() != null) {
            List<HouseType> houseTypes = new ArrayList<>();
            filter.getHouseTypeFilters().forEach(t -> houseTypes.addAll(t.getTypes()));
            selector.setHouseTypes(houseTypes);
        }

        //houseTags
        selector.decorateFromHouseTags(filter.getHouseTags());

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

        return findFangSummaryBySelector(selector, pageBounds, selector.getProcess() == HouseProcess.SUCCESS);
    }

    private PageList<FangSummary> findFangSummaryBySelector(FangSelector selector, PageBounds pageBounds) {
        return findFangSummaryBySelector(selector, pageBounds, false);
    }

    private PageList<FangSummary> findFangSummaryBySelector(FangSelector selector, PageBounds pageBounds,
                                                            boolean needDealInfo) {
        PageList<FangSummary> summaries = fangRepository.findSummaryBySelector(selector, pageBounds);

        summaries.forEach(summary -> {
                    List<FangTag> fangTags = fangRepository.findTags(summary.getId());
                    summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));

                    summary.decorateTags();

                    summary.setImageURI(Optional.ofNullable(
                            fileService.findFirst(summary.getId(),
                                    DomainType.FANG,
                                    CustomType.SHI_JING,
                                    FileProcess.WATERMARK)).
                            map(FileDescription::getFileURI).orElse(null));

                    if (needDealInfo && summary.getProcess() == HouseProcess.SUCCESS) {
                        Contract contract = contractService.findByFangId(summary.getId());
                        if (contract != null && contract.getProcess() == ContractDefine.Process.SUCCESS) {
                            summary.setDealPrice(contract.getPrice());
                            summary.setDealPriceUnit(contract.getPriceUnit());
                            summary.setDealTime(contract.getCloseTime());
                        }
                    }
                }
        );
        return summaries;
    }

    @Override
    public FangDetail getDetail(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangDetail fangDetail = fangRepository.findDetail(id);
        if (fangDetail == null || fangDetail.getProcess() == HouseProcess.DELEGATE) {
            throw new EstateException(ExCode.NOT_PUBLISH);
        } else {
            List<FangTag> fangTags = fangRepository.findTags(fangDetail.getId());
            fangDetail.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));
            fangDetail.decorateTags();
            fangDetail.setStations(xiaoQuService.findStations(fangDetail.getXiaoQuId()));
            fangDetail.setDescr(fangRepository.findDescr(id));
            return fangDetail;
        }
    }

    @Override
    public FangSummary getSummary(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangSelector selector = new FangSelector().setFangId(id);
        return findFangSummaryBySelector(selector, null).stream().findAny().orElse(null);
    }

    @Override
    public PageList<FangSummary> findSummaryByXiaoQuId(Long xiaoQuId,
                                                       BizType bizType,
                                                       PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        ExceptionUtil.checkNotNull("业务", bizType);

        XiaoQuSummary summary = xiaoQuService.getSummary(xiaoQuId);

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(FangSummaryOrder.DEFAULT.getOrders());

        FangSelector selector = new FangSelector();
        selector.setCityId(summary.getCityId());
        selector.setXiaoQuIds(Lists.newArrayList(xiaoQuId));
        selector.setBizType(bizType);
        selector.setProcess(HouseProcess.PUBLISH);

        return findFangSummaryBySelector(selector, pageBounds);
    }

    @Override
    public PageList<FangSummary> findNearbyByFangId(Long fangId) {
        FangSummary summary = getSummary(fangId);
        if (summary == null || summary.getProcess() == HouseProcess.DELEGATE) {
            return new PageList<>();
        }

        PageBounds pageBounds = new PageBounds(1, 3);
        pageBounds.setContainsTotalCount(false);
        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(FangSummaryOrder.DEFAULT.getOrders());

        FangSelector selector = new FangSelector();
        selector.setCityId(summary.getCityId());
        selector.setBizType(summary.getBizType());
        selector.setSubDistrictId(summary.getSubDistrictId());
        selector.setExcludeIds(Lists.newArrayList(fangId));
        selector.setProcess(HouseProcess.PUBLISH);

        return findFangSummaryBySelector(selector, pageBounds);
    }

    @Override
    @Transactional
    public int updateKeyword(Long fangId, String keyword) {
        return fangRepository.updateKeyword(fangId, keyword);
    }


    @Override
    public List<FileDescription> files(Long ownerId, CustomType customType) {
        customType = Optional.ofNullable(customType).orElse(CustomType.SHI_JING);
        if (Lists.newArrayList(CustomType.SHI_JING, CustomType.HU_XING).contains(customType)) {
            return fileService.find(ownerId, DomainType.FANG, customType, FileProcess.WATERMARK);
        }
        return new ArrayList<>();
    }

    @Override
    public PageList<FangSummary> recommendSellFang(Long cityId, PageBounds pageBounds) {

        List<XiaoQuSummary> xiaoQuSummaries = xiaoQuService.sellCountTopXiaoQu(cityId, new PageBounds(1, 5));

        FangSelector fangSelector = new FangSelector().setCityId(cityId)
                .setBizType(BizType.SELL)
                .setProcess(HouseProcess.PUBLISH)
                .setXiaoQuIds(xiaoQuSummaries.stream().map(XiaoQuSummary::getId).collect(Collectors.toList()));

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(FangSummaryOrder.TIME_DESC.getOrders());

        return findFangSummaryBySelector(fangSelector, pageBounds);
    }

}
