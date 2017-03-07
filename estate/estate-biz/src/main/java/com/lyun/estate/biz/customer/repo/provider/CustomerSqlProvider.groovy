package com.lyun.estate.biz.customer.repo.provider

import com.lyun.estate.biz.customer.entity.Customer

/**
 * Created by Jeffrey on 2017-03-07.
 */
class CustomerSqlProvider {

    String save(Customer customer) {
        'INSERT INTO t_customer (name, source, mobile, company_id, department_id, employee_id, a_mobile, b_mobile, qq, we_chat, email, identity_source, identity_no, gender, resident_address, status, level, purpose, time_limits) ' +
                'VALUES (#{name}, #{source}, #{mobile}, #{companyId}, #{departmentId}, #{employeeId}, #{aMobile}, #{bMobile}, #{qq}, #{weChat}, #{email}, #{identitySource}, #{identityNo}, #{gender}, #{residentAddress}, #{status}, #{level}, #{purpose}, #{timeLimits})'
    }
}
