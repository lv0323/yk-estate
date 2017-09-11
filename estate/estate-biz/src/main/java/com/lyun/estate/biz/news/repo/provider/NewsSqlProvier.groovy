package com.lyun.estate.biz.news.repo.provider

import com.lyun.estate.biz.news.entity.News
import com.lyun.estate.core.repo.SQL
import org.springframework.util.StringUtils

class NewsSqlProvier {
    String insert(News news) {
        return new SQL().INSERT_INTO("t_news")
                .VALUES_IF("title", "#{title}", !StringUtils.isEmpty(news.getTitle()))
                .VALUES_IF("category", "#{category}", Objects.nonNull(news.getCategory()))
                .VALUES_IF("scope", "#{scope}", Objects.nonNull(news.getScope()))
                .VALUES_IF("image_id", "#{imageId}", Objects.nonNull(news.getImageId()))
                .VALUES_IF("news_url", "#{newsUrl}", !StringUtils.isEmpty(news.getNewsUrl()))
                .VALUES_IF("summary", "#{summary}", !StringUtils.isEmpty(news.getSummary()))
                .toString().toString()
    }

    String updateNonNull(News news) {
        return new SQL()
                .UPDATE("t_news")
                .SET_IF("title = #{title}", !StringUtils.isEmpty(news.getTitle()))
                .SET_IF("category = #{category}", Objects.nonNull(news.getCategory()))
                .SET_IF("scope = #{scope}", Objects.nonNull(news.getScope()))
                .SET_IF("image_id = #{imageId}", Objects.nonNull(news.getImageId()))
                .SET_IF("news_url = #{newsUrl}", !StringUtils.isEmpty(news.getNewsUrl()))
                .SET_IF("summary = #{summary}", !StringUtils.isEmpty(news.getSummary()))
                .SET("update_time = now()")
                .WHERE("id = #{id}")
                .toString();
    }

    String updateInfo(News news) {
        return new SQL()
                .UPDATE("t_news")
                .SET("title = #{title}")
                .SET("category = #{category}")
                .SET("scope = #{scope}")
                .SET("news_url = #{newsUrl}")
                .SET("summary = #{summary}")
                .SET("update_time = now()")
                .WHERE("id = #{id}")
                .toString();
    }

    String getNews(Map<String, Object> params) {
        return new SQL().SELECT("*")
                .FROM("t_news")
                .WHERE_IF("category = #{category}", Objects.nonNull(params.get("category")))
                .WHERE_IF("scope = #{scope}", Objects.nonNull(params.get("scope")))
                .WHERE("is_deleted = false")
                .toString()
    }
}
