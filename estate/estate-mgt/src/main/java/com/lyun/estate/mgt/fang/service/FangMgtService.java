package com.lyun.estate.mgt.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.employee.service.EmployeeService;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.domian.FangCheckDTO;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.service.HouseLicenceService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class FangMgtService {

    private HouseLicenceService houseLicenceService;

    private MgtXiaoQuService mgtXiaoQuService;

    private MgtFangService mgtFangService;

    private MgtContext mgtContext;

    private AuditService auditService;

    private DepartmentService departmentService;

    private EmployeeService employeeService;

    private FileService fileService;

    public FangMgtService(HouseLicenceService houseLicenceService,
                          MgtXiaoQuService mgtXiaoQuService, MgtFangService mgtFangService, MgtContext mgtContext,
                          AuditService auditService, DepartmentService departmentService,
                          EmployeeService employeeService, FileService fileService) {
        this.houseLicenceService = houseLicenceService;
        this.mgtXiaoQuService = mgtXiaoQuService;
        this.mgtFangService = mgtFangService;
        this.mgtContext = mgtContext;
        this.auditService = auditService;
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.fileService = fileService;
    }

    @Transactional
    public Fang createFang(HouseLicence houseLicence, Fang fang, FangExt fangExt, List<FangContact> contacts) {

        ExceptionUtil.checkNotNull("字典编号", houseLicence);
        ExceptionUtil.checkNotNull("基本信息", fang);
        ExceptionUtil.checkNotNull("扩展信息", fangExt);
        ExceptionUtil.checkIllegal(!CollectionUtils.isEmpty(contacts), "联系方式", fangExt);

        fillFang(fang);

        fang.setProcess(HouseProcess.DELEGATE);

        checkFangContact(contacts);

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
        contacts.forEach(c -> {
            c.setFangId(result.getId());
            mgtFangService.createFangContact(c);
        });

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
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_A_R)
                .setTargetId(result.getId())
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】创建了房源编号为【" + result.getId() + "】的房源")
        );
        return result;
    }

    private void checkFangContact(List<FangContact> contacts) {
        List<FangContact> checkFaileds = new ArrayList<>();
        contacts.forEach(t -> {
            if (!mgtFangService.checkFangContact(t)) {
                checkFaileds.add(t);
            }
        });
        ExceptionUtil.checkIllegal(CollectionUtils.isEmpty(checkFaileds), "联系方式", checkFaileds);
    }


    private Fang fillFang(Fang fang) {

        if (fang.getRealArea() == null) {
            fang.setRealArea(fang.getEstateArea());
        }

        fang.setFloorType(calculateFloorType(fang.getFloor(), fang.getFloorCounts()));

        BigDecimal publishPrice = fang.getPublishPrice();
        if (fang.getBizType() == BizType.SELL && fang.getPriceUnit() == PriceUnit.WAN) {
            publishPrice = fang.getPublishPrice().multiply(new BigDecimal(10000));
        }

        fang.setUnitPrice(publishPrice.divide(fang.getEstateArea(), 2, RoundingMode.HALF_UP));

        return fang;
    }

    private FloorType calculateFloorType(int floor, int floorCounts) {
        if (floor <= 0 || floorCounts <= 0 || floor > floorCounts) {
            return null;
        }
        Integer low = floorCounts / 3;
        Integer high = floorCounts * 2 / 3;

        if (floor <= low) {
            return FloorType.LOW;
        } else if (floor > high) {
            return FloorType.HIGH;
        } else {
            return FloorType.MIDDLE;
        }
    }

    public Boolean preCheckLicence(Long xiaoquId, BizType bizType, Long buildingId, Long buildingUnitId,
                                   String houseNo) {
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
    public List<FangContact> getContacts(Long fangId) {

        List<FangContact> result = mgtFangService.getContacts(fangId);

        Operator operator = mgtContext.getOperator();
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_OWNER)
                .setTargetId(fangId)
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】查看了房源编号为【" + fangId + "】的房东联系方式")
        );

        return result;
    }

    public List<FangInfoOwner> getInfoOwners(Long fangId) {
        return mgtFangService.getInfoOwners(fangId);
    }

    public FangFollow createFollow(Long fangId, FollowType followType, String content) {
        Operator operator = mgtContext.getOperator();

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
        ExceptionUtil.checkNotNull("文件业务类型", fangId);
        ExceptionUtil.checkNotNull("文件输入流", inputStream);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(suffix), "文件后缀名", suffix);
        if (customType == CustomType.SHI_JING || customType == CustomType.HU_XING || customType == CustomType.CERTIF) {
            FileDescription fileDescription = new FileDescription()
                    .setOwnerId(fangId)
                    .setOwnerType(DomainType.FANG)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.WATERMARK.getFlag())
                    .setCustomType(customType)
                    .setExt("operator=" + mgtContext.getOperator().getId());
            return fileService.save(fileDescription, inputStream, suffix);
        }
        throw new EstateException(ExCode.CUSTOM_TYPE_NOT_SUPPORTED, customType);
    }

    public List<FileDescription> getImages(Long fangId, CustomType customType) {
        ExceptionUtil.checkNotNull("房源编号", fangId);
        ExceptionUtil.checkNotNull("房源编号", customType);

        return fileService.find(fangId, DomainType.FANG, customType, FileProcess.WATERMARK);
    }

    @Transactional
    public FangDescr updateDesc(FangDescr fangDescr) {
        FangDescr result = mgtFangService.updateDesc(fangDescr);

        Operator operator = mgtContext.getOperator();
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_M)
                .setTargetId(fangDescr.getFangId())
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】修改了房源编号为【" + fangDescr.getFangId() + "】的描述为：【" + fangDescr.toString() + "】")
        );
        return result;
    }

    public FangDescr findDescr(Long fangId) {
        return mgtFangService.findDescr(fangId);
    }

    @Transactional
    public Fang changeFangBase(Fang fang) {
        Fang needUpdate = mgtFangService.getFangBase(fang.getId());

        fang.setBizType(needUpdate.getBizType());
        fillFang(fang);

        Fang result = mgtFangService.updateFangBase(fang);

        Operator operator = mgtContext.getOperator();
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_M)
                .setTargetId(fang.getId())
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】修改了房源编号为【" + fang.getId() + "】的基本信息为：【" + fang.toString() + "】")
        );
        return result;
    }

    @Transactional
    public FangExt changeFangExt(FangExt fangExt) {
        FangExt result = mgtFangService.updateFangExt(fangExt);

        Operator operator = mgtContext.getOperator();
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_M)
                .setTargetId(fangExt.getFangId())
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】修改了房源编号为【" + fangExt.getFangId() + "】的扩展信息为：【" + fangExt.toString() + "】")
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
        Operator operator = mgtContext.getOperator();

        //todo: permission check
        //员工匹配：employeeId
        //公司匹配：companyId，员工调动
        //部门匹配：departmentId，员工离职
        List<FangInfoOwner> infoOwners = mgtFangService.getInfoOwners(fileDescription.getOwnerId());
        if (infoOwners.stream().anyMatch(t -> Objects.equals(t.getCompanyId(), operator.getCompanyId()))) {
            return fileService.delete(fileId);
        } else {
            throw new EstateException(ExCode.PERMISSION_ERROR);
        }
    }

    public Boolean setFirstImage(Long fileId) {
        FileDescription fileDescription = fileService.findOne(fileId);
        if (fileDescription == null || fileDescription.getDeleted()) {
            throw new EstateException(ExCode.FILE_NOT_EXIST);
        }
        if (fileDescription.getOwnerType() != DomainType.FANG) {
            throw new EstateException("文件归属类型不正确");
        }
        Operator operator = mgtContext.getOperator();

        //todo: permission check
        List<FangInfoOwner> infoOwners = mgtFangService.getInfoOwners(fileDescription.getOwnerId());
        if (infoOwners.stream().anyMatch(t -> Objects.equals(t.getCompanyId(), operator.getCompanyId()))) {
            return fileService.setFirst(fileId);
        } else {
            throw new EstateException(ExCode.PERMISSION_ERROR);
        }
    }
}
