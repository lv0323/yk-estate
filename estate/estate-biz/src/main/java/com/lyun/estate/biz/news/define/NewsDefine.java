package com.lyun.estate.biz.news.define;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-03-28.
 */
public class NewsDefine {
    public enum Category implements LabelEnum {
        INFO("知识"),
        TOURIST("旅游"),
        POLICY("政策");

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
