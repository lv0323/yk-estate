package com.lyun.estate.biz.support.clock;

import com.lyun.estate.biz.support.clock.repository.ClockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class Clock {

    @Autowired
    ClockMapper clockMapper;

    public Date now() {
        return clockMapper.now();
    }

    public Instant nowInstant() {
        return clockMapper.now().toInstant();
    }


}
