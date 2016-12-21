package com.lyun.estate.rest.test;

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
    @ResponseBody
    public String string() {
        return "string";
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
