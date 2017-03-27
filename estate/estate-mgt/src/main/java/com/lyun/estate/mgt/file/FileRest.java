package com.lyun.estate.mgt.file;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.mgt.context.MgtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2017-03-27.
 */
@RestController
@RequestMapping("api/file")
public class FileRest {

    @Autowired
    FileService fileService;

    @Autowired
    MgtContext mgtContext;

    Logger logger = LoggerFactory.getLogger(FileRest.class);

    @PostMapping("create")
    public FileDescription save(@RequestParam Long ownerId,
                                @RequestParam DomainType ownerType,
                                @RequestParam CustomType customType,
                                @RequestParam FileType fileType,
                                @RequestParam FileProcess process,
                                @RequestParam MultipartFile image) throws IOException {

        logger.info("{},ownerId:{},ownerType:{},customType:{},fileType:{},process:{}",
                mgtContext.getOperator().getId(),
                ownerId, ownerType, customType, fileType, process);
        FileDescription description = new FileDescription()
                .setOwnerId(ownerId)
                .setOwnerType(ownerType)
                .setCustomType(customType)
                .setFileType(fileType)
                .setFileProcess(process.getFlag());


        try (InputStream inputStream = image.getInputStream()) {
            return fileService.save(description,
                    inputStream,
                    image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")));
        }

    }
}
