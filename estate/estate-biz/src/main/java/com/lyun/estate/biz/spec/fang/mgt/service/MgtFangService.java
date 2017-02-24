package com.lyun.estate.biz.spec.fang.mgt.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangFilter;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummary;
import com.lyun.estate.biz.spec.fang.mgt.entity.MgtFangSummaryOrder;

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
}
