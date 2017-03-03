package com.lyun.estate.mgt.manage;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.biz.support.def.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jeffrey on 2017-03-03.
 */
@RestController("android-update")
public class AndroidUpdateRest {
    @Autowired
    private FileService fileService;

    @PostMapping("")
    public FileDescription uploadAPK(@RequestParam MultipartFile apk) throws IOException {
        try (InputStream inputStream = apk.getInputStream()) {
            return fileService.save(new FileDescription()
                            .setFileType(FileType.APK)
                            .setOwnerId(1L).setOwnerType(DomainType.USER)
                            .setCustomType(CustomType.APK).setFileProcess(FileProcess.NONE.getFlag()), inputStream,
                    apk.getOriginalFilename().substring(apk.getOriginalFilename().lastIndexOf('.')));
        }
    }
}
