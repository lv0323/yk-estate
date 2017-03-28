package com.lyun.estate.biz.news.service

import com.lyun.estate.biz.file.service.FileService
import com.lyun.estate.biz.news.entity.News
import com.lyun.estate.biz.news.entity.NewsDTO
import com.lyun.estate.biz.news.repo.NewsRepo
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Jeffrey on 2017-03-28.
 */
@Service
class NewsService {

    @Autowired
    NewsRepo newsRepo

    @Autowired
    FileService fileService

    List<NewsDTO> getTopNews() {
        List<NewsDTO> result = new ArrayList<>()
        List<News> newsList = newsRepo.topNews(5)
        newsList.forEach({
            result.add(mapToNewsDTO(it))
        })
        result
    }

    NewsDTO mapToNewsDTO(News news) {
        NewsDTO dto = new NewsDTO()
        BeanUtils.copyProperties(news, dto, 'class', 'metaClass')
        if (Objects.nonNull(dto.imageId)) {
            dto.setImageURI(Optional.ofNullable(fileService.findOne(dto.imageId)).map({ it.getFileURI() }).orElse(null))
        }
        dto
    }
}
