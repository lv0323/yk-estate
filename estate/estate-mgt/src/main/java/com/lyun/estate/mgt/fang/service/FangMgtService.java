package com.lyun.estate.mgt.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.def.FollowType;
import com.lyun.estate.biz.fang.def.HouseProcess;
import com.lyun.estate.biz.fang.def.InfoOwnerReason;
import com.lyun.estate.biz.fang.def.PriceUnit;
import com.lyun.estate.biz.fang.domian.FangCheckDTO;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.FangInfoOwnerDTO;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.entity.FileExt;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.service.HouseLicenceService;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.permission.service.GrantService;
import com.lyun.estate.biz.spec.fang.mgt.entity.*;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import com.lyun.estate.mgt.supports.AuditHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class FangMgtService {

    @Autowired
    private HouseLicenceService houseLicenceService;
    @Autowired
    private MgtXiaoQuService mgtXiaoQuService;
    @Autowired
    private MgtFangService mgtFangService;
    @Autowired
    private MgtContext mgtContext;
    @Autowired
    private AuditService auditService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PermissionCheckService permissionCheckService;
    @Autowired
    private GrantService grantService;

    private Logger logger = LoggerFactory.getLogger(FangMgtService.class);


    @Transactional
    public Fang createFang(HouseLicence houseLicence, Fang fang, FangExt fangExt, FangContact contact) {

        ExceptionUtil.checkNotNull("字典编号", houseLicence);
        ExceptionUtil.checkNotNull("基本信息", fang);
        ExceptionUtil.checkNotNull("扩展信息", fangExt);
        ExceptionUtil.checkNotNull("联系方式", fangExt);

        permissionCheckService.checkExist(Permission.CREATE_FANG);

        fillFang(fang);

        fang.setProcess(HouseProcess.DELEGATE);

        //register houseLicence
        XiaoQu xiaoQu = mgtXiaoQuService.findOne(fang.getXiaoQuId());
        if (xiaoQu == null) {
            throw new EstateException(ExCode.NOT_FOUND, fang.getXiaoQuId(), "小区");
        }

        HouseLicence licence = houseLicenceService.register(xiaoQu.getCommunityId(),
                houseLicence.getBizType(),
                houseLicence.getBuildingId(),
                houseLicence.getBuildingUnitId(),
                houseLicence.getHouseNo());

        //fang
        fang.setLicenceId(licence.getId());
        Fang result = mgtFangService.createFang(fang);

        //fangExt
        fangExt.setFangId(result.getId());
        mgtFangService.createFangExt(fangExt);

        //fangContact
        mgtFangService.createFangContact(contact.setFangId(result.getId()));

        //fangInfoOwner
        Operator operator = mgtContext.getOperator();
        mgtFangService.createFangInfoOwner(new FangInfoOwner().setFangId(result.getId())
                .setCompanyId(operator.getCompanyId()).setDepartmentId(operator.getDepartmentId())
                .setEmployeeId(operator.getId()).setReason(InfoOwnerReason.CREATE));

        //fangDescr
        StringBuilder titleBuilder = new StringBuilder();
        titleBuilder.append(xiaoQu.getName()).append(" ").append(fang.getsCounts()).append("室");
        if (fang.gettCounts() > 0) {
            titleBuilder.append(fang.gettCounts()).append("厅");
        }
        titleBuilder.append(" ").append(fang.getPublishPrice()).append(fang.getPriceUnit().getLabel());

        mgtFangService.createFangDescr(new FangDescr()
                .setFangId(result.getId())
                .setTitle(titleBuilder.toString()));

        //audit
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_P, result.getId(), DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) + "创建了授权编号为【" + result.getLicenceId() + "】的房源")
        );
        return result;
    }

    private Fang fillFang(Fang fang) {

        if (fang.getRealArea() == null) {
            fang.setRealArea(fang.getEstateArea());
        }

        fang.setFloorType(mgtFangService.calculateFloorType(fang.getFloor(), fang.getFloorCounts()));

        BigDecimal publishPrice = fang.getPublishPrice();
        if (fang.getBizType() == BizType.SELL && fang.getPriceUnit() == PriceUnit.WAN) {
            publishPrice = fang.getPublishPrice().multiply(new BigDecimal(10000));
        }

        fang.setUnitPrice(publishPrice.divide(fang.getEstateArea(), 0, RoundingMode.HALF_UP));

        return fang;
    }

    public Boolean preCheckLicence(Long xiaoquId, BizType bizType, Long buildingId, Long buildingUnitId,
                                   String houseNo) {

        permissionCheckService.checkExist(Permission.CREATE_FANG);

        XiaoQu xiaoQu = mgtXiaoQuService.findOne(xiaoquId);
        HouseLicence licence = houseLicenceService.findActive(xiaoQu.getCommunityId(),
                bizType,
                buildingId,
                buildingUnitId,
                houseNo);
        return licence == null;
    }

    public PageList<MgtFangSummary> listSummary(MgtFangFilter filter, MgtFangSummaryOrder order,
                                                PageBounds pageBounds) {
        Operator operator = mgtContext.getOperator();

        //permission check && biz type set
        if (!operator.getSysAdmin()) {
            Map<Permission, Grant> employeeGrantsMap = grantService.getEmployeeGrantsMap(operator.getId());
            boolean canListSell = employeeGrantsMap.get(Permission.LIST_FANG_SELL) != null;
            boolean canListRent = employeeGrantsMap.get(Permission.LIST_FANG_RENT) != null;
            if (filter.getBizType() == BizType.SELL && !canListSell) {
                throw new EstateException(ExCode.PERMISSION_ERROR);
            } else if (filter.getBizType() == BizType.RENT && !canListRent) {
                throw new EstateException(ExCode.PERMISSION_ERROR);
            } else if (filter.getBizType() == null) {
                if (!canListSell && !canListRent) {
                    throw new EstateException(ExCode.PERMISSION_ERROR);
                }
                if (canListSell && canListRent) {
                    //do nothing
                } else if (canListSell) {
                    filter.setBizType(BizType.SELL);
                } else {
                    filter.setBizType(BizType.RENT);
                }
            }
        }

        if (nonNull(filter.getDepartmentId())) {
            Department department = departmentService.selectById(filter.getDepartmentId());
            if (isNull(department) ||
                    !Objects.equals(department.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_DEPT);
            }
        }
        if (nonNull(filter.getEmployeeId())) {
            Employee employee = employeeService.selectById(filter.getEmployeeId());
            if (!Objects.equals(employee.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_EMPLOYEE);
            }
        }

        return mgtFangService.listSummary(filter, order, pageBounds);
    }

    @Transactional
    public FangContact getContact(Long fangId) {

        permissionCheckService.checkScope(fangId, Permission.VIEW_FANG_CONTACT);

        Fang fang = mgtFangService.getFangBase(fangId);

        if (fang.getBizType() == BizType.SELL) {
            permissionCheckService.checkLimit(Permission.VIEW_SELL_CONTACT_LIMIT);
        } else {
            permissionCheckService.checkLimit(Permission.VIEW_RENT_CONTACT_LIMIT);
        }

        FangContact result = mgtFangService.getContact(fangId);

        employeeService.updateFollowFangId(mgtContext.getOperator().getId(), fangId, fang.getBizType());

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_OWNER, fangId, DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) + "查看了授权编号为【" + fang.getLicenceId() + "】的房东联系方式")
        );

        return result;
    }

    @Transactional
    public List<FangInfoOwnerDTO> getSuccessiveInfoOwners(Long fangId) {
        return mgtFangService.getSuccessiveInfoOwners(fangId);
    }

    @Transactional
    public FangFollow createFollow(Long fangId, FollowType followType, String content) {
        permissionCheckService.checkScope(fangId, Permission.MODIFY_FANG_INFO);

        Operator operator = mgtContext.getOperator();

        Fang fangBase = mgtFangService.getFangBase(fangId);

        employeeService.clearFollowFangId(operator.getId(), fangId, fangBase.getBizType());

        return mgtFangService.createFollow(
                new FangFollow().setFangId(fangId)
                        .setCompanyId(operator.getCompanyId())
                        .setDepartmentId(operator.getDepartmentId())
                        .setEmployeeId(operator.getId())
                        .setFollowType(followType)
                        .setContent(content)
        );
    }

    public FangCheck createCheck(Long fangId, String advantage, String disAdvantage) {
        permissionCheckService.checkScope(fangId, Permission.MODIFY_FANG_INFO);
        Operator operator = mgtContext.getOperator();
        return mgtFangService.createCheck(
                new FangCheck().setFangId(fangId)
                        .setCompanyId(operator.getCompanyId())
                        .setDepartmentId(operator.getDepartmentId())
                        .setEmployeeId(operator.getId())
                        .setAdvantage(advantage)
                        .setDisAdvantage(disAdvantage)
        );
    }

    public PageList<FangCheckDTO> listCheck(FangCheckFilter filter, PageBounds pageBounds) {
        filter.setCompanyId(mgtContext.getOperator().getCompanyId());
        return mgtFangService.listCheck(filter, pageBounds);
    }

    public FileDescription createImage(Long fangId, CustomType customType, InputStream inputStream, String suffix) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        ExceptionUtil.checkNotNull("文件业务类型", customType);
        ExceptionUtil.checkNotNull("文件输入流", inputStream);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(suffix), "文件后缀名", suffix);

        permissionCheckService.checkScope(fangId, Permission.MODIFY_FANG_INFO);

        if (customType == CustomType.SHI_JING || customType == CustomType.HU_XING || customType == CustomType.CERTIF
                || customType == CustomType.OWNER_ID_CARD || customType == CustomType.ATTORNEY) {
            FileDescription fileDescription = new FileDescription()
                    .setOwnerId(fangId)
                    .setOwnerType(DomainType.FANG)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.WATERMARK.getFlag())
                    .setCustomType(customType)
                    .setExt(new FileExt().setCreateById(mgtContext.getOperator().getId()).toJsonString());
            return fileService.save(fileDescription, inputStream, suffix);
        }
        throw new EstateException(ExCode.CUSTOM_TYPE_NOT_SUPPORTED, customType);
    }

    public List<FileDescription> getImages(Long fangId, CustomType customType) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        ExceptionUtil.checkNotNull("文件业务类型", customType);

        return fileService.find(fangId, DomainType.FANG, customType, FileProcess.WATERMARK);
    }

    @Transactional
    public FangDescr updateDesc(FangDescr fangDescr) {
        permissionCheckService.checkScope(fangDescr.getFangId(), Permission.MODIFY_FANG_INFO);

        Fang fang = mgtFangService.getFangBase(fangDescr.getFangId());
        FangDescr result = mgtFangService.updateDesc(fangDescr);
        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_M, fangDescr.getFangId(), DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了授权编号为【" + fang.getLicenceId() + "】的描述为：【" + fangDescr.toString() + "】")
        );
        return result;
    }

    public FangDescr findDescr(Long fangId) {
        return mgtFangService.findDescr(fangId);
    }

    @Transactional
    public Fang changeFangBase(Fang fang) {
        permissionCheckService.checkScope(fang.getId(), Permission.UPDATE_FANG_BASE);

        Fang needUpdate = mgtFangService.getFangBase(fang.getId());

        fang.setBizType(needUpdate.getBizType());
        fillFang(fang);

        Fang result = mgtFangService.updateFangBase(fang);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_M, fang.getId(), DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了授权编号为【" + result.getLicenceId() + "】的基本信息为：【" + fang.toString() + "】")
        );
        return result;
    }

    @Transactional
    public FangExt changeFangExt(FangExt fangExt) {
        permissionCheckService.checkScope(fangExt.getFangId(), Permission.UPDATE_FANG_EXT);

        Fang fang = mgtFangService.getFangBase(fangExt.getFangId());
        FangExt result = mgtFangService.updateFangExt(fangExt);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_M, fangExt.getFangId(), DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了授权编号为【" + fang.getLicenceId() + "】的扩展信息为：【" + fangExt.toString() + "】")
        );
        return result;
    }

    public MgtFangSummary getFangSummary(Long fangId) {
        return mgtFangService.getFangSummary(fangId);
    }


    public Fang getFangBase(Long fangId) {
        return mgtFangService.getFangBase(fangId);
    }

    public FangExt getFangExt(Long fangId) {
        return mgtFangService.getFangExt(fangId);
    }

    public PageList<FangFollowDTO> listFollow(FangFollowFilter filter, PageBounds pageBounds) {
        Operator operator = mgtContext.getOperator();

        if (nonNull(filter.getDepartmentId())) {
            Department department = departmentService.selectById(filter.getDepartmentId());
            if (isNull(department) ||
                    !Objects.equals(department.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_DEPT);
            }
        }
        if (nonNull(filter.getIoDepartmentId())) {
            Department ioDepartment = departmentService.selectById(filter.getIoDepartmentId());
            if (isNull(ioDepartment) ||
                    !Objects.equals(ioDepartment.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_DEPT);
            }
        }
        if (nonNull(filter.getEmployeeId())) {
            Employee employee = employeeService.selectById(filter.getEmployeeId());
            if (!Objects.equals(employee.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_EMPLOYEE);
            }
        }
        if (nonNull(filter.getIoEmployeeId())) {
            Employee ioEmployee = employeeService.selectById(filter.getIoEmployeeId());
            if (!Objects.equals(ioEmployee.getCompanyId(), operator.getCompanyId())) {
                throw new EstateException(ExCode.FANG_MGT_ERROR_EMPLOYEE);
            }
        }

        filter.setCompanyId(operator.getCompanyId());

        return mgtFangService.listFollow(filter, pageBounds);
    }

    public MgtFangTiny getFangTinyByLicenceId(Long licenceId) {
        return mgtFangService.getFangTinyByLicenceId(licenceId);
    }

    public Boolean deleteImage(Long fileId) {
        FileDescription fileDescription = fileService.findOne(fileId);
        if (fileDescription == null || fileDescription.getDeleted()) {
            throw new EstateException(ExCode.FILE_NOT_EXIST);
        }
        if (fileDescription.getOwnerType() != DomainType.FANG) {
            throw new EstateException("文件归属类型不正确");
        }

        permissionCheckService.checkScope(fileDescription.getOwnerId(),
                map2Permission(fileDescription.getCustomType()));

        logger.info("员工{} 删除了图片{}", mgtContext.getOperator().getId(), fileId);
        return fileService.delete(fileId);

    }

    private Permission map2Permission(CustomType customType) {
        switch (customType) {
            case SHI_JING:
                return Permission.DEL_FANG_IMG_SHI_JING;
            case HU_XING:
                return Permission.DEL_FANG_IMG_HU_XING;
            case CERTIF:
                return Permission.DEL_FANG_IMG_CERTIF;
            case ATTORNEY:
                return Permission.DEL_FANG_IMG_ATTORNEY;
            case OWNER_ID_CARD:
                return Permission.DEL_FANG_IMG_ID_CARD;
        }
        return null;
    }

    public Boolean setFirstImage(Long fileId) {
        FileDescription fileDescription = fileService.findOne(fileId);

        if (fileDescription == null || fileDescription.getDeleted()) {
            throw new EstateException(ExCode.FILE_NOT_EXIST);
        }
        if (fileDescription.getOwnerType() != DomainType.FANG) {
            throw new EstateException("文件归属类型不正确");
        }
        permissionCheckService.checkScope(fileDescription.getOwnerId(),
                map2Permission(fileDescription.getCustomType()));

        logger.info("员工{} 将图片{}置顶", mgtContext.getOperator().getId(), fileId);
        return fileService.setFirst(fileId);
    }

    @Transactional
    public FangContact updateContact(FangContact contact) {
        permissionCheckService.checkScope(contact.getFangId(), Permission.MODIFY_FANG_CONTACT);

        Fang fang = mgtFangService.getFangBase(contact.getFangId());
        FangContact result = mgtFangService.updateContact(contact);

        auditService.save(
                AuditHelper.build(mgtContext, AuditSubject.FANG_OWNER, contact.getFangId(), DomainType.FANG,
                        AuditHelper.operatorName(mgtContext) +
                                "修改了授权编号为【" + fang.getLicenceId() + "】的房东联系方式为【" + contact.toString() + "】")
        );

        return result;
    }

    public MgtFangSummary summaryByLicenceId(Long licenceId) {
        return mgtFangService.getFangSummaryByLicenceId(licenceId);
    }
}
