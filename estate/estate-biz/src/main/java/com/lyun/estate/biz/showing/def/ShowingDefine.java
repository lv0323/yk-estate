package com.lyun.estate.biz.showing.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class ShowingDefine {
    public enum Process implements LabelEnum {
        CREATED("新建"),
        CANCEL("取消"),
        SUCCESS("结束"),;
        private final String label;

        Process(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

}
