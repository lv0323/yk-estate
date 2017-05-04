package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.domian.*;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.fang.repo.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.spec.fang.mgt.entity.*;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private MgtFangRepository mgtFangRepository;

    private DepartmentService departmentService;

    private FangRepository fangRepository;

    private FileService fileService;

    private FangContactRepo fangContactRepo;

    private FangInfoOwnerRepo fangInfoOwnerRepo;

    private FangFollowRepo fangFollowRepo;

    private FangCheckRepo fangCheckRepo;

    private FangDescrRepo fangDescrRepo;

    private EmployeeService employeeService;

    public MgtFangServiceImpl(MgtFangRepository mgtFangRepository, DepartmentService departmentService,
                              FangRepository fangRepository, FileService fileService,
                              FangContactRepo fangContactRepo, FangInfoOwnerRepo fangInfoOwnerRepo,
                              FangFollowRepo fangFollowRepo, FangCheckRepo fangCheckRepo, FangDescrRepo fangDescrRepo,
                              EmployeeService employeeService) {
        this.mgtFangRepository = mgtFangRepository;
        this.departmentService = departmentService;
        this.fangRepository = fangRepository;
        this.fileService = fileService;
        this.fangContactRepo = fangContactRepo;
        this.fangInfoOwnerRepo = fangInfoOwnerRepo;
        this.fangFollowRepo = fangFollowRepo;
        this.fangCheckRepo = fangCheckRepo;
        this.fangDescrRepo = fangDescrRepo;
        this.employeeService = employeeService;
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
        ExceptionUtil.checkNotNull("联系方式", fangContact);
        ExceptionUtil.checkNotNull("房源编号", fangContact.getFangId());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(fangContact.getName()), "房东姓名", fangContact.getName());
        ExceptionUtil.checkIllegal(ValidateUtil.isMobile(fangContact.getMobile()), "手机", fangContact.getMobile());
        ExceptionUtil.checkIllegal(Strings.isNullOrEmpty(fangContact.getEmail()) || ValidateUtil.isEmail(fangContact.getEmail()),
                "邮箱",
                fangContact.getEmail());
        if (fangContactRepo.saveFangContact(fangContact) > 0) {
            return fangContactRepo.findFangContact(fangContact.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "联系方式", fangContact.toString());
    }

    @Override
    public FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner) {
        if (fangInfoOwnerRepo.saveFangInfoOwner(fangInfoOwner) > 0) {
            return fangInfoOwnerRepo.findFangInfoOwner(fangInfoOwner.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "归属入", fangInfoOwner.toString());
    }

    @Override
    @Transactional
    public FangInfoOwner changeFangInfoOwner(FangInfoOwner fangInfoOwner) {
        ExceptionUtil.checkNotNull("归属信息", fangInfoOwner);
        ExceptionUtil.checkNotNull("房源编号", fangInfoOwner.getFangId());
        ExceptionUtil.checkNotNull("员工信息", fangInfoOwner.getEmployeeId());
        ExceptionUtil.checkNotNull("归属原因", fangInfoOwner.getReason());

        fangInfoOwnerRepo.deleteFangInfoOwner(fangInfoOwner.getFangId());

        Fang fang = mgtFangRepository.findFang(fangInfoOwner.getFangId());

        employeeService.clearAllFollowFangId(fangInfoOwner.getFangId(), fang.getBizType());

        return createFangInfoOwner(fangInfoOwner);
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
                    summary.setHead(buildHead(summary));
                    List<FangTag> fangTags = fangRepository.findTags(summary.getId());

                    summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));
                    summary.decorateTags();
                    summary.setImageURI(Optional.ofNullable(
                            fileService.findFirst(summary.getId(),
                                    DomainType.FANG,
                                    CustomType.SHI_JING,
                                    FileProcess.WATERMARK)).
                            map(FileDescription::getFileURI).orElse(null));
                    summary.setInfoOwner(fangInfoOwnerRepo.findLastFangInfoOwner(summary.getId()));
                }
        );
        return summaries;
    }

    private String buildHead(MgtFangSummary summary) {
        return summary.getXiaoQuName() + " " + summary.getsCounts() + "室" + summary.gettCounts() + "厅" + " "
                + summary.getPublishPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + summary.getPriceUnit().getLabel();
    }

    @Override
    public FangContact getContact(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangContactRepo.findByFangId(fangId);
    }

    @Override
    public List<FangInfoOwnerDTO> getSuccessiveInfoOwners(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangInfoOwnerRepo.getSuccessiveInfoOwners(fangId);
    }

    @Override
    public FangInfoOwnerDTO findLastFangInfoOwner(long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return fangInfoOwnerRepo.findLastFangInfoOwner(fangId);
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
    public PageList<FangCheckDTO> listCheck(FangCheckFilter filter, PageBounds pageBounds) {
        FangCheckSelector selector = new FangCheckSelector();
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

        PageList<FangCheckDTO> result = fangCheckRepo.list(selector, pageBounds);
        result.forEach(t -> {
            t.setAvatarURI(employeeService.getAvatarURI(t.getEmployeeId()));
            t.setFangTiny(getFangTiny(t.getFangId()));
        });
        return result;
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
        Fang needUpdate = mgtFangRepository.findFang(fang.getId());
        if (needUpdate == null || needUpdate.getDeleted()) {
            throw new EstateException(ExCode.NOT_FOUND, fang.getId(), "房源信息");
        }
        if (mgtFangRepository.updateFang(fang) > 0) {
            return mgtFangRepository.findFang(fang.getId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源基本信息", fang.toString());
    }

    @Override
    @Transactional
    public FangExt updateFangExt(FangExt fangExt) {
        ExceptionUtil.checkNotNull("fangExt", fangExt);
        ExceptionUtil.checkNotNull("房源编号", fangExt.getFangId());
        Fang needUpdate = mgtFangRepository.findFang(fangExt.getFangId());
        if (needUpdate == null || needUpdate.getDeleted()) {
            throw new EstateException(ExCode.NOT_FOUND, fangExt.getFangId(), "房源信息");
        }
        if (mgtFangRepository.updateFangExtByFangId(fangExt) > 0) {
            mgtFangRepository.updateTime(fangExt.getFangId());
            return mgtFangRepository.findFangExtByFangId(fangExt.getFangId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "房源扩展信息", fangExt.toString());
    }

    @Override
    public Fang getFangBase(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return mgtFangRepository.findFang(fangId);
    }

    @Override
    public FangExt getFangExt(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        return mgtFangRepository.findFangExtByFangId(fangId);

    }

    @Override
    public MgtFangSummary getFangSummary(Long fangId) {
        return findSummaryBySelector(new MgtFangSelector().setFangId(fangId), null)
                .stream()
                .findFirst().orElseThrow(() -> new EstateException(ExCode.NOT_FOUND, fangId, "房源信息"));
    }

    @Override
    public MgtFangTiny getFangTiny(Long fangId) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        MgtFangTiny tiny = mgtFangRepository.getMgtFangTiny(fangId);
        tiny.setFangHead(tiny.getXiaoQuName() + " " +
                tiny.getsCounts() + "室" + tiny.gettCounts() + "厅" + " "
                + tiny.getPublishPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + tiny.getPriceUnit()
                .getLabel());
        return tiny;
    }

    @Override
    public MgtFangTiny getFangTinyByLicenceId(Long licenceId) {
        ExceptionUtil.checkNotNull("房源授权编号", licenceId);
        MgtFangTiny tiny = mgtFangRepository.getMgtFangTinyByLicenceId(licenceId);
        tiny.setFangHead(tiny.getXiaoQuName() + " " +
                tiny.getsCounts() + "室" + tiny.gettCounts() + "厅" + " "
                + tiny.getPublishPrice().setScale(2, BigDecimal.ROUND_HALF_UP) + tiny.getPriceUnit()
                .getLabel());
        return tiny;
    }

    @Override
    public MgtFangSummary getFangSummaryByLicenceId(Long licenceId) {
        return findSummaryBySelector(new MgtFangSelector().setLicenceId(licenceId), null)
                .stream()
                .findFirst().orElseThrow(() -> new EstateException(ExCode.NOT_FOUND, licenceId, "房源信息"));
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

        PageList<FangFollowDTO> result = fangFollowRepo.listBySelector(selector, pageBounds);

        result.forEach(t -> {
            t.setAvatarURI(employeeService.getAvatarURI(t.getEmployeeId()));
            t.setFangTiny(getFangTiny(t.getFangId()));
        });

        return result;
    }

    @Override
    public FangContact updateContact(FangContact fangContact) {
        ExceptionUtil.checkNotNull("联系方式", fangContact);
        ExceptionUtil.checkNotNull("房源编号", fangContact.getFangId());
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(fangContact.getName()), "房东姓名", fangContact.getName());
        ExceptionUtil.checkIllegal(ValidateUtil.isMobile(fangContact.getMobile()), "手机", fangContact);
        ExceptionUtil.checkIllegal(Strings.isNullOrEmpty(fangContact.getEmail()) || ValidateUtil.isEmail(fangContact.getEmail()),
                "邮箱",
                fangContact.getEmail());
        if (fangContactRepo.updateByFangId(fangContact) > 0) {
            return fangContactRepo.findByFangId(fangContact.getFangId());
        }
        throw new EstateException(ExCode.UPDATE_FAIL, "联系方式", fangContact.toString());
    }
}
