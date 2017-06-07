package com.lyun.estate.op.dianping.comment.repo;

import com.lyun.estate.op.dianping.comment.entity.RawComment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
@Repository
public interface CommentRepo {
//    @Select("SELECT c.id, c.content, c.positive_count as positiveCount, c.create_time as createTime,\n" +
//            "  u.id as userId, u.nicky, u.avatar,\n" +
//            "  tag.name as tag\n" +
//            "  FROM\n" +
//            "    (\n" +
//            "        ( t_op_dianping_comment as c\n" +
//            "          RIGHT JOIN\n" +
//            "          t_op_dianping_user as u\n" +
//            "          ON c.user_id = u.id\n" +
//            "        )\n" +
//            "        RIGHT JOIN\n" +
//            "        t_op_dianping_comment_tag_map as tm\n" +
//            "        ON tm.comment_id = c.id\n" +
//            "    )\n" +
//            "    RIGHT JOIN\n" +
//            "    t_op_dianping_tag as tag\n" +
//            "    on tag.id = tm.tag_id\n" +
//            "\n" +
//            "WHERE c.corp_id = #{id}\n" +
//            "ORDER BY c.create_time desc;")
//    List<RawComment> getCorpComments(long id);

    @Select("SELECT c.id, c.content, c.positive_count as positiveCount, c.create_time as createTime, c.tags,\n" +
            "    u.id as userId, u.nicky, u.avatar\n" +
            "from t_op_dianping_comment as c RIGHT JOIN t_op_dianping_user as u\n" +
            "    on c.user_id = u.id\n" +
            "WHERE c.corp_id = #{id}\n" +
            "ORDER BY c.create_time desc " +
            "offset #{offset} limit #{limit}" +
            ";")
    List<RawComment> getCorpComments(@Param("id") long id, @Param("offset") long offset, @Param("limit") long limit);

    @Select("SELECT  comment_id from t_op_dianping_comment_like_map WHERE user_id = #{userId}")
    List<Long> getMyLikes(@Param("userId") long userId);


    @Select("SELECT ct.id, ct.content, ct.positive_count as positiveCount, ct.create_time as createTime, ct.tags,\n" +
            "    corp.id as corpId, corp.name as corpName\n " +
            "from t_op_dianping_comment as ct RIGHT JOIN t_op_dianping_corp as corp\n " +
            "on ct.corp_id = corp.id\n " +
            "WHERE ct.user_id = #{userId} " +
            "ORDER BY ct.create_time desc " +
            "offset #{offset} limit #{limit};")
    List<RawComment> myComments(@Param("userId") long userId, @Param("offset") long offset, @Param("limit") long limit);


    @Insert("INSERT into t_op_dianping_comment(user_id, corp_id, shopfront, content, tags) " +
            "VALUES (#{userId},#{corpId},#{shopfront},#{content}, #{tags})")
    int create(@Param("userId") long userId,
               @Param("corpId") long corpId,
               @Param("tags") String tags,
               @Param("shopfront") String shopfront,
               @Param("content") String content);

    @Select("select count(*) from t_op_dianping_comment_like_map where comment_id =#{commentId} and user_id=#{userId}")
    int liked(@Param("commentId") long commentId, @Param("userId") long userId);

    @Insert("insert into t_op_dianping_comment_like_map values(#{commentId}, #{userId})")
    int like(@Param("commentId") long commentId, @Param("userId") long userId);

    @Delete("delete from t_op_dianping_comment_like_map where comment_id=#{commentId} and user_id=#{userId}")
    int cancelLike(@Param("commentId") long commentId, @Param("userId") long userId);

    @Update("update t_op_dianping_comment set positive_count = positive_count + 1 where id=#{commentId} and user_id=#{userId}")
    int increaseLike(@Param("commentId") long commentId, @Param("userId") long userId);

    @Update("update t_op_dianping_comment set positive_count = positive_count - 1 where id=#{commentId} and user_id=#{userId}")
    int descreaseLike(@Param("commentId") long commentId, @Param("userId") long userId);

}
