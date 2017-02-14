package com.lyun.estate.mgt.department;

import com.lyun.estate.biz.department.entity.Department;
import com.lyun.estate.biz.department.service.DepartmentService;
import com.lyun.estate.mgt.employee.LoginEmployee;
import com.lyun.estate.mgt.supports.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/department")
public class DepartmentRest {

    private final DepartmentService service;

    public DepartmentRest(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Object add(Department department, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(department).setCompanyId(employee.getCompanyId());
        return service.create(department);
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Long id, @SessionAttribute LoginEmployee employee) {
        return new RestResponse().add("ret", service.deleteById(id)).get();
    }

    @PostMapping("edit")
    public Object edit(Department department, @SessionAttribute LoginEmployee employee) {
        Objects.requireNonNull(department).setCompanyId(employee.getCompanyId());
        return service.updateInfo(department);
    }

    @GetMapping("query")
    public List<Department> query(@SessionAttribute LoginEmployee employee) {
        return service.selectByCompanyId(employee.getCompanyId());
    }

    @GetMapping("changeParent")
    public Department changParent(@RequestParam Long departmentId, @RequestParam Long parentId) {
        return service.changeParent(departmentId, parentId);
    }

    @GetMapping("query-sorted")
    public Object querySorted(@SessionAttribute LoginEmployee employee) {
        List<Department> departmentList = service.selectByCompanyId(employee.getCompanyId());
        List<Map<String, Object>> sortedList = new LinkedList<>();
        Map<Long, Map<String, Object>> departmentMap = new HashMap<>();
        departmentMap.put(null, null);
        Map<Long, Map<String, Object>> nextDepartmentMap = new HashMap<>();
        int level = 0;
        while (!departmentMap.isEmpty()) {
            Iterator<Department> iterator = departmentList.iterator();
            while (iterator.hasNext()) {
                Department depart = iterator.next();
                if (!departmentMap.containsKey(depart.getParentId()))
                    continue;

                Map<String, Object> item = new HashMap<>();
                item.put("level", level);
                item.put("department", depart);
                item.put("deletable", true);
                for (Department d : departmentList) {
                    if (Objects.equals(d.getParentId(), depart.getId())) {
                        item.put("deletable", false);
                        break;
                    }
                }

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

    @GetMapping("/{departmentId}")
    Department findOne(@PathVariable Long departmentId) {
        return service.selectById(departmentId);
    }
}
