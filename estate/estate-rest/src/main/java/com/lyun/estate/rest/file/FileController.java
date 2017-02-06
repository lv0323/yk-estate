package com.lyun.estate.rest.file;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.spec.file.service.FileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public Object file(@RequestParam Long ownerId,
                       @RequestParam DomainType ownerType,
                       @RequestParam CustomType customType) {
        FileProcess process = FileProcess.NONE;
        if (Lists.newArrayList(CustomType.HUXING, CustomType.SHIJING, CustomType.CERTIF).contains(customType)) {
            process = FileProcess.WATERMARK;
        }
        return fileService.find(ownerId, ownerType, customType, process);
    }
}
