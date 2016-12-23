package com.lyun.estate.biz.file.service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.Target;
import com.lyun.estate.biz.file.entity.FileEntity;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.core.exception.EstateException;
import com.lyun.estate.core.exception.ExCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssFileService extends AbstractFileService {

    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAItEanE6SnLPfj";
    private static final String ACCESS_KEY_SECRET = "u3YhcVy3OIS0wezHPetlU2esDydWd8";
    private static final String BUCKET_NAME = "yk-estate-image";

    private static final String WATERMARK_STYLE = "image/watermark,image_cGFuZGEuanBn,t_50";

    private OSSClient client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    public OssFileService(FileRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public FileEntity save(FileEntity entity, InputStream inputStream, String suffix, FileProcess process) {
        try {
            entity.setTarget(Target.OSS);
            entity.setPath(UUID.randomUUID().toString().toLowerCase() + suffix);

            client.putObject(new PutObjectRequest(BUCKET_NAME, entity.getPath(), inputStream));
            if (process == FileProcess.WATERMARK) {
                try {
                    GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, entity.getPath());
                    request.setProcess(WATERMARK_STYLE);
                    OSSObject object = client.getObject(request);

                    FileEntity newEntity = entity.clone();
                    newEntity.setCustomType(CustomType.valueOf(entity.getCustomType().toString() + "_WM"));
                    newEntity.setPath(UUID.randomUUID().toString().toLowerCase() + suffix);
                    client.putObject(new PutObjectRequest(BUCKET_NAME, newEntity.getPath(), object.getObjectContent()));
                    repository.insert(entity);
                    repository.insert(newEntity);
                    return newEntity;
                } catch (OSSException | ClientException e) {
                    client.deleteObject(BUCKET_NAME, entity.getPath());
                    throw e;
                }
            }
            repository.insert(entity);
            return entity;
        } catch (Exception e) {
            throw new EstateException(e, ExCode.OSS_EXCEPTION);
        }
    }
}
