package com.lyun.estate.mgt.loupan;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.mgt.supports.CommonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2017-07-05.
 */
@RestController
@RequestMapping("api/loupan")
public class LouPanRest {

    @Autowired
    private FileService fileService;

    @PostMapping("video")
    public CommonResp createImage(@RequestParam Long louPanId,
                                  @RequestParam MultipartFile video) {
        if (!video.getContentType().contains("video")) {
            throw new EstateException(ExCode.FILE_NOT_VIDEO);
        }

        String originalFilename = video.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        FileDescription entity = new FileDescription().setOwnerId(louPanId).setOwnerType(DomainType.LOU_PAN)
                .setCustomType(CustomType.LP_VIDEO).setFileType(FileType.VIDEO);

        try (InputStream inputStream = video.getInputStream()) {
            fileService.save(entity, inputStream, suffix);
            return CommonResp.succeed();
        } catch (IOException e) {
            return CommonResp.failed();
        }

    }

}
