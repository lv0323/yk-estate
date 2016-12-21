package com.lyun.estate.biz.filesystem;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class AliyunFileSystem extends FileSystem {

    private static final String TARGET = "Aliyun";

    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAItEanE6SnLPfj";
    private static final String ACCESS_KEY_SECRET = "u3YhcVy3OIS0wezHPetlU2esDydWd8";
    private static final String BUCKET_NAME = "yk-estate-image";

    private static final String WATERMARK_STYLE = "image/watermark,image_cGFuZGEuanBn,t_50";

    private OSSClient client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    public AliyunFileSystem(FsRepository repository) {
        super(repository);
    }

    @Override
    public FsEntity create(OwnerType ownerType, long ownerId, String customType, FileType fileType, InputStream inputStream) {
        FsEntity entity = new FsEntity();
        entity.setOwnerType(ownerType);
        entity.setOwnerId(ownerId);
        entity.setCustomType(customType);
        entity.setFileType(fileType);
        entity.setTarget(TARGET);
        entity.setPath(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() + fileType.getSuffix());

        client.putObject(new PutObjectRequest(BUCKET_NAME, entity.getPath(), inputStream));
        repository.insert(entity);
        return entity;
    }

    @Override
    public FsEntity watermark(String customType, String path) {
        GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, path);
        request.setProcess(WATERMARK_STYLE);
        OSSObject object = client.getObject(request);

        FsEntity entity = repository.selectByPath(path);
        entity.setCustomType(customType);
        entity.setPath(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase() + entity.getFileType().getSuffix());

        client.putObject(new PutObjectRequest(BUCKET_NAME, entity.getPath(), object.getObjectContent()));
        repository.insert(entity);
        return entity;
    }
}
