package com.lyun.estate.rest.test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.file.def.*;
import com.lyun.estate.biz.file.entity.FileEntity;
import com.lyun.estate.biz.file.repository.FileRepository;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.core.exception.EstateException;
import com.lyun.estate.core.exception.ExCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final FileRepository fileRepository;
    private final FileService fileService;
    private final ApplicationContext applicationContext;

    @Autowired
    public TestController(FileRepository fileRepository, FileService fileService, ApplicationContext applicationContext) {
        this.fileRepository = fileRepository;
        this.fileService = fileService;
        this.applicationContext = applicationContext;
    }

    @GetMapping(value = "/string")
    public String string() {
        return "string";
    }

    @GetMapping(value = "/error")
    public String error() {
        throw new EstateException(ExCode.PARAM_ILLEGAL, "用户名", "1234");
    }

    @GetMapping(value = "/paginator")
    public PageBounds page(PageBounds pageBounds) {
        return pageBounds;
    }

    @RequestMapping("oss/{file}")
    public void oss(@PathVariable String file) {
        file = "D:/" + file + ".jpg";
        try {
            FileEntity entity = new FileEntity();
            entity.setOwnerType(OwnerType.VILLAGE);
            entity.setOwnerId(123L);
            entity.setCustomType(CustomType.SHIJING);
            entity.setFileType(FileType.IMAGE);
            entity = fileService.save(entity, new FileInputStream(new File(file)), ".jpg", FileProcess.WATERMARK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("context")
    public String context() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String name : applicationContext.getBeanDefinitionNames()) {
            stringBuilder.append(name).append("\n");
            System.out.println(name);
        }
        return stringBuilder.toString();
    }

    @RequestMapping("fs")
    public void fs() {
        FileEntity entity = new FileEntity();
        entity.setOwnerType(OwnerType.VILLAGE);
        entity.setOwnerId(123L);
        entity.setCustomType(CustomType.SHIJING);
        entity.setFileType(FileType.IMAGE);
        entity.setTarget(Target.OSS);
        entity.setPath("XXXXXXXXXXXX");
        fileRepository.insert(entity);
    }
}
