package com.lyun.estate.biz.permission.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-21.
 */
public class PermissionDefine {

    public enum Category implements LabelEnum {
        FANG("房源"),
        CUSTOMER("客源"),
        XIAO_QU("小区"),
        ORGANIZATION("组织架构"),
        COMPANY("公司"),
        PAGE("页面模块"),
        COMPANY_TYPE("公司性质"),
        FRANCHISEE("加盟商"),
        OPERATION("运营管理"),;

        private final String label;

        Category(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

}
