package com.lyun.estate.biz

import com.lyun.estate.biz.fang.def.*
import StructureType
import RandomUtils

File file = new File("fang.sql")

file.write('begin;\n')

for (int i = 10001; i < 15000; i++) {

    bizType = BizType.values()[RandomUtils.nextInt(BizType.values().length)]
    houseType = HouseType.values()[RandomUtils.nextInt(HouseType.values().length)]
    houseSubType = addYing houseType.getSubTypes().get(RandomUtils.nextInt(houseType.getSubTypes().size()))
    houseType = addYing houseType
    xiaoQuId = RandomUtils.nextInt(10819) + 1
    ranking = RandomUtils.nextInt(100)
    sCounts = RandomUtils.nextInt(9) + 1
    tCounts = RandomUtils.nextInt(9) + 1
    cCounts = RandomUtils.nextInt(9) + 1
    wCounts = RandomUtils.nextInt(9) + 1
    ytCounts = RandomUtils.nextInt(9) + 1
    oritation = addYing Orientation.values()[RandomUtils.nextInt(Orientation.values().length)]
    decorate = addYing Decorate.values()[RandomUtils.nextInt(Decorate.values().length)]
    estate_area = new BigDecimal(RandomUtils.nextDouble() * 150 + 50).setScale(2, 0)
    real_area = new BigDecimal(RandomUtils.nextDouble() * 150 + 50).setScale(2, 0)
    if (bizType == BizType.SELL) {
        publishPrice = RandomUtils.nextInt(800) + 100
        priceUnit = addYing PriceUnit.WAN_YUAN
        unitPrice = publishPrice * 10000 / estate_area
        transferPrice = 0
    } else {
        publishPrice = 2000 + RandomUtils.nextInt(10000)
        priceUnit = addYing PriceUnit.YUAN
        unitPrice = publishPrice / estate_area
        transferPrice = RandomUtils.nextInt(publishPrice)
    }
    unitPrice = unitPrice.toInteger()
    Integer price8 = publishPrice * 4 / 5
    Integer price2 = publishPrice / 5
    Integer bottomPrice = price8 + RandomUtils.nextInt(price2)
    if (bizType == BizType.SELL) {
        resident = addYing 'Y'
    } else {
        resident = addYing 'N'
    }
    bizType = addYing bizType
    procss = addYing HouseProcess.values()[RandomUtils.nextInt(HouseProcess.values().length)]
    floorCounts = 6 + RandomUtils.nextInt(50)
    floorType = addYing FloorType.values()[RandomUtils.nextInt(FloorType.values().length)]
    structType = addYing StructureType.values()[RandomUtils.nextInt(StructureType.values().length)]
    buildYear = 1980 + RandomUtils.nextInt(40)
    heatingType = addYing HeatingType.values()[RandomUtils.nextInt(HeatingType.values().length)]
    hasElevator = addYing(['Y', 'N'][RandomUtils.nextInt(2)])

    file.append(
            "INSERT INTO t_fang (biz_type, house_type, house_sub_type, licence_id, xiao_qu_id, ranking, s_counts, t_counts, c_counts, w_counts, yt_counts, orientation, decorate, estate_area, real_area, publish_price, price_unit, unit_price, transfer_price, bottom_price, resident, process, floor_counts, floor_type, structure_type, build_year, heating_type, has_elevator, create_time, update_time)" +
                    " values (${bizType}, ${houseType},${houseSubType}, ${i}, ${xiaoQuId},${ranking},${sCounts},${tCounts},${cCounts},${wCounts},${ytCounts}, ${oritation}, ${decorate},${estate_area},${real_area},${publishPrice}, ${priceUnit},${unitPrice}, ${transferPrice},${bottomPrice},${resident},${procss},${floorCounts},${floorType},${structType},${buildYear},${heatingType},${hasElevator},now(),now());\n"

    )
}

file.append('commit;')

def addYing(Object o) {
    return "'" + o + "'"
}