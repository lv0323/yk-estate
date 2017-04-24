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
        COMPANY("公司"),;

        private final String label;

        Category(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    public enum Operation implements LabelEnum {
        CREATE("新增"),
        UPDATE("更新"),
        QUERY("查询"),
        DELETE("删除"),;

        private final String label;

        Operation(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }


}
