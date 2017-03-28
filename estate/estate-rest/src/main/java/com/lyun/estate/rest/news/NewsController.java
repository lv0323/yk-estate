package com.lyun.estate.rest.news;

import com.lyun.estate.biz.news.entity.NewsDTO;
import com.lyun.estate.biz.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public List<NewsDTO> topNews() {
        return newsService.getTopNews();
    }
}
