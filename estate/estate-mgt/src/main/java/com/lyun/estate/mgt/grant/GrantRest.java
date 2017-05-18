package com.lyun.estate.mgt.grant;

import com.lyun.estate.biz.permission.def.GrantScope;
import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.grant.service.GrantMgtService;
import com.lyun.estate.mgt.supports.CommonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jeffrey on 2017-04-24.
 */
@RestController
@RequestMapping("api/grants")
public class GrantRest {

    @Autowired
    private GrantMgtService grantMgtService;

    @GetMapping()
    List<Grant> getGrantsByCategory(@RequestParam Long targetId,
                                    @RequestParam DomainType targetType,
                                    @RequestParam PermissionDefine.Category category) {

        return grantMgtService.getGrantsByCategory(targetId, targetType, category);
    }

    @PostMapping("/regrant")
    CommonResp regrant(@RequestParam Long targetId,
                       @RequestParam DomainType targetType,
                       @RequestParam PermissionDefine.Category category,
                       @RequestParam(required = false) List<String> grants) {
        List<Grant> grantList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(grants)) {
            grants.forEach(
                    t -> {
                        Grant grant = convertToGrant(t);
                        if (grant != null) {
                            grantList.add(grant);
                        }
                    }
            );
        }
        return grantMgtService.regrant(targetId,
                targetType,
                category,
                grantList) ? CommonResp.succeed() : CommonResp.failed();
    }

    @PostMapping("/regrant-employee-by-position")
    CommonResp regrantEmployeeByPosition(@RequestParam Long employeeId,
                                         @RequestParam Long positionId) {
        return grantMgtService.regrantEmployeeByPosition(employeeId,
                positionId) ? CommonResp.succeed() : CommonResp.failed();
    }

    @PostMapping("/regrant-by-position")
    CommonResp regrantByPosition(@RequestParam Long positionId) {
        return grantMgtService.regrantByPosition(positionId) ? CommonResp.succeed() : CommonResp.failed();
    }


    private Grant convertToGrant(String t) {
        String[] split = t.split("\\.", 2);
        Permission permission = Permission.valueOf(split[0]);
        if (isBoolean(split[1])) {
            if (isTrue(split[1])) {
                return new Grant().setPermission(permission);
            } else {
                return null;
            }
        } else if (isScope(split[1])) {
            return new Grant().setPermission(permission).setScope(GrantScope.valueOf(split[1]));
        } else {
            return new Grant().setPermission(permission).setLimits(Integer.valueOf(split[1]));
        }
    }

    private boolean isTrue(String s) {
        return "true".equalsIgnoreCase(s);
    }

    private boolean isScope(String s) {
        return Arrays.stream(GrantScope.values()).anyMatch(t -> t.name().equals(s));
    }

    private boolean isBoolean(String s) {
        return "true".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
    }

}
