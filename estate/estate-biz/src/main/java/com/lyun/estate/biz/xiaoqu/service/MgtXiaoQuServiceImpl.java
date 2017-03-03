package com.lyun.estate.biz.xiaoqu.service;

import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.repository.MgtXiaoQuRepository;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtXiaoQuServiceImpl implements MgtXiaoQuService {

    private MgtXiaoQuRepository mgtXiaoQuRepository;

    public MgtXiaoQuServiceImpl(MgtXiaoQuRepository mgtXiaoQuRepository) {
        this.mgtXiaoQuRepository = mgtXiaoQuRepository;
    }

    @Override
    public XiaoQu findOne(Long id) {
        ExceptionUtil.checkNotNull("小区编号", id);
        return mgtXiaoQuRepository.findOne(id);
    }
}
