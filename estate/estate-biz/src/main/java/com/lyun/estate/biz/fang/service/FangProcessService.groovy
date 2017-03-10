package com.lyun.estate.biz.fang.service

import com.lyun.estate.biz.fang.def.HouseProcess
import com.lyun.estate.biz.fang.entity.Fang
import com.lyun.estate.biz.fang.repo.MgtFangRepository
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService
import com.lyun.estate.biz.support.def.BizType
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
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


    @Transactional
    Fang publish(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        if (fang.getProcess() == HouseProcess.DELEGATE ||
                fang.getProcess() == HouseProcess.UN_PUBLISH) {
            mgtFangRepository.updateProcess(fangId, HouseProcess.PUBLISH)
            increaseHouseCount(fang.bizType, fang.xiaoQuId)
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
            mgtFangRepository.updateProcess(fangId, HouseProcess.UN_PUBLISH)
            if (fang.getProcess() == HouseProcess.PUBLISH) {
                decreaseHouseCount(fang.bizType, fang.xiaoQuId)
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
            mgtFangRepository.updateProcess(fangId, HouseProcess.SUCCESS)
            if (fang.getProcess() == HouseProcess.PUBLISH) {
                decreaseHouseCount(fang.bizType, fang.xiaoQuId)
            }
            return mgtFangRepository.findFang(fangId)
        } else {
            throw new EstateException(ExCode.PROCESS_ILLEGAL, fangId, fang.process, HouseProcess.SUCCESS)
        }

    }

    @Transactional
    boolean delete(long fangId) {
        Fang fang = mgtFangRepository.selectForUpdate(fangId)
        if (fang == null || Objects.equals(fang.getDeleted(), true)) {
            throw new EstateException(ExCode.NOT_FOUND, fangId, "房源")
        }
        mgtFangRepository.delete(fangId)
        if (fang.process == HouseProcess.PUBLISH) {
            decreaseHouseCount(fang.bizType, fang.xiaoQuId)
        }
        return true
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
