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
import com.lyun.estate.biz.utils.settings.SettingProvider;
import com.lyun.estate.biz.utils.settings.def.NameSpace;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@PropertySource("estate/biz/oss.properties")
public class OssFileService extends AbstractFileService {

    private final String BUCKET_NAME;// "yk-estate-image"
    private final String WATERMARK_STYLE;// "image/watermark,image_cGFuZGEuanBn,t_50,g_center";
    private final OSSClient client;

    public OssFileService(FileRepository repository, SettingProvider settingProvider,
                          @Value("${oss.access_key_id}") String accessKeyId,
                          @Value("${oss.access_key_secret}") String accessKeySecret) {
        super(repository); //"oss-cn-hangzhou.aliyuncs.com"
        BUCKET_NAME = settingProvider.find(NameSpace.FILE, "bucket_name").getValue();
        WATERMARK_STYLE = settingProvider.find(NameSpace.FILE, "watermark_style").getValue();
        client = new OSSClient(settingProvider.find(NameSpace.FILE, "endpoint").getValue(),
                accessKeyId,
                accessKeySecret);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
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

            FileDescription result = repository.findOne(wmEntity.getId());
            result.setFileURI(getFileURI(result.getPath(), result.getTarget()));
            return result;
        }
        FileDescription result = repository.findOne(entity.getId());
        result.setFileURI(getFileURI(result.getPath(), result.getTarget()));
        return result;
    }
}
