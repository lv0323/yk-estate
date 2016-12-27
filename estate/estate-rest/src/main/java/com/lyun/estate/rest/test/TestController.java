package com.lyun.estate.rest.test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.def.OwnerType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.file.spec.FileService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private FileService fileService;

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

    @GetMapping(value = "/token")
    public JWTToken token() {
        String token = tokenProvider.generate("timbo", "body", new HashMap<String, String>() {{
            put("a", "123");
            put("b", "456");
        }});
        return new JWTToken(token);
    }

    @PostMapping(value = "/validate")
    @CheckToken
    public HashMap validate(@RequestHeader("auth") JWTToken token) {
        return tokenProvider.getClaims(token.getToken(), "body", HashMap.class);
    }

    @PostMapping("oss")
    public Object oss(@RequestParam MultipartFile file) {
        try {
            FileDescription fileDescription = new FileDescription()
                    .setOwnerId(0L)
                    .setOwnerType(OwnerType.XIAOQU)
                    .setCustomType(CustomType.HUXING)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.WATERMARK.getFlag());

            return fileService.save(fileDescription, file.getInputStream(), file.getOriginalFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
