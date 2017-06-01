package com.lyun.estate.biz.permission.service;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.company.def.CompanyDefine;
import com.lyun.estate.biz.permission.def.GrantScope;
import com.lyun.estate.biz.permission.def.PermissionDefine;
import com.lyun.estate.biz.permission.entity.Grant;
import com.lyun.estate.biz.position.def.PositionType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lyun.estate.biz.permission.def.Permission.*;

/**
 * Created by Jeffrey on 2017-05-15.
 */
public class PositionPermissionTemplate {

    public static Map<PermissionDefine.Category, List<Grant>> defaultPermissions(CompanyDefine.Type companyType,
                                                                                 PositionType positionType) {
        Map<PermissionDefine.Category, List<Grant>> categoryGrantsMap = new HashMap<>();
        switch (positionType) {
            case REGION_M:
                categoryGrantsMap.put(PermissionDefine.Category.PAGE, Lists.newArrayList(
                        new Grant().setPermission(P_FANG),
                        new Grant().setPermission(P_FANG_LIST),
                        new Grant().setPermission(P_FANG_NEW),
                        new Grant().setPermission(P_FANG_FOLLOW),
                        new Grant().setPermission(P_FANG_CHECK),
                        new Grant().setPermission(P_SHOWING),
                        new Grant().setPermission(P_SHOWING_LIST),
                        new Grant().setPermission(P_CONTRACT),
                        new Grant().setPermission(P_CONTRACT_LIST),
                        new Grant().setPermission(P_ORG),
                        new Grant().setPermission(P_ORG_DEPT),
                        new Grant().setPermission(P_ORG_POSITION),
                        new Grant().setPermission(P_ORG_EMPLOYEE),
                        new Grant().setPermission(P_CONFIG),
                        new Grant().setPermission(P_CONFIG_AUDIT),
                        new Grant().setPermission(P_CONFIG_HOUSE_DICT),
                        new Grant().setPermission(P_CONFIG_PAGE),
                        new Grant().setPermission(P_CONFIG_PERMISSION)));

                categoryGrantsMap.put(PermissionDefine.Category.FANG, Lists.newArrayList(
                        new Grant().setPermission(CREATE_FANG),
                        new Grant().setPermission(LIST_FANG_SELL),
                        new Grant().setPermission(LIST_FANG_RENT),
                        new Grant().setPermission(NOT_FOLLOW_SELL),
                        new Grant().setPermission(NOT_FOLLOW_RENT),
                        new Grant().setPermission(DEL_FANG_IMG_SHI_JING).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(DEL_FANG_IMG_HU_XING).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(DEL_FANG_IMG_CERTIF).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(DEL_FANG_IMG_ATTORNEY).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(DEL_FANG_IMG_ID_CARD).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(DEL_FANG_CONTACT).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(FANG_PUBLISH).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(FANG_PAUSE).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(FANG_UN_PUBLISH).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(FANG_RE_PUBLISH).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(FANG_APPLY_PUBLIC).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(MODIFY_FANG_INFO).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(UPDATE_FANG_BASE).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(UPDATE_FANG_EXT).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(VIEW_FANG_CONTACT).setScope(GrantScope.COMPANY),
                        new Grant().setPermission(MODIFY_FANG_CONTACT).setScope(GrantScope.COMPANY)
                ));

                categoryGrantsMap.put(PermissionDefine.Category.XIAO_QU, Lists.newArrayList(
                        new Grant().setPermission(CREATE_BUILDING),
                        new Grant().setPermission(MODIFY_BUILDING),
                        new Grant().setPermission(DEL_BUILDING)
                ));

                categoryGrantsMap.put(PermissionDefine.Category.ORGANIZATION, Lists.newArrayList(
                        new Grant().setPermission(ORG_MANAGEMENT),
                        new Grant().setPermission(UNBIND_DEVICE)
                ));

                categoryGrantsMap.put(PermissionDefine.Category.COMPANY, Lists.newArrayList(
                        new Grant().setPermission(PERMISSION_MANAGEMENT),
                        new Grant().setPermission(VIEW_AUDIT_LOG)
                ));
                break;

            case DEPT_M:
                categoryGrantsMap.put(PermissionDefine.Category.PAGE, Lists.newArrayList(
                        new Grant().setPermission(P_FANG),
                        new Grant().setPermission(P_FANG_LIST),
                        new Grant().setPermission(P_FANG_NEW),
                        new Grant().setPermission(P_FANG_FOLLOW),
                        new Grant().setPermission(P_FANG_CHECK),
                        new Grant().setPermission(P_SHOWING),
                        new Grant().setPermission(P_SHOWING_LIST),
                        new Grant().setPermission(P_CONTRACT),
                        new Grant().setPermission(P_CONTRACT_LIST),
                        new Grant().setPermission(P_CONFIG),
                        new Grant().setPermission(P_CONFIG_AUDIT),
                        new Grant().setPermission(P_CONFIG_HOUSE_DICT))
                );

                categoryGrantsMap.put(PermissionDefine.Category.FANG, Lists.newArrayList(
                        new Grant().setPermission(CREATE_FANG),
                        new Grant().setPermission(LIST_FANG_SELL),
                        new Grant().setPermission(LIST_FANG_RENT),
                        new Grant().setPermission(NOT_FOLLOW_SELL),
                        new Grant().setPermission(NOT_FOLLOW_RENT),
                        new Grant().setPermission(DEL_FANG_IMG_SHI_JING).setScope(GrantScope.DEPT),
                        new Grant().setPermission(DEL_FANG_IMG_HU_XING).setScope(GrantScope.DEPT),
                        new Grant().setPermission(DEL_FANG_IMG_CERTIF).setScope(GrantScope.DEPT),
                        new Grant().setPermission(DEL_FANG_IMG_ATTORNEY).setScope(GrantScope.DEPT),
                        new Grant().setPermission(DEL_FANG_IMG_ID_CARD).setScope(GrantScope.DEPT),
                        new Grant().setPermission(DEL_FANG_CONTACT).setScope(GrantScope.DEPT),
                        new Grant().setPermission(FANG_PUBLISH).setScope(GrantScope.DEPT),
                        new Grant().setPermission(FANG_PAUSE).setScope(GrantScope.DEPT),
                        new Grant().setPermission(FANG_UN_PUBLISH).setScope(GrantScope.DEPT),
                        new Grant().setPermission(FANG_RE_PUBLISH).setScope(GrantScope.DEPT),
                        new Grant().setPermission(FANG_APPLY_PUBLIC).setScope(GrantScope.DEPT),
                        new Grant().setPermission(MODIFY_FANG_INFO).setScope(GrantScope.DEPT),
                        new Grant().setPermission(UPDATE_FANG_BASE).setScope(GrantScope.DEPT),
                        new Grant().setPermission(UPDATE_FANG_EXT).setScope(GrantScope.DEPT),
                        new Grant().setPermission(VIEW_FANG_CONTACT).setScope(GrantScope.DEPT),
                        new Grant().setPermission(MODIFY_FANG_CONTACT).setScope(GrantScope.DEPT)
                ));
                categoryGrantsMap.put(PermissionDefine.Category.XIAO_QU, Lists.newArrayList(
                        new Grant().setPermission(CREATE_BUILDING),
                        new Grant().setPermission(MODIFY_BUILDING),
                        new Grant().setPermission(DEL_BUILDING)
                ));

                categoryGrantsMap.put(PermissionDefine.Category.COMPANY, Lists.newArrayList(
                        new Grant().setPermission(VIEW_AUDIT_LOG)
                ));
                break;

            case BUSINESS:
                categoryGrantsMap.put(PermissionDefine.Category.PAGE, Lists.newArrayList(
                        new Grant().setPermission(P_FANG),
                        new Grant().setPermission(P_FANG_LIST),
                        new Grant().setPermission(P_FANG_NEW),
                        new Grant().setPermission(P_FANG_FOLLOW),
                        new Grant().setPermission(P_FANG_CHECK),
                        new Grant().setPermission(P_SHOWING),
                        new Grant().setPermission(P_SHOWING_LIST),
                        new Grant().setPermission(P_CONTRACT),
                        new Grant().setPermission(P_CONTRACT_LIST)
                ));

                categoryGrantsMap.put(PermissionDefine.Category.FANG, Lists.newArrayList(
                        new Grant().setPermission(CREATE_FANG),
                        new Grant().setPermission(LIST_FANG_SELL),
                        new Grant().setPermission(LIST_FANG_RENT),
                        new Grant().setPermission(DEL_FANG_IMG_SHI_JING).setScope(GrantScope.SELF),
                        new Grant().setPermission(DEL_FANG_IMG_HU_XING).setScope(GrantScope.SELF),
                        new Grant().setPermission(DEL_FANG_IMG_CERTIF).setScope(GrantScope.SELF),
                        new Grant().setPermission(DEL_FANG_IMG_ATTORNEY).setScope(GrantScope.SELF),
                        new Grant().setPermission(DEL_FANG_IMG_ID_CARD).setScope(GrantScope.SELF),
                        new Grant().setPermission(DEL_FANG_CONTACT).setScope(GrantScope.SELF),
                        new Grant().setPermission(FANG_PUBLISH).setScope(GrantScope.SELF),
                        new Grant().setPermission(FANG_PAUSE).setScope(GrantScope.SELF),
                        new Grant().setPermission(FANG_UN_PUBLISH).setScope(GrantScope.SELF),
                        new Grant().setPermission(FANG_RE_PUBLISH).setScope(GrantScope.SELF),
                        new Grant().setPermission(FANG_APPLY_PUBLIC).setScope(GrantScope.SELF),
                        new Grant().setPermission(MODIFY_FANG_INFO).setScope(GrantScope.SELF),
                        new Grant().setPermission(UPDATE_FANG_BASE).setScope(GrantScope.SELF),
                        new Grant().setPermission(UPDATE_FANG_EXT).setScope(GrantScope.SELF),
                        new Grant().setPermission(VIEW_FANG_CONTACT).setScope(GrantScope.SELF),
                        new Grant().setPermission(MODIFY_FANG_CONTACT).setScope(GrantScope.SELF)
                ));
                break;
            default:
                break;
        }
        return categoryGrantsMap;
    }
}
