package com.lyun.estate.mgt.xiaoqu.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.base.Strings;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.entity.FileExt;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.mgt.service.MgtXiaoQuService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.mgt.context.MgtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Jeffrey on 2017-04-17.
 */
@Service
public class XiaoQuMgtService {

    @Autowired
    private MgtXiaoQuService mgtXiaoQuService;

    @Autowired
    private FileService fileService;

    @Autowired
    private MgtContext mgtContext;

    private Logger logger = LoggerFactory.getLogger(XiaoQuMgtService.class);

    public XiaoQu findOne(Long id) {
        return mgtXiaoQuService.findOne(id);
    }

    public PageList<MgtXiaoQuSummary> list(MgtXiaoQuFilter filter, PageBounds pageBounds) {
        return mgtXiaoQuService.list(filter, pageBounds);
    }

    public MgtXiaoQuDetail detail(Long xiaoQuId) {
        return mgtXiaoQuService.detail(xiaoQuId);
    }

    public FileDescription createImage(Long xiaoQuId, CustomType customType, InputStream inputStream, String suffix) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        ExceptionUtil.checkNotNull("文件业务类型", customType);
        ExceptionUtil.checkNotNull("文件输入流", inputStream);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(suffix), "文件后缀名", suffix);

        if (customType == CustomType.SHI_JING || customType == CustomType.HU_XING) {
            FileDescription fileDescription = new FileDescription()
                    .setOwnerId(xiaoQuId)
                    .setOwnerType(DomainType.XIAO_QU)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.WATERMARK.getFlag())
                    .setCustomType(customType)
                    .setExt(new FileExt().setCreateById(mgtContext.getOperator().getId()).toJsonString());
            return fileService.save(fileDescription, inputStream, suffix);
        }
        throw new EstateException(ExCode.CUSTOM_TYPE_NOT_SUPPORTED, customType);
    }

    public List<FileDescription> getImages(Long xiaoQuId, CustomType customType) {
        ExceptionUtil.checkNotNull("小区编号", xiaoQuId);
        ExceptionUtil.checkNotNull("文件业务类型", customType);

        return fileService.find(xiaoQuId, DomainType.XIAO_QU, customType, FileProcess.WATERMARK);
    }

    public Object deleteImage(Long fileId) {
        FileDescription fileDescription = fileService.findOne(fileId);
        if (fileDescription == null || fileDescription.getDeleted()) {
            throw new EstateException(ExCode.FILE_NOT_EXIST);
        }
        if (fileDescription.getOwnerType() != DomainType.XIAO_QU) {
            throw new EstateException("文件归属类型不正确");
        }

        //permission check

        logger.info("员工{} 删除了图片{}", mgtContext.getOperator().getId(), fileId);

        return fileService.delete(fileId);
    }

    public Object setFirstImage(Long fileId) {
        FileDescription fileDescription = fileService.findOne(fileId);
        if (fileDescription == null || fileDescription.getDeleted()) {
            throw new EstateException(ExCode.FILE_NOT_EXIST);
        }
        if (fileDescription.getOwnerType() != DomainType.XIAO_QU) {
            throw new EstateException("文件归属类型不正确");
        }
        //todo: permission check
        logger.info("员工{} 将图片{}置顶", mgtContext.getOperator().getId(), fileId);
        return fileService.setFirst(fileId);
    }
}
