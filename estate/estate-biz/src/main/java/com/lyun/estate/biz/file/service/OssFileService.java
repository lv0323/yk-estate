package com.lyun.estate.biz.file.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.google.common.base.Strings;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.Target;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OssFileService extends AbstractFileService {

    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAItEanE6SnLPfj";
    private static final String ACCESS_KEY_SECRET = "u3YhcVy3OIS0wezHPetlU2esDydWd8";
    private static final String BUCKET_NAME = "yk-estate-image";

    private static final String WATERMARK_STYLE = "image/watermark,image_cGFuZGEuanBn,t_50,g_center";

    private OSSClient client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    public OssFileService(FileRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public FileDescription save(FileDescription entity, InputStream inputStream, String suffix) {
        ExceptionUtil.checkNotNull("entity", entity);
        ExceptionUtil.checkNotNull("ownerId", entity.getOwnerId());
        ExceptionUtil.checkNotNull("ownerType", entity.getOwnerType());
        ExceptionUtil.checkNotNull("fileType", entity.getFileType());
        ExceptionUtil.checkNotNull("customType", entity.getCustomType());
        ExceptionUtil.checkNotNull("inputStream", inputStream);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(suffix), "suffix", suffix);
        if (!suffix.startsWith("."))
            suffix = '.' + suffix;

        int process = 0;
        if (entity.getFileProcess() != null)
            process = entity.getFileProcess();
        entity.setFileProcess(0);
        entity.setTarget(Target.OSS);
        entity.setPath(UUID.randomUUID().toString().toLowerCase() + suffix);

        client.putObject(new PutObjectRequest(BUCKET_NAME, entity.getPath(), inputStream));
        repository.insert(entity);

        if (process == FileProcess.WATERMARK.getFlag()) {
            GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, entity.getPath());
            request.setProcess(WATERMARK_STYLE);
            OSSObject object = client.getObject(request);

            FileDescription wmEntity = entity.clone();
            wmEntity.setFileProcess(FileProcess.WATERMARK.getFlag());
            wmEntity.setPath(UUID.randomUUID().toString().toLowerCase() + suffix);

            try (InputStream is = object.getObjectContent()) {
                client.putObject(new PutObjectRequest(BUCKET_NAME, wmEntity.getPath(), is));
            } catch (IOException e) {
                throw new EstateException(ExCode.OSS_EXCEPTION);
            }
            repository.insert(wmEntity);
            return wmEntity;
        }

        return entity;
    }
}
