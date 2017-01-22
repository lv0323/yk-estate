package com.lyun.estate.biz.information.entity;

import com.lyun.estate.biz.information.def.InfoBusinessType;
import com.lyun.estate.biz.information.def.InfoContentType;

import java.util.Date;

/**
 * Created by jesse on 2017/1/20.
 */
public class InformationResource {
    private Long id;
    private String infoTitle;
    private String infoSummary;
    private String infoContent;
    private InfoContentType contentType;
    private InfoBusinessType businessType;
    private Long sender;
    private Long receiver;
    private String url;
    private Date createTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
