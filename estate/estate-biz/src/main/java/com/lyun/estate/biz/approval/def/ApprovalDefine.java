package com.lyun.estate.biz.approval.def;

import com.lyun.estate.core.supports.labelenum.LabelEnum;

/**
 * Created by Jeffrey on 2017-06-12.
 */
public class ApprovalDefine {
    public enum Type implements LabelEnum {
        LEAVING("外出", false),
        BIZ_TRIP("出差", false),
        COLD_VISIT("陌拜", false),
        SIGNING("签约", true),;

        private final String label;
        private final boolean needApproval;

        Type(String label, boolean needApproval) {
            this.label = label;
            this.needApproval = needApproval;
        }

        @Override
        public String getLabel() {
            return label;
        }

        public boolean isNeedApproval() {
            return needApproval;
        }
    }

    public enum Status implements LabelEnum {
        CREATED("待审核", false),
        APPROVED("审核通过", true),
        REJECTED("被拒绝", true),;

        private final String label;
        private final boolean end;

        Status(String label, boolean end) {
            this.label = label;
            this.end = end;
        }

        @Override
        public String getLabel() {
            return label;
        }

        public boolean isEnd() {
            return end;
        }
    }

    public enum BossType {
        DEPT_MANAGER,//店长
        MANAGER,//总经理
        AGENT,//经纪人
        BOSS;//老板
    }

}
