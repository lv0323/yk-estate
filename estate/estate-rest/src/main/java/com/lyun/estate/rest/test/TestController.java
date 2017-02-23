package com.lyun.estate.rest.test;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.TokenProvider;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.def.FileProcess;
import com.lyun.estate.biz.file.def.FileType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.file.service.FileService;
import com.lyun.estate.core.supports.exceptions.EstateException;
import com.lyun.estate.core.supports.exceptions.ExCode;
import com.lyun.estate.core.supports.types.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private FileService fileService;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private KeywordService keywordService;

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
        return tokenProvider.generate("timbo", Constant.CLIENT_ID.WEB, new HashMap<String, Object>() {{
            put("a", "123");
            put("b", "456");
        }});
    }

    @PostMapping(value = "/validate")
    @CheckToken
    public String validate(@RequestHeader("auth") JWTToken token) {
        return tokenProvider.getSubject(token.getToken());
    }

    @PostMapping("oss")
    public Object oss(@RequestParam MultipartFile file) {
        try {
            FileDescription fileDescription = new FileDescription()
                    .setOwnerId(0L)
                    .setOwnerType(DomainType.XIAO_QU)
                    .setCustomType(CustomType.HU_XING)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.WATERMARK.getFlag());

            return fileService.save(fileDescription, file.getInputStream(),
                    file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.')));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("priority")
    public void priority(@RequestParam Long id) {
        fileService.setFirst(id);
    }

    @RequestMapping("context")
    public String context() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, ?> entry : applicationContext.getBeansOfType(Object.class).entrySet()) {
            sb.append(entry.getKey())
                    .append(" ====== ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return sb.toString();
    }


    @GetMapping("/keywords/contain")
    List<KeywordBean> findContain(@RequestParam Long cityId, @RequestParam String keyword,
                                  @RequestParam(required = false) Integer limit) {
        return keywordService.findContain(keyword, cityId, Lists.newArrayList(DomainType.values()), limit);
    }

    @GetMapping("/keywords/name-match")
    List<KeywordBean> findNameMatch(@RequestParam Long cityId, @RequestParam String keyword,
                                    @RequestParam(required = false) Integer limit) {
        return keywordService.findNameMatch(keyword, cityId, Lists.newArrayList(DomainType.values()), limit);
    }
}
