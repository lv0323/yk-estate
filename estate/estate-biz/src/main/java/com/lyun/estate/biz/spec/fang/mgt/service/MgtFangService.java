package com.lyun.estate.biz.spec.fang.mgt.service;

import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public interface MgtFangService {

    public Fang createFang(Fang fang);

    public FangExt createFangExt(FangExt fangExt);

    public FangContact createFangContact(FangContact fangContact);

    public boolean checkFangContact(FangContact fangContact);

    public FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner);
}
