package com.lyun.estate.biz.fang.service

import com.google.common.base.Strings
import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.service.EventService
import com.lyun.estate.biz.fang.def.HouseProcess
import com.lyun.estate.biz.fang.def.HouseSubProcess
import com.lyun.estate.biz.fang.def.InfoOwnerReason
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.biz.fang.entity.FangDescr
import com.lyun.estate.biz.fang.entity.FangInfoOwner
import com.lyun.estate.biz.fang.repo.FangDescrRepo
import com.lyun.estate.biz.fang.repo.MgtFangRepository
import com.lyun.estate.biz.file.def.CustomType
import com.lyun.estate.biz.file.def.FileProcess
import com.lyun.estate.biz.file.service.FileService
import com.lyun.estate.biz.houselicence.service.HouseLicenceService
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.biz.support.def.DomainType
import com.lyun.estate.biz.support.settings.SettingProvider
import com.lyun.estate.biz.support.settings.def.NameSpace
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.utils.CommonUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils

/**
 * Created by Jeffrey on 2017-03-10.
 */
@Service
class FangProcessService {

    @Autowired
    XiaoQuService xiaoQuService

    @Autowired
    MgtFangRepository mgtFangRepository

    @Autowired
    FangDescrRepo fangDescrRepo

    @Autowired
    HouseLicenceService houseLicenceService

    @Autowired
    MgtFangService mgtFangService

    @Autowired
    FileService fileService

    @Autowired
    SettingProvider settingProvider

    @Autowired
    EventService eventService

    Logger logger = LoggerFactory.getLogger(FangProcessService.class)


    @Transactional
    Fang publish(long fangId, FangInfoOwner newInfoOwner) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.DELEGATE
                || fang.getProcess() == HouseProcess.UN_PUBLISH
                || fang.getProcess() == HouseProcess.PAUSE) {

            //update process
            mgtFangRepository.publish(fangId)

            // 如果是下架后重新上架，重新上架人为新的归属人,且发送上架事件
            if (fang.getProcess() == HouseProcess.UN_PUBLISH) {
                newInfoOwner.setFangId(fangId)
                newInfoOwner.setReason(InfoOwnerReason.RE_PUBLISH)

                mgtFangService.changeFangInfoOwner(newInfoOwner)
            }

            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.PUBLISH)
        }
    }

    @Transactional
    Fang pause(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang.getProcess() == HouseProcess.DELEGATE
                || fang.getProcess() == HouseProcess.PUBLISH) {

            //update process and sub_process
            mgtFangRepository.pause(fangId)

            // update count
            if (fang.getProcess() == HouseProcess.PUBLISH
                    && fang.getSubProcess() == HouseSubProcess.PUBLIC) {
                decreaseHouseCount(fang.bizType, fang.xiaoQuId)
            }

            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.PAUSE)
        }
    }

    @Transactional
    Fang unPublish(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.DELEGATE
                || fang.getProcess() == HouseProcess.PUBLISH
                || fang.getProcess() == HouseProcess.PAUSE) {
            // update process and sub_process
            mgtFangRepository.unPublish(fangId)
            // update count
            if (fang.getProcess() == HouseProcess.PUBLISH
                    && fang.getSubProcess() == HouseSubProcess.PUBLIC) {
                decreaseHouseCount(fang.bizType, fang.xiaoQuId)
            }

            try {
                eventService.produce(new Event()
                        .setUuid(CommonUtil.getUuid())
                        .setDomainId(fangId).setDomainType(DomainType.FANG)
                        .setType(EventDefine.Type.FANG_PROCESS))
            } catch (Exception e) {
                logger.error("unPublish event error:{}", e)
            }
            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.UN_PUBLISH)
        }
    }

    @Transactional
    Fang deal(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        //update process
        mgtFangRepository.deal(fangId)
        //update count
        if (fang.getProcess() == HouseProcess.PUBLISH
                && fang.getSubProcess() == HouseSubProcess.PUBLIC) {
            decreaseHouseCount(fang.bizType, fang.xiaoQuId)
        }
        //update licence status
        houseLicenceService.invalid(fang.getLicenceId())

        try {
            eventService.produce(new Event()
                    .setUuid(CommonUtil.getUuid())
                    .setDomainId(fangId).setDomainType(DomainType.FANG)
                    .setType(EventDefine.Type.FANG_PROCESS))
        } catch (Exception e) {
            logger.error("deal event error:{}", e)
        }
        return mgtFangRepository.findFang(fangId)
    }

    @Transactional
    Fang delete(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        //delete
        mgtFangRepository.delete(fangId)
        //update count
        if (fang.process == HouseProcess.PUBLISH
                && fang.getSubProcess() == HouseSubProcess.PUBLIC) {
            decreaseHouseCount(fang.bizType, fang.xiaoQuId)
        }
        //update licence status
        houseLicenceService.invalid(fang.getLicenceId())
        return mgtFangRepository.findFang(fangId)
    }

    @Transactional
    Fang applyPublic(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }

        if (fang.getProcess() == HouseProcess.PUBLISH && fang.getSubProcess() == null) {

            publicPreCheck(fangId)

            mgtFangRepository.applyPublic(fangId)
            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.SUB_PROCESS_ILLEGAL, fangId, fang.process, fang.subProcess)
        }
    }


    @Transactional
    Fang rejectPublic(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.PUBLISH
                && fang.getSubProcess() == HouseSubProcess.PRE_PUBLIC) {
            mgtFangRepository.rejectPublic(fangId)
            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.SUB_PROCESS_ILLEGAL, fangId, fang.process, fang.subProcess)
        }
    }

    @Transactional
    Fang confirmPublic(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.PUBLISH
                && fang.getSubProcess() == HouseSubProcess.PRE_PUBLIC) {

            mgtFangRepository.confirmPublic(fangId)

            //update count
            increaseHouseCount(fang.bizType, fang.xiaoQuId)

            try {
                eventService.produce(new Event()
                        .setUuid(CommonUtil.getUuid())
                        .setDomainId(fangId).setDomainType(DomainType.FANG)
                        .setType(EventDefine.Type.FANG_PUBLISH))
            } catch (Exception e) {
                logger.error("confirm public event error:{}", e)
            }
            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.SUB_PROCESS_ILLEGAL, fangId, fang.process, fang.subProcess)
        }
    }

    @Transactional
    Fang undoPublic(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.PUBLISH
                && fang.getSubProcess() == HouseSubProcess.PUBLIC) {
            mgtFangRepository.undoPublic(fangId)
            //update count
            decreaseHouseCount(fang.bizType, fang.xiaoQuId)

            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.SUB_PROCESS_ILLEGAL, fangId, fang.process, fang.subProcess)
        }
    }

    def publicPreCheck(long fangId) {
        //check desc core
        FangDescr fangDescr = fangDescrRepo.findByFangId(fangId)
        if (Strings.isNullOrEmpty(fangDescr.getCore())) {
            throw new EstateException(ExCode.DESC_CORE_NULL, fangId)
        }
        //查三证
        def setting = settingProvider.find(NameSpace.CONFIG, "CHECK_DOCS")
        if (Boolean.valueOf(setting.getValue())) {

            checkFangDoc(fangId, CustomType.CERTIF)
            checkFangDoc(fangId, CustomType.ATTORNEY)
            checkFangDoc(fangId, CustomType.OWNER_ID_CARD)

        }
    }

    def checkFangDoc(long fangId, CustomType customType) {
        if (CollectionUtils.isEmpty(
                fileService.find(fangId, DomainType.FANG, customType, FileProcess.WATERMARK))) {
            throw new EstateException(ExCode.REQUIRE_DOCS_NULL, customType.label)
        }
    }

    def increaseHouseCount(BizType bizType, long xiaoQuId) {
        if (bizType == BizType.SELL) {
            xiaoQuService.increaseSellCount(xiaoQuId)
        } else if (bizType == BizType.RENT) {
            xiaoQuService.increaseRentCount(xiaoQuId)
        }
        produceXiaoQuCountEvent(xiaoQuId)
    }

    def decreaseHouseCount(BizType bizType, long xiaoQuId) {
        if (bizType == BizType.SELL) {
            xiaoQuService.decreaseSellCount(xiaoQuId)
        } else if (bizType == BizType.RENT) {
            xiaoQuService.decreaseRentCount(xiaoQuId)
        }
        produceXiaoQuCountEvent(xiaoQuId)
    }

    def produceXiaoQuCountEvent(long xiaoQuId) {
        try {
            eventService.produce(new Event()
                    .setUuid(CommonUtil.getUuid())
                    .setDomainId(xiaoQuId).setDomainType(DomainType.XIAO_QU)
                    .setType(EventDefine.Type.XIAO_QU_COUNT))
        } catch (Exception e) {
            logger.error("xiao qu count event error:{}", e)
        }
    }
}
