package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.fang.domian.MgtFangSelector;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.fang.repo.FangRepository;
import com.lyun.estate.biz.fang.repo.MgtFangRepository;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.service.HouseLicenceService;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtFangServiceImpl implements MgtFangService {

    private Logger logger = LoggerFactory.getLogger(MgtFangServiceImpl.class);

    private MgtFangRepository mgtFangRepository;

    private DepartmentService departmentService;

    private FangRepository fangRepository;

    private FileService fileService;

    private HouseLicenceService licenceService;

    private HouseDictService houseDictService;

    public MgtFangServiceImpl(MgtFangRepository mgtFangRepository, DepartmentService departmentService,
                              FangRepository fangRepository, FileService fileService,
                              HouseLicenceService licenceService, HouseDictService houseDictService) {
        this.mgtFangRepository = mgtFangRepository;
        this.departmentService = departmentService;
        this.fangRepository = fangRepository;
        this.fileService = fileService;
        this.licenceService = licenceService;
        this.houseDictService = houseDictService;
    }

    @Override
    public Fang createFang(Fang fang) {
        if (mgtFangRepository.saveFang(fang) > 0) {
            return mgtFangRepository.findFang(fang.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房", fang.toString());
    }

    @Override
    public FangExt createFangExt(FangExt fangExt) {
        if (mgtFangRepository.saveFangExt(fangExt) > 0) {
            return mgtFangRepository.findFangExt(fangExt.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房扩展信息", fangExt.toString());

    }

    @Override
    public FangContact createFangContact(FangContact fangContact) {
        boolean flag = checkFangContact(fangContact);
        ExceptionUtil.checkIllegal(flag, "联系方式", fangContact.toString());
        if (mgtFangRepository.saveFangContact(fangContact) > 0) {
            return mgtFangRepository.findFangContact(fangContact.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "联系方式", fangContact.toString());
    }

    @Override
    public boolean checkFangContact(FangContact fangContact) {
        ExceptionUtil.checkNotNull("联系信息", fangContact);
        boolean flag;
        switch (fangContact.getContactType()) {
            case MOBILE:
                flag = ValidateUtil.isMobile(fangContact.getContactInfo());
                break;
            case QQ:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case WECHAT:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case PHONE:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case EMAIL:
                flag = ValidateUtil.isEmail(fangContact.getContactInfo());
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }

    @Override
    public FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner) {
        if (mgtFangRepository.saveFangInfoOwner(fangInfoOwner) > 0) {
            return mgtFangRepository.findFangInfoOwner(fangInfoOwner.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "联系方式", fangInfoOwner.toString());

    }

    @Override
    public PageList<MgtFangSummary> listSummary(MgtFangFilter filter, MgtFangSummaryOrder order,
                                                PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("过滤条件", filter);
        ExceptionUtil.checkNotNull("城市", filter.getCityId());
        ExceptionUtil.checkNotNull("排序条件", order);
        ExceptionUtil.checkNotNull("pageBounds", pageBounds);

        pageBounds.getOrders().clear();
        pageBounds.getOrders().addAll(order.getOrders());

        PageList<FangSummary> empty = new PageList<>(Lists.newArrayList(),
                new Paginator(pageBounds.getPage(), pageBounds.getLimit(), 0));


        MgtFangSelector selector = new MgtFangSelector();

        BeanUtils.copyProperties(filter, selector);

        //houseTags
        selector.decorateFromHouseTags(filter.getHouseTags());

        //times
        if (nonNull(filter.getTimeType())
                && (nonNull(filter.getStartTime()) || nonNull(filter.getEndTime()))) {
            switch (filter.getTimeType()) {
                case CREATE_TIME:
                    selector.setMinCreateTime(filter.getStartTime()).setMaxCreateTime(filter.getEndTime());
                    break;
                case DELEGATE_TIME:
                    selector.setMinDelegateTime(filter.getStartTime()).setMaxDelegateTime(filter.getEndTime());
                    break;
                case PUBLISH_TIME:
                    selector.setMinPublishTime(filter.getStartTime()).setMaxPublishTime(filter.getEndTime());
                    break;
                case FOLLOW_TIME:
                    selector.setMinFollowTime(filter.getStartTime()).setMaxFollowTime(filter.getEndTime());
                    break;
            }
        }

        //departmentId and include children
        if (nonNull(filter.getDepartmentId())) {
            if (Objects.equals(true, filter.getIncludeChildren())) {
                Department department = departmentService.selectById(filter.getDepartmentId());
                if (nonNull(department)) {
                    Set<Long> childIds = departmentService.findChildIds(department.getCompanyId(),
                            filter.getDepartmentId());
                    selector.setDepartmentIds(Lists.newArrayList(childIds));
                }
            } else {
                selector.setDepartmentIds(Lists.newArrayList(filter.getDepartmentId()));
            }
        }


        PageList<MgtFangSummary> summaries = mgtFangRepository.listSummary(selector, pageBounds);

        summaries.forEach(summary -> {
                    ExceptionUtil.checkNotNull("房源编号：" + summary.getId() + "的授权编号", summary.getLicenceId());
                    summary.setHead(buildHead(summary.getXiaoQuName(), summary.getLicenceId()));
                    List<FangTag> fangTags = fangRepository.findTags(summary.getId());

                    summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));
                    summary.decorateTags();
                    summary.setImageURI(Optional.ofNullable(
                            fileService.findFirst(summary.getId(),
                                    DomainType.FANG,
                                    CustomType.SHI_JING,
                                    FileProcess.WATERMARK)).
                            map(FileDescription::getFileURI).orElse(null));
                }
        );
        return summaries;

    }

    private String buildHead(String xiaoQuName, Long licenceId) {
        StringBuilder headBuilder = new StringBuilder(xiaoQuName);
        HouseLicence licence = licenceService.findOne(licenceId);
        if (licence == null) {
            logger.error("房源授权未找到，编号：" + licenceId);
        } else {
            Building building = houseDictService.findBuildingAndUnit(licence.getBuildingId(),
                    licence.getBuildingUnitId());
            if (building == null) {
                logger.error("房源授权楼栋信息未找到，编号：" + licenceId);
            } else if (CollectionUtils.isEmpty(building.getUnits())) {
                logger.error("房源授权单元信息未找到，编号：" + licenceId);
            } else {
                headBuilder.append(" ").append(building.getName()).append(" ")
                        .append(building.getUnits().stream().findAny().map(BuildingUnit::getUnitName).orElse(""))
                        .append(" ").append(licence.getHouseNo());
            }
        }
        return headBuilder.toString();
    }
}
