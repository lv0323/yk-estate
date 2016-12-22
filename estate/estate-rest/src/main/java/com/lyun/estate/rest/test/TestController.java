package com.lyun.estate.rest.test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.core.exception.EstateException;
import com.lyun.estate.core.exception.ExCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lyun.estate.biz.Oss;
import com.lyun.estate.biz.filesystem.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final Oss oss;
    private final FsRepository fsRepository;
    private final AliyunFileSystem fileSystem;

    @Autowired
    public TestController(Oss oss, FsRepository fsRepository, AliyunFileSystem fileSystem) {
        this.oss = oss;
        this.fsRepository = fsRepository;
        this.fileSystem = fileSystem;
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
            FsEntity entity = fileSystem.create(OwnerType.TEST, 0, "Ori", FileType.IMAGE, new FileInputStream(new File(file)));
            fileSystem.watermark("WaterMark", entity.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("fs")
    public void fs() {
        FsEntity entity = new FsEntity();
        entity.setOwnerType(OwnerType.TEST);
        entity.setOwnerId(123L);
        entity.setCustomType("BBB");
        entity.setFileType(FileType.IMAGE);
        entity.setTarget("Aliyun");
        entity.setPath("XXXXXXXXXXXX");
        fsRepository.insert(entity);
    }
}
