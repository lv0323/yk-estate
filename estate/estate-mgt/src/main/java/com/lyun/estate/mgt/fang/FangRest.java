package com.lyun.estate.mgt.fang;

import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.housedict.def.StructureType;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.core.supports.types.YN;
import com.lyun.estate.mgt.fang.service.FangMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-02-19.
 */
@RestController
@RequestMapping("fang")
public class FangRest {

    private FangMgtService fangMgtService;

    public FangRest(FangMgtService fangMgtService) {
        this.fangMgtService = fangMgtService;
    }

    @PostMapping("create")
    public Fang createFang(@RequestParam BizType bizType,
                           @RequestParam HouseType houseType,
                           @RequestParam HouseSubType houseSubType,
                           @RequestParam Long xiaoquId,
                           @RequestParam Long buildingId,
                           @RequestParam Long buildingUnitId,
                           @RequestParam String houseNo,
                           @RequestParam Integer floor,
                           @RequestParam Integer floorCounts,
                           @RequestParam StructureType structureType,
                           @RequestParam Integer builYear,
                           @RequestParam String address,
                           @RequestParam(required = false) String certifNo,
                           @RequestParam Integer sCounts,
                           @RequestParam Integer tCounts,
                           @RequestParam Integer cCounts,
                           @RequestParam Integer wCounts,
                           @RequestParam Integer ytCounts,
                           @RequestParam Orientation orientation,
                           @RequestParam BigDecimal estateArea,
                           @RequestParam(required = false) BigDecimal realArea,
                           @RequestParam BigDecimal publishPrice,
                           @RequestParam PriceUnit priceUnit,
                           @RequestParam BigDecimal unitPrice,
                           @RequestParam(required = false) BigDecimal transferPrice,
                           @RequestParam(required = false) BigDecimal bottomPrice,
                           @RequestParam(required = false) YN resident,
                           @RequestParam String ownerName,
                           @RequestParam List<String> mobiles,
                           @RequestParam YN isOnly,
                           @RequestParam Integer overYears,
                           @RequestParam HouseLevel level,
                           @RequestParam DelegateType delegateType,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateStart,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateEnd,
                           @RequestParam Showing showing,
                           @RequestParam HouseStatus status,
                           @RequestParam HouseSource source,
                           @RequestParam(required = false) CertifType certifType,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date purchaseDate,
                           @RequestParam(required = false) PropertyType propertyType,
                           @RequestParam Decorate decorate,
                           @RequestParam HeatingType heatingType,
                           @RequestParam YN hasElevator,
                           @RequestParam TaxesWilling taxesWilling,
                           @RequestParam CommissionWilling commissionWilling,
                           @RequestParam BigDecimal purchasePrice,
                           @RequestParam String note) {

        HouseLicence houseLicence = new HouseLicence().setType(bizType)
                .setBuildingId(buildingId)
                .setBuildingUnitId(buildingUnitId)
                .setHouseNo(houseNo);

        Fang fang = new Fang().setBizType(bizType).setHouseType(houseType).setHouseSubType(houseSubType)
                .setXiaoQuId(xiaoquId).setFloor(floor).setFloorCounts(floorCounts).setsCounts(sCounts)
                .settCounts(tCounts).setcCounts(cCounts).setwCounts(wCounts).setYtCounts(ytCounts)
                .setOrientation(orientation).setEstateArea(estateArea).setRealArea(realArea)
                .setPublishPrice(publishPrice).setPriceUnit(priceUnit).setUnitPrice(unitPrice)
                .setTransferPrice(transferPrice).setStructureType(structureType).setBuildYear(builYear)
                .setHeatingType(heatingType).setHasElevator(hasElevator)
                .setBottomPrice(bottomPrice).setResident(resident).setDecorate(decorate);

        FangExt fangExt = new FangExt().setCertifAdress(address).setCertifNo(certifNo).setIsOnly(isOnly)
                .setOverYears(overYears).setLevel(level).setDelegateType(delegateType).setDelegateStart(delegateStart)
                .setDelegateEnd(delegateEnd).setShowing(showing).setSource(source).setStatus(status)
                .setCertifType(certifType).setPurchaseDate(purchaseDate).setPropertyType(propertyType)
                .setTaxesWilling(taxesWilling).setCommissionWilling(commissionWilling).setPurchasePrice(purchasePrice)
                .setNote(note);

        List<FangContact> contacts = mobiles.stream().map(m ->
                new FangContact().setContactType(ContactType.MOBILE).setOwnerName(ownerName).setContactInfo(m))
                .collect(Collectors.toList());

        return fangMgtService.createFang(houseLicence, fang, fangExt, contacts);
    }

    @PostMapping("pre-check-licence")
    public CommonResp preCheck(@RequestParam Long xiaoquId,
                               @RequestParam BizType bizType,
                               @RequestParam Long buildingId,
                               @RequestParam Long buildingUnitId,
                               @RequestParam String houseNo) {
        return fangMgtService.preCheckLicence(xiaoquId,
                bizType,
                buildingId,
                buildingUnitId,
                houseNo) ? CommonResp.succeed() : CommonResp.failed();
    }

}
