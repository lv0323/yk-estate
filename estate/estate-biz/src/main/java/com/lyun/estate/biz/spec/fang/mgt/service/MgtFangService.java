package com.lyun.estate.biz.spec.fang.mgt.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public interface MgtFangService {

    Fang createFang(Fang fang);

    FangExt createFangExt(FangExt fangExt);

    FangContact createFangContact(FangContact fangContact);

    boolean checkFangContact(FangContact fangContact);

    FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner);

    PageList<MgtFangSummary> listSummary(MgtFangFilter filter, MgtFangSummaryOrder order, PageBounds pageBounds);

    List<FangContact> getContacts(Long fangId);

    List<FangInfoOwner> getInfoOwners(Long fangId);

    FangFollow createFollow(FangFollow fangFollow);

    PageList<FangFollow> getFollows(Long fangId, PageBounds pageBounds);

    FangCheck createCheck(FangCheck fangCheck);

    PageList<FangCheck> getChecks(Long fangId, PageBounds pageBounds);

    FangDescr createFangDescr(FangDescr fangDescr);

    String buildHead(String xiaoQuName, Long licenceId);

    FangDescr updateDesc(FangDescr fangDescr);

    FangDescr findDescr(Long fangId);

    Fang updateFangBase(Fang fang);

    FangExt updateFangExt(FangExt fangExt);

    Fang getFangBase(Long fangId);

    FangExt getFangExt(Long fangId);
}
