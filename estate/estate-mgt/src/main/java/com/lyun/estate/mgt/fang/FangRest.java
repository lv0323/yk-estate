package com.lyun.estate.mgt.fang;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.fang.def.*;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.houselicence.entity.HouseLicence;
import com.lyun.estate.biz.spec.fang.mgt.def.TimeType;
import com.lyun.estate.biz.spec.fang.mgt.entity.FangFollowFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;
import com.lyun.estate.biz.support.def.BizType;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.core.supports.types.YN;
import com.lyun.estate.mgt.fang.service.FangMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
                           @RequestParam Long xiaoQuId,
                           @RequestParam Long buildingId,
                           @RequestParam Long buildingUnitId,
                           @RequestParam String houseNo,
                           @RequestParam Integer floor,
                           @RequestParam Integer floorCounts,
                           @RequestParam StructureType structureType,
                           @RequestParam Integer buildYear,
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
                           @RequestParam(required = false) BigDecimal unitPrice,
                           @RequestParam(required = false) BigDecimal transferPrice,
                           @RequestParam(required = false) BigDecimal bottomPrice,
                           @RequestParam(required = false) YN resident,
                           @RequestParam String ownerName,
                           @RequestParam List<String> mobiles,
                           @RequestParam YN isOnly,
                           @RequestParam Integer overYears,
                           @RequestParam HouseLevel level,
                           @RequestParam DelegateType delegateType,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateStart,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateEnd,
                           @RequestParam Showing showing,
                           @RequestParam HouseStatus status,
                           @RequestParam HouseSource source,
                           @RequestParam(required = false) CertifType certifType,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date purchaseDate,
                           @RequestParam(required = false) PropertyType propertyType,
                           @RequestParam Decorate decorate,
                           @RequestParam HeatingType heatingType,
                           @RequestParam YN hasElevator,
                           @RequestParam TaxesWilling taxesWilling,
                           @RequestParam CommissionWilling commissionWilling,
                           @RequestParam(required = false) BigDecimal purchasePrice,
                           @RequestParam(required = false) YN mortgage,
                           @RequestParam(required = false) String note) {

        HouseLicence houseLicence = new HouseLicence().setBizType(bizType)
                .setBuildingId(buildingId)
                .setBuildingUnitId(buildingUnitId)
                .setHouseNo(houseNo);

        Fang fang = new Fang().setBizType(bizType).setHouseType(houseType).setHouseSubType(houseSubType)
                .setXiaoQuId(xiaoQuId).setFloor(floor).setFloorCounts(floorCounts).setsCounts(sCounts)
                .settCounts(tCounts).setcCounts(cCounts).setwCounts(wCounts).setYtCounts(ytCounts)
                .setOrientation(orientation).setEstateArea(estateArea).setRealArea(realArea)
                .setPublishPrice(publishPrice).setPriceUnit(priceUnit).setUnitPrice(unitPrice)
                .setTransferPrice(transferPrice).setStructureType(structureType).setBuildYear(buildYear)
                .setHeatingType(heatingType).setHasElevator(hasElevator)
                .setBottomPrice(bottomPrice).setResident(resident).setDecorate(decorate);

        FangExt fangExt = new FangExt().setCertifAddress(address)
                .setCertifNo(certifNo)
                .setIsOnly(isOnly)
                .setOverYears(Optional.ofNullable(overYears).orElse(0))
                .setLevel(level)
                .setDelegateType(delegateType)
                .setDelegateStart(delegateStart)
                .setDelegateEnd(delegateEnd)
                .setShowing(showing)
                .setSource(source)
                .setStatus(status)
                .setCertifType(certifType)
                .setPurchaseDate(purchaseDate)
                .setPropertyType(propertyType)
                .setTaxesWilling(taxesWilling)
                .setCommissionWilling(commissionWilling)
                .setPurchasePrice(purchasePrice)
                .setMortgage(mortgage)
                .setNote(note);

        List<FangContact> contacts = mobiles.stream().filter(t -> !Strings.isNullOrEmpty(t)).map(m ->
                new FangContact().setContactType(ContactType.MOBILE).setOwnerName(ownerName).setContactInfo(m))
                .collect(Collectors.toList());

        return fangMgtService.createFang(houseLicence, fang, fangExt, contacts);
    }

    @PostMapping("pre-check-licence")
    public CommonResp preCheck(@RequestParam Long xiaoQuId,
                               @RequestParam BizType bizType,
                               @RequestParam Long buildingId,
                               @RequestParam Long buildingUnitId,
                               @RequestParam String houseNo) {
        return fangMgtService.preCheckLicence(xiaoQuId,
                bizType,
                buildingId,
                buildingUnitId,
                houseNo) ? CommonResp.succeed() : CommonResp.failed();
    }

    @GetMapping("list")
    public PageList<MgtFangSummary> getMgtFangSummary(@RequestParam Long cityId,
                                                      @RequestParam(required = false) BizType bizType,
                                                      @RequestParam(required = false) Long districtId,
                                                      @RequestParam(required = false) Long subDistrictId,
                                                      @RequestParam(required = false) HouseType houseType,
                                                      @RequestParam(required = false) HouseProcess process,
                                                      @RequestParam(required = false) Integer minArea,
                                                      @RequestParam(required = false) Integer maxArea,
                                                      @RequestParam(required = false) Integer minPrice,
                                                      @RequestParam(required = false) Integer maxPrice,
                                                      @RequestParam(required = false) Integer sCounts,
                                                      @RequestParam(required = false) List<HouseTag> hts,
                                                      @RequestParam(required = false) Long departmentId,
                                                      @RequestParam(required = false) Long employeeId,
                                                      @RequestParam(required = false) Boolean includeChildren,
                                                      @RequestParam(required = false) TimeType timeType,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                                      @RequestParam(required = false) DelegateType delegateType,
                                                      @RequestParam(required = false) Decorate decorate,
                                                      @RequestParam(required = false) PropertyType propertyType,
                                                      @RequestParam(required = false) CertifType certifType,
                                                      @RequestParam(required = false) YN resident,
                                                      @RequestParam MgtFangSummaryOrder order,
                                                      @RequestHeader("X-PAGING") PageBounds pageBounds) {
        MgtFangFilter filter = new MgtFangFilter().setCityId(cityId)
                .setBizType(bizType)
                .setDistrictId(districtId)
                .setSubDistrictId(subDistrictId)
                .setHouseType(houseType)
                .setProcess(process)
                .setMinArea(minArea)
                .setMaxArea(maxArea)
                .setMinPrice(minPrice)
                .setMaxPrice(maxPrice)
                .setsCounts(sCounts)
                .setHouseTags(hts)
                .setDecorate(decorate)
                .setDepartmentId(departmentId)
                .setEmployeeId(employeeId)
                .setIncludeChildren(includeChildren)
                .setTimeType(timeType)
                .setStartTime(startDate)
                .setEndTime(Optional.ofNullable(endDate)
                        .map(time -> Date.from(time.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null))
                .setDelegateType(delegateType)
                .setPropertyType(propertyType)
                .setCertifType(certifType)
                .setResident(resident);
        return fangMgtService.listSummary(filter, order, pageBounds);
    }

    @GetMapping("sub-types")
    public List<HouseSubType> subTypes(@RequestParam HouseType houseType) {
        return houseType.getSubTypes();
    }

    @GetMapping("summary")
    public MgtFangSummary getFangSummary(@RequestParam Long fangId) {
        return fangMgtService.getFangSummary(fangId);
    }

    @GetMapping("base")
    public Fang getFangBase(@RequestParam Long fangId) {
        return fangMgtService.getFangBase(fangId);
    }


    @GetMapping("ext")
    public FangExt getFangExt(@RequestParam Long fangId) {
        return fangMgtService.getFangExt(fangId);
    }

    @GetMapping("contact")
    public List<FangContact> getContacts(@RequestParam Long fangId) {
        return fangMgtService.getContacts(fangId);
    }

    @GetMapping("info-owner")
    public List<FangInfoOwner> getInfoOwners(@RequestParam Long fangId) {
        return fangMgtService.getInfoOwners(fangId);
    }

    @PostMapping("change-base")
    public Fang changeBase(@RequestParam Long fangId,
                           @RequestParam HouseSubType houseSubType,
                           @RequestParam Integer sCounts,
                           @RequestParam Integer tCounts,
                           @RequestParam Integer wCounts,
                           @RequestParam Integer cCounts,
                           @RequestParam Integer ytCounts,
                           @RequestParam Orientation orientation,
                           @RequestParam Decorate decorate,
                           @RequestParam BigDecimal estateArea,
                           @RequestParam BigDecimal realArea,
                           @RequestParam BigDecimal publishPrice,
                           @RequestParam PriceUnit priceUnit,
                           @RequestParam(required = false) BigDecimal transferPrice,
                           @RequestParam(required = false) BigDecimal bottomPrice,
                           @RequestParam(required = false) YN resident,
                           @RequestParam Integer floor,
                           @RequestParam Integer floorCounts,
                           @RequestParam StructureType structureType,
                           @RequestParam Integer buildYear,
                           @RequestParam HeatingType heatingType,
                           @RequestParam YN hasElevator) {
        Fang fang = new Fang().setId(fangId).setHouseSubType(houseSubType)
                .setsCounts(sCounts).settCounts(tCounts).setwCounts(wCounts)
                .setcCounts(cCounts).setYtCounts(ytCounts).setOrientation(orientation)
                .setDecorate(decorate).setEstateArea(estateArea).setRealArea(realArea)
                .setPublishPrice(publishPrice).setPriceUnit(priceUnit)
                .setTransferPrice(transferPrice).setBottomPrice(bottomPrice).setResident(resident)
                .setFloor(floor).setFloorCounts(floorCounts).setStructureType(structureType)
                .setBuildYear(buildYear).setHeatingType(heatingType).setHasElevator(hasElevator);
        return fangMgtService.changeFangBase(fang);
    }

    @PostMapping("change-ext")
    public FangExt changeExt(@RequestParam Long fangId,
                             @RequestParam HouseLevel level,
                             @RequestParam Showing showing,
                             @RequestParam DelegateType delegateType,
                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateStart,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date delegateEnd,
                             @RequestParam HouseStatus status,
                             @RequestParam HouseSource source,
                             @RequestParam String address,
                             @RequestParam(required = false) CertifType certifType,
                             @RequestParam(required = false) PropertyType propertyType,
                             @RequestParam(required = false) String certifNo,
                             @RequestParam TaxesWilling taxesWilling,
                             @RequestParam CommissionWilling commissionWilling,
                             @RequestParam(required = false) BigDecimal purchasePrice,
                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date purchaseDate,
                             @RequestParam YN isOnly,
                             @RequestParam Integer overYears,
                             @RequestParam(required = false) YN mortgage,
                             @RequestParam(required = false) String note) {
        FangExt fangExt = new FangExt().setFangId(fangId)
                .setLevel(level)
                .setShowing(showing)
                .setDelegateType(delegateType)
                .setDelegateStart(delegateStart)
                .setDelegateEnd(delegateEnd)
                .setStatus(status)
                .setSource(source)
                .setCertifType(certifType)
                .setCertifAddress(address)
                .setCertifNo(certifNo)
                .setPropertyType(propertyType)
                .setTaxesWilling(taxesWilling)
                .setCommissionWilling(commissionWilling)
                .setPurchasePrice(purchasePrice)
                .setPurchaseDate(purchaseDate)
                .setIsOnly(isOnly)
                .setOverYears(Optional.ofNullable(overYears).orElse(0))
                .setMortgage(mortgage)
                .setNote(note);

        return fangMgtService.changeFangExt(fangExt);
    }

    @PostMapping("follow")
    public FangFollow createFollow(@RequestParam Long fangId,
                                   @RequestParam FollowType followType,
                                   @RequestParam String content) {
        return fangMgtService.createFollow(fangId, followType, content);
    }

    @PostMapping("check")
    public FangCheck createCheck(@RequestParam Long fangId,
                                 @RequestParam String advantage,
                                 @RequestParam(required = false) String disAdvantage) {
        return fangMgtService.createCheck(fangId, advantage, disAdvantage);
    }

    @GetMapping("check")
    public PageList<FangCheck> getChecks(Long fangId,
                                         @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return fangMgtService.getChecks(fangId, pageBounds);
    }

    @PostMapping("image")
    public FileDescription createImage(@RequestParam Long fangId,
                                       @RequestParam CustomType customType,
                                       @RequestParam MultipartFile image) throws IOException {
        try (InputStream inputStream = image.getInputStream()) {
            return fangMgtService.createImage(fangId,
                    customType,
                    inputStream,
                    image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")));
        }
    }

    @GetMapping("image")
    public List<FileDescription> getImages(@RequestParam Long fangId,
                                           @RequestParam CustomType customType) {
        return fangMgtService.getImages(fangId, customType);
    }


    @PostMapping("descr")
    public FangDescr updateDesc(@RequestParam Long fangId,
                                @RequestParam String title,
                                @RequestParam String core,
                                @RequestParam(required = false) String huXing,
                                @RequestParam(required = false) String quanShu,
                                @RequestParam(required = false) String shuiFei,
                                @RequestParam(required = false) String xueQu,
                                @RequestParam(required = false) String zhuangXiu,
                                @RequestParam(required = false) String jiaoTong,
                                @RequestParam(required = false) String xiaoQu,
                                @RequestParam(required = false) String peiTao,
                                @RequestParam(required = false) String touZi) {
        FangDescr descr = new FangDescr().setFangId(fangId)
                .setTitle(title)
                .setCore(core)
                .setHuXing(huXing)
                .setQuanShu(quanShu)
                .setShuiFei(shuiFei)
                .setXueQu(xueQu)
                .setZhuangXiu(zhuangXiu)
                .setJiaoTong(jiaoTong)
                .setXiaoQu(xiaoQu)
                .setPeiTao(peiTao)
                .setTouZi(touZi);

        return fangMgtService.updateDesc(descr);
    }

    @GetMapping("descr")
    public FangDescr getDescr(@RequestParam Long fangId) {
        return fangMgtService.findDescr(fangId);
    }

    @GetMapping("list-follow")
    public PageList<FangFollowDTO> listFollow(@RequestParam(required = false) Long fangId,
                                              @RequestParam(required = false) FollowType followType,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date minFollowDate,
                                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date maxFollowDate,
                                              @RequestParam(required = false) Long departmentId,
                                              @RequestParam(required = false) Boolean children,
                                              @RequestParam(required = false) Long employeeId,
                                              @RequestParam(required = false) Long ioDepartmentId,
                                              @RequestParam(required = false) Boolean ioChildren,
                                              @RequestParam(required = false) Long ioEmployeeId,
                                              @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {

        FangFollowFilter filter = new FangFollowFilter()
                .setFangId(fangId)
                .setFollowType(followType)
                .setMinFollowTime(minFollowDate)
                .setMaxFollowTime(Optional.ofNullable(maxFollowDate)
                        .map(t -> Date.from(t.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null))
                .setDepartmentId(departmentId)
                .setChildren(children)
                .setEmployeeId(employeeId)
                .setIoDepartmentId(ioDepartmentId)
                .setIoChildren(ioChildren)
                .setIoEmployeeId(ioEmployeeId);

        return fangMgtService.listFollow(filter, pageBounds);
    }

    @GetMapping("/tiny")
    public MgtFangTiny getFangTinyByLicenceId(@RequestParam Long licenceId) {
        return fangMgtService.getFangTinyByLicenceId(licenceId);
    }

}
