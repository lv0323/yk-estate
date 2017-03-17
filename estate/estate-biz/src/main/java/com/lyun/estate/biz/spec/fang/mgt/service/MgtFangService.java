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

    Fang updateFangBase(Fang fang);

    Fang getFangBase(Long fangId);

    FangExt createFangExt(FangExt fangExt);

    FangExt updateFangExt(FangExt fangExt);

    FangExt getFangExt(Long fangId);


    FangContact createFangContact(FangContact fangContact);

    FangContact updateContact(FangContact contact);

    FangContact getContact(Long fangId);


    FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner);

    List<FangInfoOwner> getInfoOwners(Long fangId);


    FangDescr createFangDescr(FangDescr fangDescr);

    FangDescr updateDesc(FangDescr fangDescr);

    FangDescr findDescr(Long fangId);


    FangFollow createFollow(FangFollow fangFollow);

    PageList<FangFollowDTO> listFollow(FangFollowFilter filter, PageBounds pageBounds);


    FangCheck createCheck(FangCheck fangCheck);

    PageList<FangCheckDTO> listCheck(FangCheckFilter filter, PageBounds pageBounds);


    MgtFangSummary getFangSummary(Long fangId);

    PageList<MgtFangSummary> listSummary(MgtFangFilter filter, MgtFangSummaryOrder order, PageBounds pageBounds);

    MgtFangTiny getFangTiny(Long fangId);

    MgtFangTiny getFangTinyByLicenceId(Long licenceId);

    MgtFangSummary getFangSummaryByLicenceId(Long licenceId);
}
