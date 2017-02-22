package com.lyun.estate.biz.fang.service;

import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.repo.MgtFangRepository;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtFangService {

    private MgtFangRepository mgtFangRepository;

    public MgtFangService(MgtFangRepository mgtFangRepository) {
        this.mgtFangRepository = mgtFangRepository;
    }

    public Fang createFang(Fang fang) {
        if (mgtFangRepository.saveFang(fang) > 0) {
            return mgtFangRepository.findFang(fang.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房", fang.toString());
    }

    public FangExt createFangExt(FangExt fangExt) {
        if (mgtFangRepository.saveFangExt(fangExt) > 0) {
            return mgtFangRepository.findFangExt(fangExt.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房扩展信息", fangExt.toString());

    }

    public void createFangContact(FangContact fangContact) {

    }

    public void createFangInfoOwner(FangInfoOwner fangInfoOwner) {

    }
}
