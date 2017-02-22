package com.lyun.estate.mgt.fang.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.audit.def.AuditSubject;
import com.lyun.estate.biz.audit.entity.Audit;
import com.lyun.estate.biz.audit.service.AuditService;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.service.MgtFangService;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.houselicence.service.HouseLicenceService;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.service.MgtXiaoQuService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import com.lyun.estate.mgt.context.MgtContext;
import com.lyun.estate.mgt.context.Operator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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

    public FangMgtService(HouseLicenceService houseLicenceService,
                          MgtXiaoQuService mgtXiaoQuService, MgtFangService mgtFangService, MgtContext mgtContext,
                          AuditService auditService) {
        this.houseLicenceService = houseLicenceService;
        this.mgtXiaoQuService = mgtXiaoQuService;
        this.mgtFangService = mgtFangService;
        this.mgtContext = mgtContext;
        this.auditService = auditService;
    }

    @Transactional
    public Fang createFang(HouseLicence houseLicence, Fang fang, FangExt fangExt, List<FangContact> contacts) {

        ExceptionUtil.checkNotNull("字典编号", houseLicence);
        ExceptionUtil.checkNotNull("基本信息", fang);
        ExceptionUtil.checkNotNull("扩展信息", fangExt);
        ExceptionUtil.checkIllegal(!CollectionUtils.isEmpty(contacts), "联系方式", fangExt);

        buildFang(fang);

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
        contacts.forEach(mgtFangService::createFangContact);

        //fangInfoOwner
        Operator operator = mgtContext.getOperator();
        mgtFangService.createFangInfoOwner(new FangInfoOwner().setFangId(result.getId())
                .setCompanyId(operator.getCompanyId()).setDepartmentId(operator.getDepartmentId())
                .setEmployeeId(operator.getId()).setReason(InfoOwnerReason.CREATE));

        //audit
        auditService.save(new Audit()
                .setCompanyId(operator.getCompanyId())
                .setDepartmentId(operator.getDepartmentId())
                .setOperatorId(operator.getId())
                .setSubject(AuditSubject.FANG_A_R)
                .setTargetId(result.getId())
                .setDomainType(DomainType.FANG)
                .setContent("【" + operator.getDepartmentName() + "--" + operator
                        .getName() + "】创建了编号为【" + licence.getId() + "】的房源")
        );
        return result;
    }

    private void checkFangContact(List<FangContact> contacts) {
        contacts.forEach(c -> {
            boolean flag = true;
            switch (c.getContactType()) {
                case MOBILE:
                    flag = ValidateUtil.isMobile(c.getContactInfo());
                    break;
                case QQ:
                    flag = !Strings.isNullOrEmpty(c.getContactInfo());
                    break;
                case WECHAT:
                    flag = !Strings.isNullOrEmpty(c.getContactInfo());
                    break;
                case PHONE:
                    flag = !Strings.isNullOrEmpty(c.getContactInfo());
                    break;
                case EMAIL:
                    flag = ValidateUtil.isEmail(c.getContactInfo());
                    break;
            }
            ExceptionUtil.checkIllegal(flag, c.getContactType().getLabel(), c.getContactInfo());
        });
    }


    private void buildFang(Fang fang) {
        fang.setProcess(HouseProcess.DELEGATE);

        fang.setFloorType(calculateFloorType(fang.getFloor(), fang.getFloorCounts()));

        BigDecimal publishPrice = fang.getPublishPrice();
        if (fang.getBizType() == BizType.SELL && fang.getPriceUnit() == PriceUnit.WAN) {
            publishPrice = fang.getPublishPrice().multiply(new BigDecimal(10000));
        }

        fang.setUnitPrice(publishPrice.divide(fang.getEstateArea(), 2, RoundingMode.HALF_UP));
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
}
