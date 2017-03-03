package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.FangFollowSelector;
import com.lyun.estate.biz.fang.domian.MgtFangSelector;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.fang.repo.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.housedict.entity.Building;
import com.lyun.estate.biz.housedict.entity.BuildingUnit;
import com.lyun.estate.biz.housedict.service.HouseDictService;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.service.HouseLicenceService;
import com.lyun.estate.biz.spec.fang.mgt.entity.FangFollowFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
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
import org.springframework.transaction.annotation.Transactional;

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

    private FangContactRepo fangContactRepo;

    private FangInfoOwnerRepo fangInfoOwnerRepo;

    private FangFollowRepo fangFollowRepo;

    private FangCheckRepo fangCheckRepo;

    private FangDescrRepo fangDescrRepo;

    public MgtFangServiceImpl(MgtFangRepository mgtFangRepository, DepartmentService departmentService,
                              FangRepository fangRepository, FileService fileService,
                              HouseLicenceService licenceService, HouseDictService houseDictService,
                              FangContactRepo fangContactRepo, FangInfoOwnerRepo fangInfoOwnerRepo,
                              FangFollowRepo fangFollowRepo, FangCheckRepo fangCheckRepo, FangDescrRepo fangDescrRepo) {
        this.mgtFangRepository = mgtFangRepository;
        this.departmentService = departmentService;
        this.fangRepository = fangRepository;
        this.fileService = fileService;
        this.licenceService = licenceService;
        this.houseDictService = houseDictService;
        this.fangContactRepo = fangContactRepo;
        this.fangInfoOwnerRepo = fangInfoOwnerRepo;
        this.fangFollowRepo = fangFollowRepo;
        this.fangCheckRepo = fangCheckRepo;
        this.fangDescrRepo = fangDescrRepo;
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

        return findSummaryBySelector(selector, pageBounds);
    }

    private PageList<MgtFangSummary> findSummaryBySelector(MgtFangSelector selector, PageBounds pageBounds) {
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
                    summary.setInfoOwner(mgtFangRepository.findLastFangInfoOwner(summary.getId()));
                }
        );
        return summaries;
    }

    @Override
    public List<FangContact> getContacts(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangContactRepo.findByFangId(fangId);
    }

    @Override
    public List<FangInfoOwner> getInfoOwners(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangInfoOwnerRepo.findByFangId(fangId);
    }

    @Override
    public FangFollow createFollow(FangFollow fangFollow) {
        ExceptionUtil.checkNotNull("fangFollow", fangFollow);
        ExceptionUtil.checkNotNull("房源编号", fangFollow.getFangId());
        ExceptionUtil.checkNotNull("跟进方式", fangFollow.getFollowType());
        ExceptionUtil.checkNotNull("跟进人", fangFollow.getEmployeeId());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(fangFollow.getContent())
                        && fangFollow.getContent().length() > 10,
                "跟进内容不满10个字", fangFollow.getContent());

        if (fangFollowRepo.save(fangFollow) > 0) {
            return fangFollowRepo.findOne(fangFollow.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "跟进记录", fangFollow.toString());
    }

    @Override
    public PageList<FangFollow> getFollows(Long fangId, PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangFollowRepo.findByFangId(fangId, pageBounds);
    }

    @Override
    public FangCheck createCheck(FangCheck fangCheck) {
        ExceptionUtil.checkNotNull("fangCheck", fangCheck);
        ExceptionUtil.checkNotNull("房源编号", fangCheck.getFangId());
        ExceptionUtil.checkNotNull("跟进人", fangCheck.getEmployeeId());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(fangCheck.getAdvantage())
                        && fangCheck.getAdvantage().length() > 10,
                "勘查内容不满10个字", fangCheck.getAdvantage());

        if (fangCheckRepo.save(fangCheck) > 0) {
            return fangCheckRepo.findOne(fangCheck.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "勘查记录", fangCheck.toString());
    }

    @Override
    public PageList<FangCheck> getChecks(Long fangId, PageBounds pageBounds) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangCheckRepo.findByFangId(fangId, pageBounds);
    }

    @Override
    public FangDescr createFangDescr(FangDescr fangDescr) {
        ExceptionUtil.checkNotNull("fangDescr", fangDescr);
        ExceptionUtil.checkNotNull("房源编号", fangDescr.getFangId());
        if (fangDescrRepo.save(fangDescr) > 0) {
            return fangDescrRepo.findOne(fangDescr.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房源描述", fangDescr.toString());
    }

    public String buildHead(String xiaoQuName, Long licenceId) {
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

    @Override
    public FangDescr updateDesc(FangDescr fangDescr) {
        ExceptionUtil.checkNotNull("fangDescr", fangDescr);
        ExceptionUtil.checkNotNull("房源编号", fangDescr.getFangId());
        if (fangDescrRepo.update(fangDescr) > 0) {
            return fangDescrRepo.findByFangId(fangDescr.getFangId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源描述", fangDescr.toString());
    }

    @Override
    public FangDescr findDescr(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangDescrRepo.findByFangId(fangId);
    }

    @Override
    public Fang updateFangBase(Fang fang) {
        ExceptionUtil.checkNotNull("fang", fang);
        ExceptionUtil.checkNotNull("房源编号", fang.getId());
        ExceptionUtil.checkNotNull("房源子类型", fang.getHouseSubType());
        Fang needUpdate = fangRepository.findFang(fang.getId());
        if (needUpdate == null || needUpdate.getDeleted()) {
            throw new EstateException(ExCode.FANG_MGT_ERROR_EMPLOYEE);
        }
        if (fangRepository.updateFang(fang) > 0) {
            return fangRepository.findFang(fang.getId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源基本信息", fang.toString());
    }

    @Override
    @Transactional
    public FangExt updateFangExt(FangExt fangExt) {
        ExceptionUtil.checkNotNull("fangExt", fangExt);
        ExceptionUtil.checkNotNull("房源编号", fangExt.getFangId());
        Fang needUpdate = fangRepository.findFang(fangExt.getFangId());
        if (needUpdate == null || needUpdate.getDeleted()) {
            throw new EstateException(ExCode.FANG_MGT_ERROR_EMPLOYEE);
        }
        if (fangRepository.updateFangExtByFangId(fangExt) > 0) {
            fangRepository.updateTime(fangExt.getFangId());
            return fangRepository.findFangExtByFangId(fangExt.getFangId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源扩展信息", fangExt.toString());
    }

    @Override
    public Fang getFangBase(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangRepository.findFang(fangId);
    }

    @Override
    public FangExt getFangExt(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangRepository.findFangExtByFangId(fangId);

    }

    @Override
    public MgtFangSummary getFangSummary(Long fangId) {
        return findSummaryBySelector(new MgtFangSelector().setFangId(fangId), null)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public PageList<FangFollowDTO> listFollow(FangFollowFilter filter, PageBounds pageBounds) {

        FangFollowSelector selector = new FangFollowSelector();
        BeanUtils.copyProperties(filter, selector);

        //department and children
        if (nonNull(filter.getDepartmentId())) {
            if (Objects.equals(true, filter.getChildren())) {
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

        //info owner department and children
        if (nonNull(filter.getIoDepartmentId())) {
            if (Objects.equals(true, filter.getIoChildren())) {
                Department ioDepartment = departmentService.selectById(filter.getIoDepartmentId());
                if (nonNull(ioDepartment)) {
                    Set<Long> ioChildIds = departmentService.findChildIds(ioDepartment.getCompanyId(),
                            filter.getDepartmentId());
                    selector.setIoDepartmentIds(Lists.newArrayList(ioChildIds));
                }
            } else {
                selector.setIoDepartmentIds(Lists.newArrayList(filter.getIoDepartmentId()));
            }
        }


        return fangFollowRepo.listBySelector(selector, pageBounds);
    }
}
