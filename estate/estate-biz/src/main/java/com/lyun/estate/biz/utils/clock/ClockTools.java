package com.lyun.estate.biz.utils.clock;

import com.lyun.estate.biz.utils.clock.repository.ClockMapper;
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
