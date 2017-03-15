package com.lyun.estate.mgt.contract

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractFilter
import com.lyun.estate.biz.fang.def.HouseType
import com.lyun.estate.biz.fang.def.PriceUnit
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.biz.support.def.IdentitySource
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver
import com.lyun.estate.mgt.contract.service.ContractMgtService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.*

import java.time.LocalTime

/**
 * Created by Jeffrey on 2017-03-09.
 */
@RestController
@RequestMapping("api/contract")
class ContractRest {

    @Autowired
    ContractMgtService contractMgtService

    @PostMapping("create")
    Contract createContract(@RequestParam Long fangId,
                            @RequestParam(required = false) ContractDefine.Type contractType,
                            @RequestParam HouseType houseType,
                            @RequestParam(required = false) String certifAddress,
                            @RequestParam(required = false) String certifNo,
                            @RequestParam BigDecimal estateArea,
                            @RequestParam BigDecimal price,
                            @RequestParam PriceUnit priceUnit,
                            @RequestParam BizType bizType,
                            @RequestParam String assignorName,
                            @RequestParam String assignorMobile,
                            @RequestParam IdentitySource assignorIdSource,
                            @RequestParam String assignorIdNo,
                            @RequestParam String assigneeName,
                            @RequestParam String assigneeMobile,
                            @RequestParam IdentitySource assigneeIdSource,
                            @RequestParam String assigneeIdNo,
                            @RequestParam(required = false) String note
    ) {
        return contractMgtService.create(
                new Contract()
                        .setFangId(fangId)
                        .setType(Optional.ofNullable(contractType).orElse(ContractDefine.Type.DEAL))
                        .setHouseType(houseType)
                        .setCertifAddress(certifAddress)
                        .setCertifNo(certifNo)
                        .setEstateArea(estateArea)
                        .setPrice(price)
                        .setPriceUnit(priceUnit)
                        .setBizType(bizType)
                        .setAssignorName(assignorName)
                        .setAssignorMobile(assignorMobile)
                        .setAssignorIdSource(assignorIdSource)
                        .setAssignorIdNo(assignorIdNo)
                        .setAssigneeName(assigneeName)
                        .setAssigneeMobile(assigneeMobile)
                        .setAssigneeIdSource(assigneeIdSource)
                        .setAssigneeIdNo(assigneeIdNo)
                        .setNote(note)
        )
    }

    @PostMapping("close")
    Contract closeShowing(@RequestParam Long contractId,
                          @RequestParam ContractDefine.Process process) {

        return contractMgtService.close(contractId, process)
    }

    @GetMapping("list")
    PageList<ContractDTO> listContract(
            @RequestParam(required = false) Long fangId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date minCreateDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date maxCreateDate,
            @RequestParam(required = false) ContractDefine.Process process,
            @RequestParam(required = false) BizType bizType,
            @RequestParam(required = false) HouseType houseType,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) Boolean children,
            @RequestParam(required = false) Long employeeId,
            @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        ContractFilter filter = new ContractFilter()
                .setFangId(fangId)
                .setMinCreateTime(minCreateDate)
                .setMaxCreateTime(Optional.ofNullable(maxCreateDate)
                .map({ Date.from(it.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())) })
                .orElse(null))
                .setProcess(process)
                .setBizType(bizType)
                .setHouseType(houseType)
                .setDepartmentId(departmentId)
                .setChildren(children)
                .setEmployeeId(employeeId)

        return contractMgtService.list(filter, pageBounds)
    }

    @GetMapping("/{id}")
    ContractDTO findOne(@PathVariable Long id) {
        return contractMgtService.findOne(id)
    }
}
