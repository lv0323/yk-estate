package com.lyun.estate.biz.news.define;

import com.google.common.collect.Lists;
import com.lyun.estate.core.supports.labelenum.LabelEnum;

import java.util.List;

/**
 * Created by Jeffrey on 2017-03-28.
 */
public class NewsDefine {
    public enum Category implements LabelEnum {
        INFO("知识"),
        TOURIST("旅游"),
        POLICY("政策"),
        FRANCHISEE("加盟"),
        NEWS("资讯"),;

        private final String label;

        Category(String label) {
            this.label = label;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }

    public enum Scope implements LabelEnum {
        APP("APP", Lists.newArrayList(Category.INFO, Category.TOURIST, Category.POLICY)),
        WEBSITE("网站", Lists.newArrayList(Category.FRANCHISEE, Category.NEWS));
        private final String label;
        private final List<Category> categorys;

        Scope(String label, List<Category> categorys) {
            this.label = label;
            this.categorys = categorys;
        }

        @Override
        public String getLabel() {
            return label;
        }
    }
}
