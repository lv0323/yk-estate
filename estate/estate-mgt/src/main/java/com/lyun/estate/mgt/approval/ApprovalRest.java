package com.lyun.estate.mgt.approval;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.approval.def.ApprovalDefine;
import com.lyun.estate.biz.approval.domain.ApprovalDTO;
import com.lyun.estate.biz.approval.entity.Approval;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.approval.service.ApprovalMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-06-14.
 */
@RestController
@RequestMapping("api/approval")
public class ApprovalRest {

    @Autowired
    private ApprovalMgtService approvalMgtService;

    @PostMapping("create")
    public Approval create(@RequestParam ApprovalDefine.Type type,
                           @RequestParam String data) {
        return approvalMgtService.create(type, data);
    }

    @PostMapping("approve")
    public Approval approve(@RequestParam Long id,
                            @RequestParam ApprovalDefine.Status status,
                            @RequestParam(required = false) String comment) {
        return approvalMgtService.approve(id, status, comment);
    }

    @GetMapping("list")
    public PageList<ApprovalDTO> list(@RequestParam ApprovalDefine.Type type,
                                      @RequestParam(required = false) ApprovalDefine.Status status,
                                      @RequestParam(required = false) Long applyCompanyId,
                                      @RequestParam(required = false) Long applyDeptId,
                                      @RequestParam(required = false) Long applyId,
                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                      @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return approvalMgtService.list(type,
                status,
                applyCompanyId,
                applyDeptId,
                applyId,
                startTime,
                Optional.ofNullable(endTime)
                        .map(t -> Date.from(t.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null),
                pageBounds);
    }

    @GetMapping("report-export")
    public void reportExportLeaving(@RequestParam ApprovalDefine.Type type,
                                    @RequestParam(required = false) ApprovalDefine.Status status,
                                    @RequestParam(required = false) Long applyCompanyId,
                                    @RequestParam(required = false) Long applyDeptId,
                                    @RequestParam(required = false) Long applyId,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                    HttpServletResponse httpResponse) {
        approvalMgtService.reportExport(type,
                status,
                applyCompanyId,
                applyDeptId,
                applyId,
                startTime,
                Optional.ofNullable(endTime)
                        .map(t -> Date.from(t.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null),
                httpResponse);
    }

    @GetMapping("my")
    public PageList<ApprovalDTO> my(@RequestParam ApprovalDefine.Type type,
                                    @RequestParam(required = false) ApprovalDefine.Status status,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startTime,
                                    @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endTime,
                                    @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        return approvalMgtService.myApproval(type,
                status,
                startTime,
                Optional.ofNullable(endTime)
                        .map(t -> Date.from(t.toInstant().plusSeconds(LocalTime.MAX.toSecondOfDay())))
                        .orElse(null),
                pageBounds);
    }

}
