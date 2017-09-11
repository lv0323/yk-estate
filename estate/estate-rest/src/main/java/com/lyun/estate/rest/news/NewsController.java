package com.lyun.estate.rest.news;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.news.define.NewsDefine;
import com.lyun.estate.biz.news.entity.NewsDTO;
import com.lyun.estate.biz.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jeffrey on 2017-03-28.
 */
@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("top")
    public List<NewsDTO> appTopNews() {
        return newsService.getAppTopNews();
    }

    @GetMapping("list")
    public List<NewsDTO> getNews(@RequestParam(required = false) NewsDefine.Scope scope,
                                 @RequestParam(required = false) NewsDefine.Category category,
                                 @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return newsService.getNews(scope, category, pageBounds);
    }
}
