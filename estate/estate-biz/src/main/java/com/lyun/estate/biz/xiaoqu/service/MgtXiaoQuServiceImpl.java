package com.lyun.estate.biz.xiaoqu.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.def.StructureType;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.support.settings.SettingProvider;
import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.biz.support.settings.entity.Setting;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.repository.MgtXiaoQuRepository;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Created by Jeffrey on 2017-02-21.
 */
@Service
public class MgtXiaoQuServiceImpl implements MgtXiaoQuService {

    @Autowired
    private MgtXiaoQuRepository mgtXiaoQuRepository;

    @Autowired
    private SettingProvider settingProvider;

    @Autowired
    private FileService fileService;

    private FileDescription xiaoQuDefaultImg;


    @PostConstruct
    private void init() {
        Setting defaultImg = settingProvider.find(NameSpace.XIAO_QU, "default_img");
        xiaoQuDefaultImg = fileService.findOne(Long.valueOf(defaultImg.getValue()));
    }

    @Override
    public XiaoQu findOne(Long id) {
        ExceptionUtil.checkNotNull("小区编号", id);
        return mgtXiaoQuRepository.findOne(id);
    }

    @Override
    public PageList<MgtXiaoQuSummary> list(MgtXiaoQuFilter filter, PageBounds pageBounds) {
        PageList<MgtXiaoQuSummary> result = mgtXiaoQuRepository.list(filter, pageBounds);
        result.forEach(t -> {

            t.setStructureStr(StructureType.getTypeStr(t.getStructureType()));

            FileDescription firstImg = fileService.findFirst(t.getId(),
                    DomainType.XIAO_QU,
                    CustomType.SHI_JING,
                    FileProcess.WATERMARK);
            t.setImageURI(Optional.ofNullable(firstImg)
                    .map(FileDescription::getFileURI)
                    .orElse(xiaoQuDefaultImg.getFileURI()));

        });
        return result;
    }

    @Override
    public MgtXiaoQuDetail detail(Long xiaoQuId) {
        MgtXiaoQuDetail result = mgtXiaoQuRepository.detail(xiaoQuId);
        if (result != null) {
            result.setStructureStr(StructureType.getTypeStr(result.getStructureType()));
            FileDescription firstImg = fileService.findFirst(result.getId(),
                    DomainType.XIAO_QU,
                    CustomType.SHI_JING,
                    FileProcess.WATERMARK);
            result.setImageURI(Optional.ofNullable(firstImg)
                    .map(FileDescription::getFileURI)
                    .orElse(xiaoQuDefaultImg.getFileURI()));
        }
        return result;
    }
}
