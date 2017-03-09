package com.lyun.estate.biz.showing.repo

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.showing.def.ShowingDefine
import com.lyun.estate.biz.showing.entity.Showing
import com.lyun.estate.biz.showing.entity.ShowingDTO
import com.lyun.estate.biz.showing.entity.ShowingSelector
import com.lyun.estate.biz.showing.repo.provider.ShowingSqlProvider
import org.apache.ibatis.annotations.*
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

    @Update("UPDATE t_showing SET process = #{process} , update_time = now() WHERE id = #{id} AND process = 'CREATED' AND is_deleted = FALSE ")
    int close(@Param("id") Long id, @Param("process") ShowingDefine.Process process)

    @SelectProvider(type = ShowingSqlProvider.class, method = 'list')
    PageList<ShowingDTO> list(ShowingSelector selector, PageBounds pageBounds)
}
