package com.lyun.estate.rest.keyword;

import com.google.common.collect.Lists;
import com.lyun.estate.biz.keyword.entity.KeywordBean;
import com.lyun.estate.biz.keyword.service.KeywordService;
import com.lyun.estate.biz.spec.common.DomainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jeffrey on 2017-01-10.
 */
@RestController
@RequestMapping("/keyword")
public class KeywordController {
    @Autowired
    private KeywordService keywordService;

    @GetMapping("/contain")
    List<KeywordBean> findContain(@RequestParam String keyword, @RequestParam(required = false) Integer limit) {
        return keywordService.findContain(keyword, Lists.newArrayList(DomainType.values()), limit);
    }

    @GetMapping("/nameMatch")
    List<KeywordBean> findNameMatch(@RequestParam String keyword, @RequestParam(required = false) Integer limit) {
        return keywordService.findNameMatch(keyword, Lists.newArrayList(DomainType.values()), limit);
    }
}