package com.lyun.estate.biz.dianping.repo;

import com.lyun.estate.biz.dianping.entity.Comment;
import com.lyun.estate.biz.dianping.entity.Corp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by localuser on 2017/6/13.
 */
@Repository
public interface DianpingRepo {
    @Insert("insert t_op_dianping_corp(name, status) values #{values}")
    int insertCorps(@Param("values")String values);

    @Select("SELECT id, " +
            "name, " +
            "positive_count as positiveCount, " +
            "negative_count as negativeCount," +
            "visit_count as visitCount, " +
            "creater_id as createrId, " +
            "is_deleted as deleted, " +
            "comment_count as commentCount " +
            "FROM t_op_dianping_corp where id=#{id}")
    Corp getCorp(long id);

    @Select("SELECT id, " +
            "name, " +
            "status, " +
            "positive_count as positiveCount, " +
            "negative_count as negativeCount, " +
            "visit_count as visitCount, " +
            "comment_count as commentCount " +
            "FROM t_op_dianping_corp "+
            "order by create_time desc " +
            "WHERE status =#{status} "+
            "offset #{offset} limit #{limit}"
    )
    List<Corp> getCorps(@Param("status")String status, @Param("offset")int offset, @Param("limit")int limit);


    @Select("select count(id) from t_op_dianping_corp where status=#{status}")
    int getCorpCount(@Param("status")String status);

    @Select("SELECT " +
            "id, " +
            "name, " +
            "status, " +
            "positive_count as positiveCount, " +
            "negative_count as negativeCount, " +
            "visit_count as visitCount, " +
            "comment_count as commentCount " +
            "FROM t_op_dianping_corp " +
            "order by create_time desc " +
            "WHERE name like '%'||#{name}||'%'")
    List<Corp> searchCorps(@Param("name")String name);

    @Update("update t_op_dianping_corp set status = 'active' where id = #{corpId} ")
    int activeCorp(@Param("corpId")long corpId);

    @Update("update t_op_dianping_corp set status = 'suspend' where id = #{corpId} ")
    int suspendCorp(@Param("corpId")long corpId);

    @Select("select count(id) from t_op_dianping_corp where id = #{corpId}")
    int existCorp(@Param("corpId")long corpId);

    @Update("update from t_op_dianping_corp set is_deleted = true where id = #{corpId} ")
    int deleteCorp(@Param("corpId")long corpId);

    @Select("SELECT " +
            "c.id, " +
            "c.content, " +
            "c.positive_count as positiveCount, " +
            "c.create_time as createTime, " +
            "c.tags, " +
            "u.id as userId, " +
            "u.nicky, " +
            "u.avatar " +
            "from t_op_dianping_comment as c left join t_op_dianping_user as u " +
            "on c.user_id = u.id " +
            "WHERE c.corp_id = #{id} " +
            "ORDER BY c.create_time desc " +
            "offset #{offset} limit #{limit}")
    List<Comment> getCorpComments(@Param("id") long id, @Param("offset") long offset, @Param("limit") long limit);

    @Select("select count(id) from t_op_dianping_comment where corp_id=#{corpId}")
    int getCorpCommentCount(@Param("corpId")long corpId);

    @Delete("delete from t_op_dianping_comment where corp_id = #{corpId} and id = #{commentId}")
    int deleteCorpComment(@Param("corpId")long corpId, @Param("commentId")long commentId);

    @Update("update t_op_dianping_corp set comment_count = comment_count - 1 where id=#{corpId}")
    int decreaseCommentCount(@Param("corpId")long corpId);


    @Select("select count(id) from t_op_dianping_comment where id = #{commentId}")
    int countComment(@Param("commentId")long commentId);

    @Update("update from t_op_dianping_comment set corp_id = #{corpIdTo} where corp_id = #{corpIdFrom} ")
    int moveCorpComment(@Param("corpIdTo")long corpId, @Param("corpIdFrom")long corpIdFrom);

    @Update("update from t_op_dianping_corp_visit set corp_id = #{corpIdTo} where corp_id = #{corpIdFrom} ")
    int moveCorpVisit(@Param("corpIdTo")long corpId, @Param("corpIdFrom")long corpIdFrom);

    @Update("update from t_op_dianping_judgement set corp_id = #{corpIdTo} where corp_id = #{corpIdFrom} ")
    int moveCorpJudgement(@Param("corpIdTo")long corpId, @Param("corpIdFrom")long corpIdFrom);

    @Update("update from t_op_dianping_corp set corp_id = #{corpIdTo} where corp_id = #{corpIdFrom} ")
    int updateCorpCount(@Param("corpId")long corpId,
                        @Param("visitCount")long visitCount,
                        @Param("positiveCount")long positiveCount,
                        @Param("negativeCount")long negativeCount);



















}
