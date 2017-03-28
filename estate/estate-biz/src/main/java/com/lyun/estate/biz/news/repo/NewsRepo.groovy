package com.lyun.estate.biz.news.repo

import com.lyun.estate.biz.news.entity.News
import org.apache.ibatis.annotations.Select

/**
 * Created by Jeffrey on 2017-03-28.
 */
interface NewsRepo {
    @Select("SELECT * FROM t_news WHERE is_deleted = false ORDER BY id DESC LIMIT #{limit}")
    List<News> topNews(Integer limit)
}