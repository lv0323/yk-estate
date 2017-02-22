package com.lyun.estate.biz.xiaoqu.service;

import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.repository.MgtXiaoQuRepository;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtXiaoQuService {

    private MgtXiaoQuRepository mgtXiaoQuRepository;

    public MgtXiaoQuService(MgtXiaoQuRepository mgtXiaoQuRepository) {
        this.mgtXiaoQuRepository = mgtXiaoQuRepository;
    }

    public XiaoQu findOne(Long id) {
        ExceptionUtil.checkNotNull("小区编号", id);
        return mgtXiaoQuRepository.findOne(id);
    }
}
