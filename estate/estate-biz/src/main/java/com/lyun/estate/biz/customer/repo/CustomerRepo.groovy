package com.lyun.estate.biz.customer.repo

import com.lyun.estate.biz.customer.entity.Customer
import com.lyun.estate.biz.customer.entity.CustomerTiny
import com.lyun.estate.biz.customer.repo.provider.CustomerSqlProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository

/**
 * Created by Jeffrey on 2017-03-07.
 */
@Repository
interface CustomerRepo {

    @Select('SELECT * FROM t_customer WHERE company_id = #{companyId} AND mobile = #{mobile} AND is_deleted = FALSE')
    Customer findCustomerByCompanyIdAndMobile(@Param('companyId') Long companyId, @Param('mobile') String mobile)

    @Select('SELECT * FROM t_customer WHERE id = #{id}')
    Customer findOne(Long id)

    @InsertProvider(type = CustomerSqlProvider.class, method = 'save')
    @Options(useGeneratedKeys = true)
    int save(Customer customer)

    @Select('SELECT id, name, source, mobile FROM t_customer WHERE id = #{id}')
    CustomerTiny getTiny(Long id)
}