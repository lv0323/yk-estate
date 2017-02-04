package com.lyun.estate.biz.fang.entity;

/**
 * Created by Jeffrey on 2017-01-23.
 */
public class FangDescr {
    private Long id;
    private Long fangId;
    private String title;
    private String core;
    private String huXing;
    private String quanShu;
    private String shuiFei;
    private String zhuangXiu;
    private String jiaoTong;
    private String xiaoQu;

    public Long getId() {
        return id;
    }

    public FangDescr setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFangId() {
        return fangId;
    }

    public FangDescr setFangId(Long fangId) {
        this.fangId = fangId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FangDescr setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getCore() {
        return core;
    }

    public FangDescr setCore(String core) {
        this.core = core;
        return this;
    }

    public String getHuXing() {
        return huXing;
    }

    public FangDescr setHuXing(String huXing) {
        this.huXing = huXing;
        return this;
    }

    public String getQuanShu() {
        return quanShu;
    }

    public FangDescr setQuanShu(String quanShu) {
        this.quanShu = quanShu;
        return this;
    }

    public String getShuiFei() {
        return shuiFei;
    }

    public FangDescr setShuiFei(String shuiFei) {
        this.shuiFei = shuiFei;
        return this;
    }

    public String getZhuangXiu() {
        return zhuangXiu;
    }

    public FangDescr setZhuangXiu(String zhuangXiu) {
        this.zhuangXiu = zhuangXiu;
        return this;
    }

    public String getJiaoTong() {
        return jiaoTong;
    }

    public FangDescr setJiaoTong(String jiaoTong) {
        this.jiaoTong = jiaoTong;
        return this;
    }

    public String getXiaoQu() {
        return xiaoQu;
    }

    public FangDescr setXiaoQu(String xiaoQu) {
        this.xiaoQu = xiaoQu;
        return this;
    }
}
