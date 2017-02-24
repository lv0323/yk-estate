package com.lyun.estate.biz.fang.service;

import com.google.common.base.Strings;
import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.biz.fang.entity.FangInfoOwner;
import com.lyun.estate.biz.fang.repo.MgtFangRepository;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtFangServiceImpl implements MgtFangService {

    private MgtFangRepository mgtFangRepository;

    public MgtFangServiceImpl(MgtFangRepository mgtFangRepository) {
        this.mgtFangRepository = mgtFangRepository;
    }

    @Override
    public Fang createFang(Fang fang) {
        if (mgtFangRepository.saveFang(fang) > 0) {
            return mgtFangRepository.findFang(fang.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房", fang.toString());
    }

    @Override
    public FangExt createFangExt(FangExt fangExt) {
        if (mgtFangRepository.saveFangExt(fangExt) > 0) {
            return mgtFangRepository.findFangExt(fangExt.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "房扩展信息", fangExt.toString());

    }

    @Override
    public FangContact createFangContact(FangContact fangContact) {
        boolean flag = checkFangContact(fangContact);
        ExceptionUtil.checkIllegal(flag, "联系方式", fangContact.toString());
        if (mgtFangRepository.saveFangContact(fangContact) > 0) {
            return mgtFangRepository.findFangContact(fangContact.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "联系方式", fangContact.toString());
    }

    @Override
    public boolean checkFangContact(FangContact fangContact) {
        ExceptionUtil.checkNotNull("联系信息", fangContact);
        boolean flag;
        switch (fangContact.getContactType()) {
            case MOBILE:
                flag = ValidateUtil.isMobile(fangContact.getContactInfo());
                break;
            case QQ:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case WECHAT:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case PHONE:
                flag = !Strings.isNullOrEmpty(fangContact.getContactInfo());
                break;
            case EMAIL:
                flag = ValidateUtil.isEmail(fangContact.getContactInfo());
                break;
            default:
                flag = false;
                break;
        }
        return flag;
    }

    @Override
    public FangInfoOwner createFangInfoOwner(FangInfoOwner fangInfoOwner) {
        if (mgtFangRepository.saveFangInfoOwner(fangInfoOwner) > 0) {
            return mgtFangRepository.findFangInfoOwner(fangInfoOwner.getId());
        }
        throw new EstateException(ExCode.CREATE_FAIL, "联系方式", fangInfoOwner.toString());

    }
}
