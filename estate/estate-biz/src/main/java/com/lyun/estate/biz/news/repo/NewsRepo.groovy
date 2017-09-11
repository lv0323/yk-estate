package com.lyun.estate.biz.news.repo

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.lyun.estate.biz.news.define.NewsDefine
import com.lyun.estate.biz.news.entity.News
import com.lyun.estate.biz.news.repo.provider.NewsSqlProvier
import org.apache.ibatis.annotations.*

/**
 * Created by Jeffrey on 2017-03-28.
 */
interface NewsRepo {
    @Select("SELECT * FROM t_news WHERE scope = 'APP' AND is_deleted = false ORDER BY id DESC LIMIT #{limit}")
    List<News> getAppTopNews(Integer limit)

    @Select("SELECT * FROM t_news WHERE id = #{id} AND is_deleted = false")
    News findOne(long id)

    @SelectProvider(type = NewsSqlProvier.class, method = "getNews")
    List<News> getNews(@Param("scope") NewsDefine.Scope scope,
                       @Param("category") NewsDefine.Category category, PageBounds pageBounds)

    @UpdateProvider(type = NewsSqlProvier.class, method = "updateNonNull")
    int updateNonNull(News news)

    @UpdateProvider(type = NewsSqlProvier.class, method = "updateInfo")
    int updateInfo(News news)

    @InsertProvider(type = NewsSqlProvier.class, method = "insert")
    @Options(useGeneratedKeys = true)
    int save(News news)

    @Update("UPDATE t_news SET is_deleted = TRUE WHERE id =#{newsId}")
    int delete(long newsId)
}