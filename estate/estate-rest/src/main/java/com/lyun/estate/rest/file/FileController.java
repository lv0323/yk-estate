package com.lyun.estate.rest.file;

import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.service.OssFileService;
import com.lyun.estate.biz.spec.common.DomainType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file")
public class FileController {

    private final OssFileService fileService;

    public FileController(OssFileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{ownerType}/{ownerId}")
    public Object oss(@PathVariable DomainType ownerType, @PathVariable Long ownerId) {
        return fileService.find(ownerId, ownerType, null, null);
    }

    @GetMapping("{ownerType}/{ownerId}/{customType}")
    public Object oss(@PathVariable DomainType ownerType, @PathVariable Long ownerId, @PathVariable CustomType customType) {
        return fileService.find(ownerId, ownerType, customType, null);
    }

    @GetMapping("{ownerType}/{ownerId}/{customType}/{process}")
    public Object oss(@PathVariable DomainType ownerType, @PathVariable Long ownerId, @PathVariable CustomType customType, @PathVariable FileProcess process) {
        return fileService.find(ownerId, ownerType, customType, process);
    }
}
