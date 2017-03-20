package com.lyun.estate.biz.fang.service

import com.google.common.base.Strings
import com.lyun.estate.biz.event.def.EventDefine
import com.lyun.estate.biz.event.entity.Event
import com.lyun.estate.biz.event.service.EventService
import com.lyun.estate.biz.fang.def.HouseProcess
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.biz.fang.entity.FangDescr
import com.lyun.estate.biz.fang.repo.FangDescrRepo
import com.lyun.estate.biz.fang.repo.MgtFangRepository
import com.lyun.estate.biz.houselicence.service.HouseLicenceService
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.biz.support.def.DomainType
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.utils.CommonUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
    EventService eventService

    Logger logger = LoggerFactory.getLogger(FangProcessService.class)


    @Transactional
    Fang publish(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.DELEGATE ||
                fang.getProcess() == HouseProcess.UN_PUBLISH) {

            //check desc core
            FangDescr fangDescr = fangDescrRepo.findByFangId(fangId)
            if (Strings.isNullOrEmpty(fangDescr.getCore())) {
                throw new EstateException(ExCode.DESC_CORE_NULL, fangId)
            }
            //update process
            mgtFangRepository.publish(fangId)
            //update count
            increaseHouseCount(fang.bizType, fang.xiaoQuId)
            //update licence status
            houseLicenceService.active(fang.getLicenceId())

            try {
                if (fang.getProcess() == HouseProcess.DELEGATE) {
                    eventService.produce(new Event()
                            .setUuid(CommonUtil.getUuid())
                            .setDomainId(fangId).setDomainType(DomainType.FANG)
                            .setType(EventDefine.Type.FANG_PUBLISH))
                } else if (fang.getProcess() == HouseProcess.UN_PUBLISH) {
                    eventService.produce(new Event()
                            .setUuid(CommonUtil.getUuid())
                            .setDomainId(fangId).setDomainType(DomainType.FANG)
                            .setType(EventDefine.Type.FANG_PROCESS))
                }
            } catch (EstateException e) {
                logger.error("publish event error:{}", e)
            }

            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.PUBLISH)
        }
    }

    @Transactional
    Fang unPublish(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.DELEGATE ||
                fang.getProcess() == HouseProcess.PUBLISH) {
            // update process
            mgtFangRepository.unPublish(fangId)
            // update count
            if (fang.getProcess() == HouseProcess.PUBLISH) {
                decreaseHouseCount(fang.bizType, fang.xiaoQuId)
            }
            // update licence status
            houseLicenceService.invalid(fang.getLicenceId())

            try {
                eventService.produce(new Event()
                        .setUuid(CommonUtil.getUuid())
                        .setDomainId(fangId).setDomainType(DomainType.FANG)
                        .setType(EventDefine.Type.FANG_PROCESS))
            } catch (EstateException e) {
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
        if (fang.getProcess() == HouseProcess.DELEGATE ||
                fang.getProcess() == HouseProcess.PUBLISH) {
            //update process
            mgtFangRepository.updateProcess(fangId, HouseProcess.SUCCESS)
            //update count
            if (fang.getProcess() == HouseProcess.PUBLISH) {
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
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.SUCCESS)
        }

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
        if (fang.process == HouseProcess.PUBLISH) {
            decreaseHouseCount(fang.bizType, fang.xiaoQuId)
        }
        //update licence status
        houseLicenceService.invalid(fang.getLicenceId())
        return mgtFangRepository.findFang(fangId)
    }

    def increaseHouseCount(BizType bizType, long xiaoQuId) {
        if (bizType == BizType.SELL) {
            xiaoQuService.increaseSellCount(xiaoQuId)
        } else if (bizType == BizType.RENT) {
            xiaoQuService.increaseRentCount(xiaoQuId)
        }
    }

    def decreaseHouseCount(BizType bizType, long xiaoQuId) {
        if (bizType == BizType.SELL) {
            xiaoQuService.decreaseSellCount(xiaoQuId)
        } else if (bizType == BizType.RENT) {
            xiaoQuService.decreaseRentCount(xiaoQuId)
        }
    }
}
