package com.lyun.estate.biz

import com.lyun.estate.biz.fang.def.*
import RandomUtils

File file = new File("fangExt.sql")

file.write('begin;\n')

for (int i = 1; i < 5000; i++) {
    level = addYing HouseLevel.values()[RandomUtils.nextInt(HouseLevel.values().length)]
    showing = addYing Showing.values()[RandomUtils.nextInt(Showing.values().length)]
    delegateType = addYing DelegateType.values()[RandomUtils.nextInt(DelegateType.values().length)]
    status = addYing HouseStatus.values()[RandomUtils.nextInt(HouseStatus.values().length)]
    source = addYing HouseSource.values()[RandomUtils.nextInt(HouseSource.values().length)]
    certifType = addYing CertifType.values()[RandomUtils.nextInt(CertifType.values().length)]
    propertyType = addYing PropertyType.values()[RandomUtils.nextInt(PropertyType.values().length)]
    taxesWilling = addYing TaxesWilling.values()[RandomUtils.nextInt(TaxesWilling.values().length)]
    commissionWilling = addYing CommissionWilling.values()[RandomUtils.nextInt(CommissionWilling.values().length)]
    isOnly = addYing(['Y', 'N'][RandomUtils.nextInt(2)])
    overYears = [0, 2, 5][RandomUtils.nextInt(3)]
    mortgage = addYing(['Y', 'N'][RandomUtils.nextInt(2)])
    file.append(
            " INSERT INTO t_fang_ext(fang_id, level, showing, delegate_type, delegate_start, delegate_end, status, source, certif_type, certif_adress, certif_no, property_type, taxes_willing, commission_willing, purchase_price, purchase_date, is_only, over_years, mortgage, note)" +
                    " VALUES (${i},${level},${showing},${delegateType},now(),now(),${status},${source},${certifType},'','12345678',${propertyType},${taxesWilling},${commissionWilling},0,now(),${isOnly},${overYears},${mortgage},'');\n"
    )
}

file.append('commit;')

def addYing(Object o) {
    return "'" + o + "'"
}