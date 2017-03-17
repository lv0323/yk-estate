package com.lyun.estate.biz.contract.repo

import com.github.miemiedev.mybatis.paginator.domain.PageBounds
import com.github.miemiedev.mybatis.paginator.domain.PageList
import com.lyun.estate.biz.contract.def.ContractDefine
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractDTO
import com.lyun.estate.biz.contract.entity.ContractSelector
import com.lyun.estate.biz.contract.repo.provider.ContractSqlProvider
import org.apache.ibatis.annotations.*
import org.springframework.stereotype.Repository

/**
 * Created by Jeffrey on 2017-03-09.
 */
@Repository
interface ContractRepo {

    @Options(useGeneratedKeys = true)
    @InsertProvider(type = ContractSqlProvider, method = 'save')
    int save(Contract contract)

    @Select("SELECT * FROM t_contract WHERE id =#{id}")
    Contract findOne(long id)

    @Update("UPDATE t_contract SET process = #{process},close_time = now(), update_time = now() WHERE id = #{id} AND process = 'CREATED' AND is_deleted = FALSE ")
    int close(@Param("id") long id, @Param("process") ContractDefine.Process process)

    @SelectProvider(type = ContractSqlProvider, method = 'list')
    PageList<ContractDTO> list(ContractSelector selector, PageBounds pageBounds)
}