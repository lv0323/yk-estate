package com.lyun.estate.biz.file.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.google.common.base.Strings;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.def.Target;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.utils.settings.def.NameSpace;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class OssFileService extends AbstractFileService {

    private String BUCKET_NAME;
    private String WATERMARK_STYLE;// "image/watermark,image_cGFuZGEuanBn,t_50,g_center";
    private OSSClient client;

    @PostConstruct
    private void init() {
        BUCKET_NAME = settingProvider.find(NameSpace.FILE, "bucket_name").getValue();
        WATERMARK_STYLE = settingProvider.find(NameSpace.FILE, "watermark_style").getValue();
        client = new OSSClient(settingProvider.find(NameSpace.FILE, "endpoint").getValue(),
                environment.getRequiredProperty("oss.access_key_id"),
                environment.getRequiredProperty("oss.access_key_secret"));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public FileDescription save(FileDescription entity, InputStream inputStream, String suffix) {
        ExceptionUtil.checkNotNull("entity", entity);
        ExceptionUtil.checkNotNull("ownerId", entity.getOwnerId());
        ExceptionUtil.checkNotNull("ownerType", entity.getOwnerType());
        ExceptionUtil.checkNotNull("fileType", entity.getFileType());
        ExceptionUtil.checkNotNull("inputStream", inputStream);
        ExceptionUtil.checkIllegal(!Strings.isNullOrEmpty(suffix), "suffix", suffix);
        if (!suffix.startsWith("."))
            suffix = '.' + suffix;

        byte[] bytes;
        try {
            bytes = IOUtils.readStreamAsByteArray(inputStream);
        } catch (IOException e) {
            throw new EstateException(ExCode.OSS_EXCEPTION);
        }

        if (entity.getFileType() == FileType.IMAGE) {
            try (ByteArrayInputStream testImageIS = new ByteArrayInputStream(bytes)) {
                BufferedImage testImage = ImageIO.read(testImageIS);
                if (testImage == null) {
                    throw new EstateException(ExCode.OSS_FILE_NOT_IMAGE);
                }
            } catch (IOException e) {
                throw new EstateException(ExCode.OSS_EXCEPTION);
            }
        }

        try (InputStream fileIS = new ByteArrayInputStream(bytes)) {
            int process = Optional.ofNullable(entity.getFileProcess()).orElse(0);

            entity.setFileProcess(0);
            entity.setTarget(Target.OSS);
            entity.setPath(UUID.randomUUID().toString().toLowerCase() + suffix);

            client.putObject(new PutObjectRequest(BUCKET_NAME,
                    entity.getPath(),
                    fileIS));
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

                FileDescription result = repository.findOne(wmEntity.getId());
                result.setFileURI(getFileURI(result.getPath(), result.getTarget()));
                return result;
            }
            FileDescription result = repository.findOne(entity.getId());
            result.setFileURI(getFileURI(result.getPath(), result.getTarget()));
            return result;
        } catch (IOException e) {
            throw new EstateException(ExCode.OSS_EXCEPTION);
        }


    }
}
