package com.lyun.estate.biz.contract.def

import com.lyun.estate.core.supports.labelenum.LabelEnum

/**
 * Created by Jeffrey on 2017-03-09.
 */
class ContractDefine {
    enum Process implements LabelEnum {
        CREATED('新建', false),
        CANCEL('取消', true),
        SUCCESS('完成', true)

        final String label
        final boolean end

        Process(String label, boolean end) {
            this.label = label
            this.end = end
        }
    }

    enum Type implements LabelEnum {
        DEAL("成交")

        final String label

        Type(String label) {
            this.label = label
        }
    }
}
