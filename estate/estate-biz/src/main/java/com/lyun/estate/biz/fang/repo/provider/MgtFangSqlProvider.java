package com.lyun.estate.biz.fang.repo.provider;

import com.lyun.estate.biz.fang.entity.Fang;
import com.lyun.estate.biz.fang.entity.FangExt;
import com.lyun.estate.core.repo.SQL;

import java.util.Objects;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class MgtFangSqlProvider {

    public String saveFang(Fang fang) {
        return new SQL() {{
            INSERT_INTO("t_fang")
                    .VALUES("biz_type", "#{bizType}")
                    .VALUES("house_type", "#{houseType}")
                    .VALUES("house_sub_type", "#{houseSubType}")
                    .VALUES("licence_id", "#{licenceId}")
                    .VALUES("xiao_qu_id", "#{xiaoQuId}")
                    .VALUES("s_counts", "#{sCounts}")
                    .VALUES("t_counts", "#{tCounts}")
                    .VALUES("c_counts", "#{cCounts}")
                    .VALUES("w_counts", "#{wCounts}")
                    .VALUES("yt_counts", "#{ytCounts}")
                    .VALUES("orientation", "#{orientation}")
                    .VALUES("decorate", "#{decorate}")
                    .VALUES("estate_area", "#{estateArea}")
                    .VALUES_IF("real_area", "#{realArea}", Objects.nonNull(fang.getRealArea()))
                    .VALUES("publish_price", "#{publishPrice}")
                    .VALUES("price_unit", "#{priceUnit}")
                    .VALUES("unit_price", "#{unitPrice}")
                    .VALUES_IF("transfer_price", "#{transferPrice}", Objects.nonNull(fang.getTransferPrice()))
                    .VALUES_IF("bottom_price", "#{bottomPrice}", Objects.nonNull(fang.getBottomPrice()))
                    .VALUES_IF("resident", "#{resident}", Objects.nonNull(fang.getResident()))
                    .VALUES("process", "#{process}")
                    .VALUES("floor", "#{floor}")
                    .VALUES("floor_counts", "#{floorCounts}")
                    .VALUES("floor_type", "#{floorType}")
                    .VALUES("structure_type", "#{structureType}")
                    .VALUES("build_year", "#{buildYear}")
                    .VALUES("heating_type", "#{heatingType}")
                    .VALUES("has_elevator", "#{hasElevator}");
        }}.toString();
    }

    public String saveFangExt(FangExt fangExt) {
        return new SQL() {{
            INSERT_INTO("t_fang_ext")
                    .VALUES("fang_id", "#{fangId}")
                    .VALUES("level", "#{level}")
                    .VALUES("showing", "#{showing}")
                    .VALUES("delegate_type", "#{delegateType}")
                    .VALUES_IF("delegate_start", "#{delegateStart}", Objects.nonNull(fangExt.getDelegateStart()))
                    .VALUES_IF("delegate_end", "#{delegateEnd}", Objects.nonNull(fangExt.getDelegateEnd()))
                    .VALUES("status", "#{status}")
                    .VALUES("source", "#{source}")
                    .VALUES("certif_type", "#{certifType}")
                    .VALUES("certif_address", "#{certifAdress}")
                    .VALUES_IF("certif_no", "#{certifNo}", Objects.nonNull(fangExt.getCertifNo()))
                    .VALUES("property_type", "#{propertyType}")
                    .VALUES("taxes_willing", "#{taxesWilling}")
                    .VALUES("commission_willing", "#{commissionWilling}")
                    .VALUES_IF("purchase_price", "#{purchasePrice}", Objects.nonNull(fangExt.getPurchasePrice()))
                    .VALUES_IF("purchase_date", "#{purchaseDate}", Objects.nonNull(fangExt.getPurchaseDate()))
                    .VALUES("is_only", "#{isOnly}")
                    .VALUES("over_years", "#{overYears}")
                    .VALUES_IF("mortgage", "#{mortgage}", Objects.nonNull(fangExt.getMortgage()))
                    .VALUES_IF("note", "#{note}", Objects.nonNull(fangExt.getNote()));
        }}.toString();
    }
}
