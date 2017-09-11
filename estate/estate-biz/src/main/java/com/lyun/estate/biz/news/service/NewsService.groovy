package com.lyun.estate.biz.news.service

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.lyun.estate.biz.file.def.CustomType
import com.lyun.estate.biz.file.def.FileProcess
import com.lyun.estate.biz.file.def.FileType
import com.lyun.estate.biz.file.entity.FileDescription
import com.lyun.estate.biz.file.service.FileService
import com.lyun.estate.biz.news.define.NewsDefine
import com.lyun.estate.biz.news.entity.News
import com.lyun.estate.biz.news.entity.NewsDTO
import com.lyun.estate.biz.news.repo.NewsRepo
import com.lyun.estate.biz.support.def.DomainType
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.stream.Collectors

/**
 * Created by Jeffrey on 2017-03-28.
 */
@Service
class NewsService {

    @Autowired
    NewsRepo newsRepo

    @Autowired
    FileService fileService

    List<NewsDTO> getAppTopNews() {
        List<NewsDTO> result = new ArrayList<>()
        List<News> newsList = newsRepo.getNews(NewsDefine.Scope.APP, null, new PageBounds().setLimit(5))
        newsList.forEach({
            result.add(mapToNewsDTO(it))
        })
        result
    }

    List<NewsDTO> getNews(NewsDefine.Scope scope, NewsDefine.Category category, PageBounds pageBounds) {
        newsRepo.getNews(scope, category, pageBounds).
                stream().
                map({ t -> this.mapToNewsDTO(t) }).
                collect(Collectors.toList())
    }


    @Transactional
    NewsDTO createNews(News news, InputStream imageIS, String imageSuffix) {
        newsRepo.save(news)
        if (imageIS != null) {
            FileDescription fileDescription = fileService.save(new FileDescription()
                    .setOwnerId(news.getId())
                    .setOwnerType(DomainType.NEWS)
                    .setCustomType(CustomType.NEWS_IMG)
                    .setFileType(FileType.IMAGE)
                    .setFileProcess(FileProcess.NONE.flag),
                    imageIS, imageSuffix)

            newsRepo.updateNonNull(new News().setId(news.id).setImageId(fileDescription.id))
        }
        return findOne(news.id)
    }

    boolean delete(Long newsId) {
        def one = newsRepo.findOne(newsId)
        if (one != null) {
            if (!Objects.equals(one.getIsDeleted(), true)) {
                return newsRepo.delete(newsId) > 0
            }
            return true
        }
        return false
    }

    NewsDTO updateInfo(News news) {
        newsRepo.updateInfo(news)
        return findOne(news.id)
    }

    NewsDTO updateImage(Long newsId, InputStream imageIS, String imageSuffix) {
        FileDescription fileDescription = fileService.save(new FileDescription()
                .setOwnerId(newsId)
                .setOwnerType(DomainType.NEWS)
                .setCustomType(CustomType.NEWS_IMG)
                .setFileType(FileType.IMAGE)
                .setFileProcess(FileProcess.NONE.flag),
                imageIS, imageSuffix)

        newsRepo.updateNonNull(new News().setId(newsId).setImageId(fileDescription.id))
        return findOne(newsId)
    }


    NewsDTO findOne(Long id) {
        Optional.ofNullable(newsRepo.findOne(id)).map({ t -> this.mapToNewsDTO(t) }).orElse(null)
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
