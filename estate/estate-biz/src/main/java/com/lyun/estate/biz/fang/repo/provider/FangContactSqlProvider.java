package com.lyun.estate.biz.fang.repo.provider;

import com.lyun.estate.biz.fang.entity.FangContact;
import com.lyun.estate.core.repo.SQL;

import java.util.Objects;

/**
 * Created by Jeffrey on 2017-03-08.
 */
public class FangContactSqlProvider {

    public String save(FangContact fangContact) {
        return new SQL().INSERT_INTO("t_fang_contact")
                .VALUES("fang_id", "#{fangId}")
                .VALUES("name", "#{name}")
                .VALUES("mobile", "#{mobile}")
                .VALUES_IF("a_mobile", "#{aMobile}", Objects.nonNull(fangContact.getaMobile()))
                .VALUES_IF("b_mobile", "#{bMobile}", Objects.nonNull(fangContact.getbMobile()))
                .VALUES_IF("qq", "#{qq}", Objects.nonNull(fangContact.getQq()))
                .VALUES_IF("we_chat", "#{weChat}", Objects.nonNull(fangContact.getWeChat()))
                .VALUES_IF("email", "#{email}", Objects.nonNull(fangContact.getEmail()))
                .toString();
    }
}
