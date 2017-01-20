package com.lyun.estate.biz.information.entity;

import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class Information {
    private Long id;
    private String infoTitle;
    private String infoSummary;
    private String infoContent;
    private InfoContentType contentType;
    private InfoBusinessType businessType;
    private Long sender;
    private Long receiver;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoSummary() {
        return infoSummary;
    }

    public void setInfoSummary(String infoSummary) {
        this.infoSummary = infoSummary;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public InfoContentType getContentType() {
        return contentType;
    }

    public void setContentType(InfoContentType contentType) {
        this.contentType = contentType;
    }

    public InfoBusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(InfoBusinessType businessType) {
        this.businessType = businessType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSender() {
        return sender;
    }

    public void setSender(Long sender) {
        this.sender = sender;
    }

    public Long getReceiver() {
        return receiver;
    }

    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Information{" +
                "id=" + id +
                ", infoTitle='" + infoTitle + '\'' +
                ", infoSummary='" + infoSummary + '\'' +
                ", infoContent='" + infoContent + '\'' +
                ", contentType=" + contentType +
                ", businessType=" + businessType +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
