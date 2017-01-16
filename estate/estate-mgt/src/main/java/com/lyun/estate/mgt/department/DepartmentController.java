package com.lyun.estate.mgt.department;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.mgt.employee.LoginEmployee;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Department department, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(department).setCompanyId(employee.getCompanyId());
        return service.create(department);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id, @SessionAttribute LoginEmployee employee) {
        Department department = service.selectById(id);
        if (department == null)
            return new RestResponse().add("ret", true).get();
        if (!Objects.equals(department.getCompanyId(), employee.getCompanyId()))
            return new RestResponse().add("ret", false).get();
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Department department, @SessionAttribute LoginEmployee employee) {
        return service.update(department);
    }

    @GetMapping("query")
    public Object query(@SessionAttribute LoginEmployee employee) {
        return service.selectByCompanyId(employee.getCompanyId());
    }

    @GetMapping("query-sorted")
    public Object querySorted(@SessionAttribute LoginEmployee employee) {
        List<Department> departmentList = service.selectByCompanyId(employee.getCompanyId());
        List<Map<String, Object>> sortedList = new ArrayList<>(departmentList.size());
        Map<Long, Map<String, Object>> departmentMap = new HashMap<>();
        departmentMap.put(null, null);
        Map<Long, Map<String, Object>> nextDepartmentMap = new HashMap<>();
        int level = 0;
        while (!departmentList.isEmpty()) {
            Iterator<Department> iterator = departmentList.iterator();
            while (iterator.hasNext()) {
                Department depart = iterator.next();
                if (!departmentMap.containsKey(depart.getParentId()))
                    continue;

                Map<String, Object> item = new HashMap<>();
                item.put("level", level);
                item.put("department", depart);

                Map<String, Object> parent = departmentMap.get(depart.getParentId());
                if (parent == null)
                    sortedList.add(item);
                else
                    sortedList.add(sortedList.indexOf(parent) + 1, item);

                iterator.remove();
                nextDepartmentMap.put(depart.getId(), item);
            }
            departmentMap = nextDepartmentMap;
            nextDepartmentMap = new HashMap<>();
            level++;
        }
        return sortedList;
    }
}
