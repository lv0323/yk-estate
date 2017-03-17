package com.lyun.estate.biz.customer.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class CustomerDefine {

    public enum Source implements LabelEnum {
        VISIT("来访"),
        CALLING("来电"),
        ONLINE("网络"),
        AGENCY("中介"),;
        private final String label;

        Source(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Status implements LabelEnum {
        ZI_ZU("自租"),
        HE_ZU("合租"),
        JIE_ZHU("借住"),
        LIN_SHI("临时"),
        ZI_GOU("自购"),
        QI_TA("其他"),;

        private String label;

        Status(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Level implements LabelEnum {
        YOU("优质客"),
        GANG("刚需客"),
        YI("一般客"),;

        private String label;

        Level(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    public enum Purpose implements LabelEnum {
        GANG("刚需"),
        GAI("改善"),
        TOU("投资");

        private String label;

        Purpose(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    public enum TimeLimits implements LabelEnum {
        D_2("2天内"),
        D_5("5天内"),
        W_1("1周内"),
        W_2("2周内"),
        W_3("3周内"),
        M_1("1月内"),
        M_3("3月内"),;

        private String label;

        TimeLimits(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }
}
