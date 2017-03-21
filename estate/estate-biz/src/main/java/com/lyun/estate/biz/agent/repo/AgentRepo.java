package com.lyun.estate.biz.agent.repo;

import com.lyun.estate.biz.agent.entity.Agent;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Jeffrey on 2017-03-21.
 */
@Repository
public interface AgentRepo {

    @Select("SELECT e.id as employee_id, e.name, e.gender, e.mobile , e.open_contact, e.avatar_id FROM t_fang_info_owner fio " +
            "LEFT JOIN t_employee e ON fio.employee_id = e.id WHERE fio.is_deleted = FALSE AND fio.fang_id = #{fangId} LIMIT 1;")
    Agent getFangAgent(Long fangId);

    @Select("SELECT  e.id AS employee_id,  e.name,  e.gender,  e.mobile,  e.open_contact,  e.avatar_id\n" +
            "FROM t_employee e WHERE e.id IN (SELECT DISTINCT fio.employee_id " +
            "       FROM t_fang f LEFT JOIN t_fang_info_owner fio ON f.id = fio.fang_id " +
            "       WHERE f.xiao_qu_id = #{xiaoQuId} AND f.is_deleted = FALSE AND fio.is_deleted = FALSE LIMIT 3)")
    List<Agent> getXiaoQuAgents(Long xiaoQuId);
}
