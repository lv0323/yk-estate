package com.lyun.estate.mgt.employee;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.employee.def.WorkingStatus;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.support.def.Gender;
import com.lyun.estate.mgt.auth.def.SaltSugar;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeRest {

    private final EmployeeMgtService service;

    public EmployeeRest(EmployeeMgtService employeeMgtService) {
        this.service = employeeMgtService;
    }

    @PostMapping("create")
    public Object create(@RequestParam Long departmentId,
                         @RequestParam Long positionId,
                         @RequestParam String mobile,
                         @RequestParam(required = false) String openContact,
                         @RequestParam String name,
                         @RequestParam Gender gender,
                         @RequestParam(required = false) String idcardNumber,
                         @RequestParam(required = false) String wechat,
                         @RequestParam WorkingStatus status,
                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date entryDate) {
        Employee employee = new Employee().setDepartmentId(departmentId)
                .setPositionId(positionId)
                .setAgent(true)
                .setMobile(mobile)
                .setOpenContact(openContact)
                .setName(name)
                .setGender(gender)
                .setIdcardNumber(idcardNumber)
                .setWechat(wechat)
                .setStatus(status)
                .setEntryDate(entryDate);
        return service.create(employee);
    }

    @GetMapping("query")
    public PageList<Employee> query(@RequestParam(required = false) Long companyId,
                                    @RequestParam(required = false) Long departmentId,
                                    @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return service.listByCompanyIdDepartmentId(companyId, departmentId, pageBounds);
    }

    @GetMapping("query-all")
    public List<Employee> queryAll(
            @RequestParam(required = false) Long departmentId) {
        return service.listByCompanyIdDepartmentId(null, departmentId, null);
    }

    @PostMapping("edit")
    public Object edit(@RequestParam Long id,
                       @RequestParam Long departmentId,
                       @RequestParam Long positionId,
                       @RequestParam String openContact,
                       @RequestParam String name,
                       @RequestParam Gender gender,
                       @RequestParam(required = false) String idcardNumber,
                       @RequestParam(required = false) String wechat,
                       @RequestParam WorkingStatus status,
                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date entryDate) {
        Employee employee = new Employee().setId(id)
                .setDepartmentId(departmentId)
                .setPositionId(positionId)
                .setAgent(true)
                .setOpenContact(openContact)
                .setName(name)
                .setGender(gender)
                .setIdcardNumber(idcardNumber)
                .setWechat(wechat)
                .setStatus(status)
                .setEntryDate(entryDate);

        return service.update(employee);
    }


    @GetMapping("quit")
    public Object quit(@RequestParam Long id) {
        return new RestResponse().add("ret", service.quit(id)).get();
    }

    @GetMapping("avatar")
    public Object avatar() {
        return new RestResponse().add("url", service.getAvatar()).get();
    }

    @PostMapping("avatar")
    public FileDescription avatar(@RequestParam MultipartFile avatar) throws IOException {
        try (InputStream avatarIS = avatar.getInputStream()) {
            return service.createAvatar(avatarIS,
                    avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')));
        }
    }

    @PostMapping("change-password")
    public Object changePassword(@RequestParam String sugaredPassword, @RequestParam String saltedNewPassword) {
        return new RestResponse().add("ret", service.changePassword(sugaredPassword, saltedNewPassword)).get();
    }

    @GetMapping("change-password-salt-sugar")
    public SaltSugar changePasswordSaltSugar() {
        return service.changePasswordSaltSugar();
    }


    @GetMapping("self")
    public Employee self() {
        return service.self();
    }

    @PostMapping("self-edit")
    public Employee selfEdit(@RequestParam(required = false) String openContact,
                             @RequestParam(required = false) String weChat) {
        return service.selfEdit(openContact, weChat);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("unbind")
    public CommonResp unbindDevice(Long id) {
        return service.unbindDevice(id) ? CommonResp.succeed() : CommonResp.failed();
    }


}
