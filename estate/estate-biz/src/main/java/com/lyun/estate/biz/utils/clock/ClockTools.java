package com.lyun.estate.biz.utils.clock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClockTools {

    @Autowired
    ClockMapper clockMapper;

    public Date now() {
        return clockMapper.now();
    }

}
