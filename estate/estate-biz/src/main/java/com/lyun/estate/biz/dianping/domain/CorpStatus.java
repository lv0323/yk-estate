package com.lyun.estate.biz.dianping.domain;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by localuser on 2017/6/13.
 */
public enum CorpStatus implements LabelEnum {

    NEW("新建"), ACTIVE("激活"), SUSPEND("冻结");/*, REJECTED*/

    private String label;
    CorpStatus(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }


}
