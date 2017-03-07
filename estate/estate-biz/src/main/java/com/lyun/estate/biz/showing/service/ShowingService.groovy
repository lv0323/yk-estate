package com.lyun.estate.biz.showing.service

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

        if (showingRepo.save(showing) > 0) {
            return showingRepo.findOne(showing.getId())
        }
        throw new EstateException(ExCode.CREATE_FAIL, "带看记录", showing.toString())
    }
}
