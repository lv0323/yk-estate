package com.lyun.estate.mgt.employee;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.employee.entity.Employee;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.mgt.employee.service.EmployeeMgtService;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/employee")
public class EmployeeRest {

    private final EmployeeMgtService service;

    public EmployeeRest(EmployeeMgtService employeeMgtService) {
        this.service = employeeMgtService;
    }

    @PostMapping("create")
    public Object create(Employee entity) {
        return service.create(entity);
    }

    @GetMapping("query")
    public PageList<Employee> query(@RequestParam(required = false) Long departmentId,
                                    @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return service.listByCompanyIdDepartmentId(departmentId,
                pageBounds);
    }

    @PostMapping("edit")
    public Object edit(Employee entity) {
        return service.update(entity);
    }


    @GetMapping("quit")
    public Object quit(@RequestParam Long id) {
        return new RestResponse().add("ret", service.quit(id)).get();
    }

    @GetMapping("avatar")
    public Object avatar() {
        return new RestResponse().add("url", service.getAvatar());
    }

    @PostMapping("avatar")
    public FileDescription avatar(@RequestParam MultipartFile avatar) throws IOException {
        return service.createAvatar(avatar.getInputStream(),
                avatar.getOriginalFilename().substring(avatar.getOriginalFilename().lastIndexOf('.')));
    }
}
