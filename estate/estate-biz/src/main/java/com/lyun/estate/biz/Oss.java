package com.lyun.estate.biz;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Base64;

/**
 * Created by LocalUser on 2016/12/20.
 */
@Component
public class Oss {

    private static final String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAItEanE6SnLPfj";
    private static final String ACCESS_KEY_SECRET = "u3YhcVy3OIS0wezHPetlU2esDydWd8";
    private static final String BUCKET_NAME = "yk-estate-image";

    private static final String WATERMARK_STYLE = "image/watermark,image_cGFuZGEuanBn,t_50";

    private OSSClient client = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    public String upload(String key, byte[] data) {
        String originKey = "origin_" + key;
        client.putObject(new PutObjectRequest(BUCKET_NAME, originKey, new ByteArrayInputStream(data)));

        GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, originKey);
        request.setProcess(WATERMARK_STYLE);
        OSSObject object = client.getObject(request);

        client.putObject(new PutObjectRequest(BUCKET_NAME, key, object.getObjectContent()));
        return String.format("http://%s.%s/%s", BUCKET_NAME, ENDPOINT, key);
    }

    public String upload(String key, File file) {
        try {
            String originKey = "origin_" + key;
            client.putObject(new PutObjectRequest(BUCKET_NAME, originKey, new FileInputStream(file)));

            GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, originKey);
            request.setProcess(WATERMARK_STYLE);
            OSSObject object = client.getObject(request);

            client.putObject(new PutObjectRequest(BUCKET_NAME, key, object.getObjectContent()));
            return String.format("http://%s.%s/%s", BUCKET_NAME, ENDPOINT, key);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static String urlSafeBase64(String src) {
        try {
            String base64 = new String(Base64.getEncoder().encode(src.getBytes("UTF-8")));
            return base64.replaceAll("\\+", "-").replaceAll("/", "_");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
