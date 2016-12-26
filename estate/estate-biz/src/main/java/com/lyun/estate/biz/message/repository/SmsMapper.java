package com.lyun.estate.biz.message.repository;

import com.lyun.estate.core.supports.resources.SmsCode;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsMapper {
    @Insert("")
    int createSmsLog(SmsCode smsCode);
}
