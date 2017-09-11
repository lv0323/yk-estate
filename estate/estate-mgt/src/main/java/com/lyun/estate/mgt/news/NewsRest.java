package com.lyun.estate.mgt.news;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.lyun.estate.biz.news.define.NewsDefine;
import com.lyun.estate.biz.news.entity.News;
import com.lyun.estate.biz.news.entity.NewsDTO;
import com.lyun.estate.biz.news.service.NewsService;
import com.lyun.estate.mgt.supports.CommonResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Jeffrey on 2017-07-05.
 */
@RestController
@RequestMapping("api/news")
public class NewsRest {

    @Autowired
    private NewsService newsService;

    @PostMapping("create")
    public NewsDTO create(@RequestParam String title,
                          @RequestParam NewsDefine.Category category,
                          @RequestParam NewsDefine.Scope scope,
                          @RequestParam(required = false) String summary,
                          @RequestParam(required = false) String newsUrl,
                          @RequestParam(required = false) MultipartFile newsImage) throws IOException {

        if (newsImage != null) {
            String originalFilename = newsImage.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            try (InputStream inputStream = newsImage.getInputStream()) {
                return newsService.createNews(new News()
                        .setTitle(title)
                        .setCategory(category)
                        .setScope(scope).setSummary(summary)
                        .setNewsUrl(newsUrl), inputStream, suffix);
            }
        } else {
            return newsService.createNews(new News()
                    .setTitle(title)
                    .setCategory(category)
                    .setScope(scope).setSummary(summary)
                    .setNewsUrl(newsUrl), null, null);
        }

    }

    @GetMapping("list")
    public List<NewsDTO> getNews(@RequestParam(required = false) NewsDefine.Scope scope,
                                 @RequestParam(required = false) NewsDefine.Category category,
                                 @RequestHeader("X-PAGING") PageBounds pageBounds) {
        return newsService.getNews(scope, category, pageBounds);
    }

    @PostMapping("delete")
    public CommonResp getNews(@RequestParam Long newsId) {
        return newsService.delete(newsId) ? CommonResp.succeed() : CommonResp.failed();
    }

    @PostMapping("updateInfo")
    public NewsDTO update(@RequestParam Long newsId,
                          @RequestParam String title,
                          @RequestParam NewsDefine.Category category,
                          @RequestParam NewsDefine.Scope scope,
                          @RequestParam(required = false) String summary,
                          @RequestParam(required = false) String newsUrl) {
        return newsService.updateInfo(new News()
                .setId(newsId)
                .setTitle(title)
                .setCategory(category)
                .setScope(scope)
                .setSummary(summary)
                .setNewsUrl(newsUrl));
    }

    @PostMapping("updateImage")
    public NewsDTO updateImage(@RequestParam Long newsId,
                               @RequestParam MultipartFile newsImage) throws IOException {
        String originalFilename = newsImage.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        try (InputStream inputStream = newsImage.getInputStream()) {
            return newsService.updateImage(newsId, inputStream, suffix);
        }
    }

}
