package com.lyun.estate.rest.test;

import com.lyun.estate.biz.Oss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@RequestMapping("/test")
public class TestController {

    private final Oss oss;

    @Autowired
    public TestController(Oss oss) {
        this.oss = oss;
    }

    @GetMapping(value = "/string")
    @ResponseBody
    public String string() {
        return "string";
    }

    @RequestMapping("oss/{file}")
    public String oss(@PathVariable String file) {
        file += ".jpg";
        return oss.upload(file, new File("D:\\" + file));
    }
}
