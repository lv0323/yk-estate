package com.lyun.estate.biz.contract.repo.provider

import com.google.common.base.Joiner
import com.lyun.estate.biz.contract.entity.Contract
import com.lyun.estate.biz.contract.entity.ContractSelector
import com.lyun.estate.core.repo.SQL

import static com.lyun.estate.core.repo.SqlSupport.hasNotNullElement
import static java.util.Objects.nonNull

/**
 * Created by Jeffrey on 2017-03-09.
 */
class ContractSqlProvider {

    String save(Contract contract) {
        new SQL()
                .INSERT_INTO('t_contract')
                .VALUES('fang_id', '#{fangId}')
                .VALUES('company_id', '#{companyId}')
                .VALUES('department_id', '#{departmentId}')
                .VALUES('employee_id', '#{employeeId}')
                .VALUES('house_type', '#{houseType}')
                .VALUES('certif_address', '#{certifAddress}')
                .VALUES('certif_no', '#{certifNo}')
                .VALUES('estate_area', '#{estateArea}')
                .VALUES('price', '#{price}')
                .VALUES('price_unit', '#{priceUnit}')
                .VALUES('assignor_name', '#{assignorName}')
                .VALUES('assignor_mobile', '#{assignorMobile}')
                .VALUES('assignor_id_source', '#{assignorIdSource}')
                .VALUES('assignor_id_no', '#{assignorIdNo}')
                .VALUES('assignee_name', '#{assigneeName}')
                .VALUES('assignee_mobile', '#{assigneeMobile}')
                .VALUES('assignee_id_source', '#{assigneeIdSource}')
                .VALUES('assignee_id_no', '#{assigneeIdNo}')
                .VALUES('note', '#{note}')
                .VALUES('biz_type', '#{bizType}')
                .VALUES('type', '#{type}')
                .VALUES('process', '#{process}')
                .toString()
    }

    String list(ContractSelector selector) {
        SQL sql = new SQL().SELECT("c.*, d.name AS department_name, e.name AS employee_name")
                .FROM(" t_contract c")
                .LEFT_OUTER_JOIN("t_department d on c.department_id = d.id")
                .LEFT_OUTER_JOIN("t_employee e on c.employee_id = e.id")
                .WHERE_IF("c.fang_id = #{fangId}", nonNull(selector.getFangId()))
                .WHERE_IF("c.process = #{process}", nonNull(selector.getProcess()))
                .WHERE_IF("c.type = #{type}", nonNull(selector.getType()))
                .WHERE_IF("c.create_time >= #{minCreateTime}", nonNull(selector.getMinCreateTime()))
                .WHERE_IF("c.create_time < #{maxCreateTime}", nonNull(selector.getMaxCreateTime()))
                .WHERE_IF("c.company_id = #{companyId}", nonNull(selector.getCompanyId()))
                .WHERE_IF("c.biz_type = #{bizType}", nonNull(selector.getBizType()))
                .WHERE_IF("c.house_type = #{houseType}", nonNull(selector.getHouseType()))
        if (hasNotNullElement(selector.getDepartmentIds())) {
            sql.WHERE("c.department_id IN (" + Joiner.on(",").
                    skipNulls().
                    join(selector.getDepartmentIds()) + ")")
        }
        sql.WHERE_IF("c.employee_id = #{employeeId}", nonNull(selector.getEmployeeId()))
        sql.WHERE_IF("c.id = #{id}", nonNull(selector.getId()))
        sql.ORDER_BY("c.id desc")
        sql.toString()
    }
}
