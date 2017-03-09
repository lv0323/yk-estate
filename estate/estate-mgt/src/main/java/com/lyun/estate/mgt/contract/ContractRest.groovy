package com.lyun.estate.mgt.contract

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractFilter
import com.lyun.estate.biz.customer.def.CustomerDefine
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
                            @RequestParam String customerName,
                            @RequestParam CustomerDefine.Source customerSource,
                            @RequestParam String customerMobile) {
        return contractMgtService.create(
                fangId,
                Optional.ofNullable(contractType).orElse(ContractDefine.Type.DEAL),
                customerName,
                customerSource,
                customerMobile)
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
                .setDepartmentId(departmentId)
                .setChildren(children)
                .setEmployeeId(employeeId)

        return contractMgtService.list(filter, pageBounds)
    }
}
