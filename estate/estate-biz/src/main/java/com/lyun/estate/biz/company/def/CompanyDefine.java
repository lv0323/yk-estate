package com.lyun.estate.biz.company.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-04-21.
 */
public class CompanyDefine {

    public enum Type implements LabelEnum {
        YK("盈科"),
        CHANNEL("渠道"),
        SINGLE_STORE("单店"),
        REGIONAL_AGENT("区域代理");

        private final String label;

        Type(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

}
