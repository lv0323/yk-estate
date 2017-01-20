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
}
