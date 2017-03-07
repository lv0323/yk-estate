package com.lyun.estate.biz.showing.service

import com.lyun.estate.biz.showing.def.ShowingDefine
import com.lyun.estate.biz.showing.entity.Showing
import com.lyun.estate.biz.showing.repo.ShowingRepo
import com.lyun.estate.core.supports.exceptions.EstateException
import com.lyun.estate.core.supports.exceptions.ExCode
import com.lyun.estate.core.supports.exceptions.ExceptionUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Service
class ShowingService {

    @Autowired
    ShowingRepo showingRepo

    Showing create(Showing showing) {
        ExceptionUtil.checkNotNull("带看信息", showing)
        showing.setProcess(ShowingDefine.Process.CREATED)

        if (showingRepo.save(showing) > 0) {
            return showingRepo.findOne(showing.getId())
        }
        throw new EstateException(ExCode.CREATE_FAIL, "带看记录", showing.toString())
    }

    Showing close(Long showingId, ShowingDefine.Process process) {
        ExceptionUtil.checkNotNull("带看编号", showingId)
        ExceptionUtil.checkIllegal(process != null && process.isEnd(), "状态", process)

        if (showingRepo.close(showingId, process) > 0) {
            return showingRepo.findOne(showingId)
        } else {
            def showing = showingRepo.findOne(showingId)
            if (Objects.nonNull(showing) && showing.getProcess().isEnd()) {
                return showing
            }
        }
        throw new EstateException(
                ExCode.UPDATE_FAIL,
                "带看",
                new Showing().setId(showingId).setProcess(process).toString())
    }
}
