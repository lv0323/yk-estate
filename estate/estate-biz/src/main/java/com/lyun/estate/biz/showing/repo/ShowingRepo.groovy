package com.lyun.estate.biz.showing.repo

import com.lyun.estate.biz.showing.entity.Showing
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Repository
interface ShowingRepo {

    @Insert('INSERT INTO t_showing (customer_id, fang_id, company_id, department_id, employee_id, process) VALUES (#{customerId}, #{fangId}, #{companyId}, #{departmentId}, #{employeeId}, #{process}) ')
    @Options(useGeneratedKeys = true)
    int save(Showing showing)

    @Select('SELECT * FROM t_showing WHERE id = #{id}')
    Showing findOne(Long id)
}
