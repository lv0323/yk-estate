package com.lyun.estate.biz.fang.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.entity.FangTag;
import com.lyun.estate.biz.fang.repo.FangRepository;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.fang.entity.FangDetail;
import com.lyun.estate.biz.spec.fang.entity.FangFilter;
import com.lyun.estate.biz.spec.fang.entity.FangSummary;
import com.lyun.estate.biz.spec.fang.entity.FangSummaryOrder;
import com.lyun.estate.biz.spec.fang.service.FangService;
import com.lyun.estate.biz.spec.file.service.FileService;
import com.lyun.estate.biz.spec.xiaoqu.entity.XiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.service.XiaoQuService;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jeffrey on 2017-01-20.
 */
@Service
public class FangServiceImpl implements FangService {


    @Autowired
    private FangRepository fangRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private XiaoQuService xiaoQuService;

    @Override
    public PageList<FangSummary> findFangSummaryByKeyword(FangFilter filter, FangSummaryOrder order,
                                                          PageBounds pageBounds) {


        return null;
    }

    @Override
    public FangDetail getDetail(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangDetail fangDetail = fangRepository.findDetail(id);
        if (fangDetail != null) {
            fangDetail.setDescr(fangRepository.findDescr(id));
            XiaoQuDetail detail = xiaoQuService.getDetail(fangDetail.getXiaoQuId());
            fangDetail.setSubDistrict(detail.getSubDistrict());
            fangDetail.setDistrict(detail.getDistrict());
        }
        return fangDetail;
    }

    @Override
    public FangSummary getSummary(Long id) {
        ExceptionUtil.checkNotNull("房编号", id);
        FangSummary summary = fangRepository.findSummary(id);
        List<FangTag> fangTags = fangRepository.findTags(id);
        summary.setTags(fangTags.stream().map(FangTag::getHouseTag).collect(Collectors.toList()));
        summary.setImageURI(fileService.findFirst(id, DomainType.FANG, CustomType.SHIJING, FileProcess.WATERMARK)
                .getFileURI());
        return summary;
    }

}
