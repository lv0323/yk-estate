package com.lyun.estate.biz.spec.fang.mgt.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.domian.FangCheckDTO;
import com.lyun.estate.biz.fang.domian.FangFollowDTO;
import com.lyun.estate.biz.fang.domian.MgtFangTiny;
import com.lyun.estate.biz.fang.entity.*;
import com.lyun.estate.biz.spec.fang.mgt.entity.*;

import java.util.List;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public interface MgtFangService {

    Fang createFang(Fang fang);

    FangExt createFangExt(FangExt fangExt);

    FangContact createFangContact(FangContact fangContact);

    FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner);

    PageList<MgtFangSummary> listSummary(MgtFangFilter filter, MgtFangSummaryOrder order, PageBounds pageBounds);

    FangContact getContact(Long fangId);

    List<FangInfoOwner> getInfoOwners(Long fangId);

    FangFollow createFollow(FangFollow fangFollow);

    FangCheck createCheck(FangCheck fangCheck);

    PageList<FangCheckDTO> listCheck(FangCheckFilter filter, PageBounds pageBounds);

    FangDescr createFangDescr(FangDescr fangDescr);

    FangDescr updateDesc(FangDescr fangDescr);

    FangDescr findDescr(Long fangId);

    Fang updateFangBase(Fang fang);

    FangExt updateFangExt(FangExt fangExt);

    Fang getFangBase(Long fangId);

    FangExt getFangExt(Long fangId);

    MgtFangSummary getFangSummary(Long fangId);

    MgtFangTiny getFangTiny(Long fangId);

    MgtFangTiny getFangTinyByLicenceId(Long licenceId);

    PageList<FangFollowDTO> listFollow(FangFollowFilter filter, PageBounds pageBounds);

    FangContact updateContact(FangContact contact);
}
