package com.lyun.estate.biz.showing.service

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.google.common.collect.Lists
import com.lyun.estate.biz.customer.service.CustomerService
import com.lyun.estate.biz.department.entity.Department
import com.lyun.estate.biz.department.service.DepartmentService
import com.lyun.estate.biz.employee.service.EmployeeService
import com.lyun.estate.biz.showing.def.ShowingDefine
import com.lyun.estate.biz.showing.entity.Showing
import com.lyun.estate.biz.showing.entity.ShowingDTO
import com.lyun.estate.biz.showing.entity.ShowingFilter
import com.lyun.estate.biz.showing.entity.ShowingSelector
import com.lyun.estate.biz.showing.repo.ShowingRepo
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.supports.exceptions.ExceptionUtil
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import static java.util.Objects.nonNull

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Service
class ShowingService {

    @Autowired
    ShowingRepo showingRepo

    @Autowired
    DepartmentService departmentService

    @Autowired
    CustomerService customerService

    @Autowired
    MgtFangService mgtFangService

    @Autowired
    EmployeeService employeeService

    Showing create(Showing showing) {
        ExceptionUtil.checkNotNull("带看信息", showing)
        showing.setProcess(ShowingDefine.Process.CREATED)

        if (showingRepo.save(showing) > 0) {
            return showingRepo.findOne(showing.getId())
        }
        throw new EstateException(ExCode.CREATE_FAIL, "带看记录", showing.toString())
    }

    Showing close(Long showingId, ShowingDefine.Process process) {
        ExceptionUtil.checkNotNull("带看编号", showingId)
        ExceptionUtil.checkIllegal(process != null && process.isEnd(), "状态", process)

        if (showingRepo.close(showingId, process) > 0) {
            return showingRepo.findOne(showingId)
        } else {
            def showing = showingRepo.findOne(showingId)
            if (nonNull(showing) && showing.getProcess().isEnd()) {
                return showing
            }
        }
        throw new EstateException(
                ExCode.UPDATE_FAIL,
                "带看",
                new Showing().setId(showingId).setProcess(process).toString())
    }

    PageList<ShowingDTO> list(ShowingFilter filter, PageBounds pageBounds) {
        ShowingSelector selector = new ShowingSelector()
        BeanUtils.copyProperties(filter, selector)

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

        PageList<ShowingDTO> result = showingRepo.list(selector, pageBounds)
        result.forEach({
            it.setAvatarURI(employeeService.getAvatarURI(it.getEmployeeId()))
            it.setCustomerTiny(customerService.getTiny(it.getCustomerId()))
            it.setFangTiny(mgtFangService.getFangTiny(it.getFangId()))
        })
        return result
    }

    int countSucceedShowing(Long fangId, Long employeeId) {
        ExceptionUtil.checkNotNull("房源编号", fangId)
        ExceptionUtil.checkNotNull("员工编号", employeeId)

        showingRepo.countSucceedShowing(fangId, employeeId)
    }
}
