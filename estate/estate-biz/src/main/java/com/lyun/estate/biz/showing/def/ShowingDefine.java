package com.lyun.estate.biz.showing.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-06.
 */
public class ShowingDefine {
    public enum Process implements LabelEnum {
        CREATED("新建", false),
        CANCEL("取消", true),
        SUCCESS("完成", true),;
        private final String label;
        private final boolean end;

        Process(String label, boolean end) {
            this.label = label;
            this.end = end;
        }

        public String getLabel() {
            return label;
        }

        public boolean isEnd() {
            return end;
        }
    }

}
