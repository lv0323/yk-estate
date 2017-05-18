package com.lyun.estate.op.corp.repo;

import com.lyun.estate.op.corp.entity.Corp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by localuser on 2017/5/15.
 */
@Repository
public interface CorpRepo {

    @Select("SELECT id, " +
            "name, " +
            "positive_count as positiveCount, " +
            "negative_count as negativeCount," +
            "visit_count as visitCount, " +
            "creater_id as createrId, " +
            "is_deleted as deleted, " +
            "comment_count as commentCount " +
            "FROM t_op_dianping_corp WHERE name like '%'||#{name}||'%'")
    List<Corp> search(String name);

//    @Select("SELECT count(map.tag_id) as count, tag.name\n" +
//            "  FROM\n" +
//            "    (t_op_dianping_comment as cmt\n" +
//            "      RIGHT JOIN\n" +
//            "      t_op_dianping_comment_tag_map as map\n" +
//            "      on cmt.id = map.comment_id\n" +
//            "    )\n" +
//            "    RIGHT JOIN\n" +
//            "    t_op_dianping_tag as tag\n" +
//            "    ON map.tag_id = tag.id\n" +
//            "\n" +
//            "WHERE cmt.corp_id = 3\n" +
//            "GROUP BY tag.name\n" +
//            "ORDER BY count desc\n" +
//            "LIMIT #{id}")
//    List<TagCount> getTags(long id);

    @Select("select tags from t_op_dianping_comment where corp_id=#{id}")
    List<String> getTagStrs(long id);

    @Select("SELECT id, " +
            "name, " +
            "positive_count as positiveCount, " +
            "negative_count as negativeCount," +
            "visit_count as visitCount, " +
            "creater_id as createrId, " +
            "is_deleted as deleted, " +
            "comment_count as commentCount " +
            "FROM t_op_dianping_corp where id=#{id}")
    Corp get(long id);

    @Insert("INSERT into t_op_dianping_corp(name, creater_id) VALUES (#{name},#{userId})")
    int create(@Param("name") String name, @Param("userId") long userId);


    @Update("update t_op_dianping_corp set positive_count = positive_count + 1 where id=#{id}")
    void increasePositiveCount(long id);

    @Update("update t_op_dianping_corp set positive_count = positive_count - 1 where id=#{id}")
    void discreasePositiveCount(long id);

    @Update("update t_op_dianping_corp set netative_count = netative_count + 1 where id=#{id}")
    void increaseNegativeCount(long id);

    @Update("update t_op_dianping_corp set netative_count = netative_count - 1 where id=#{id}")
    void discreaseNegativeCount(long id);

    @Update("update t_op_dianping_corp set comment_count = comment_count + 1 where id=#{id}")
    void increaseCommentCount(long corpId);

    @Update("update t_op_dianping_corp set visit_count = visit_count + 1 where id=#{id}")
    void increaseVisitCount(long corpId);

    @Select("select judgement from t_op_dianping_judgement where corp_id=#{corpId} and user_id =#{userId}")
    String getJudgementStatus(@Param("corpId") long corpId, @Param("userId") long userId);

    @Insert("insert into t_op_dianping_judgement(corp_id, user_id, judgement) values(#{corpId},#{userId},#{judgement})")
    void createJudgement(@Param("corpId")long corpId, @Param("userId")long userId, @Param("judgement")String judgement);

    @Update("update t_op_dianping_judgement set judgement=#{judgement} where corp_id=#{corpId} and user_id=#{userId}")
    void updateJudgement(@Param("corpId")long corpId, @Param("userId")long userId, @Param("judgement")String judgement);

}
