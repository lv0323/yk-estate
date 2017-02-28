package com.lyun.estate.biz.fang.repo;

import com.lyun.estate.biz.fang.entity.FangDescr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeffrey on 2017-02-28.
 */
@Repository
public interface FangDescrRepo {

    @Insert("INSERT INTO t_fang_descr (fang_id, title, core, hu_xing, zhuang_xiu, quan_shu, xue_qu, pei_tao, shui_fei, jiao_tong, xiao_qu, tou_zi) VALUES" +
            " (#{fangId}, #{title}, #{core}, #{huXing}, #{zhuangXiu}, #{quanShu}, #{xueQu}, #{peiTao}, #{shuiFei}, #{jiaoTong}, #{xiaoQu}, #{touZi})")
    @Options(useGeneratedKeys = true)
    int save(FangDescr fangDescr);

    @Select("select * from t_fang_descr where id =#{id}")
    FangDescr findOne(Long id);

    @Update("UPDATE t_fang_descr\n" +
            "SET title  = #{title}, core = #{core}, hu_xing = #{huXing}, zhuang_xiu = #{zhuangXiu}, quan_shu = #{quanShu}\n" +
            ", xue_qu = #{xueQu}, pei_tao = #{peiTao}, shui_fei = #{shuiFei}, jiao_tong = #{jiaoTong}, xiao_qu = #{xiaoQu}, tou_zi = #{touZi}\n" +
            "WHERE fang_id = #{fangId}")
    int update(FangDescr fangDescr);


    @Select("select * from t_fang_descr where fang_id = #{fangId}")
    FangDescr findByFangId(Long fangId);
}
