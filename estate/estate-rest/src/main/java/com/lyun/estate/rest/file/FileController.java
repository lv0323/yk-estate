package com.lyun.estate.rest.file;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.service.OssFileService;
import com.lyun.estate.biz.spec.common.DomainType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class FileController {

    private final OssFileService fileService;

    public FileController(OssFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public Object file(@RequestParam Long ownerId, @RequestParam DomainType ownerType, @RequestParam(required = false) CustomType customType) {
        FileProcess process = null;
        if (customType == CustomType.HUXING)
            process = FileProcess.WATERMARK;
        return fileService.find(ownerId, ownerType, customType, process);
    }
}