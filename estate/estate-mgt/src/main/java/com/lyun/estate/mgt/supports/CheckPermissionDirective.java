package com.lyun.estate.mgt.supports;

import com.lyun.estate.biz.permission.def.Permission;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.permission.service.PermissionCheckService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by MingXing on 2016/5/9.
 */
public class CheckPermissionDirective implements TemplateDirectiveModel {

    @Autowired
    private PermissionCheckService permissionCheckerService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        SimpleScalar valueScalar = (SimpleScalar) params.get("value");
        if (valueScalar == null || StringUtils.isEmpty(valueScalar.getAsString())) {
            throw new EstateException(ExCode.PERMISSION_EMPTY);
        }

        Set<String> names = StringUtils.commaDelimitedListToSet(valueScalar.getAsString());

        boolean flag = names.stream().anyMatch(t -> {
                    Permission p = Permission.valueOf(t);
                    if (p.getCategory() == PermissionDefine.Category.PAGE) {
                        return permissionCheckerService.verifyPage(p);
                    } else if (p.getCategory() == PermissionDefine.Category.COMPANY_TYPE) {
                        return permissionCheckerService.verifyCompanyType(p);
                    } else {
                        return false;
                    }
                }
        );

        if (flag) {
            body.render(env.getOut());
        }
    }
}
