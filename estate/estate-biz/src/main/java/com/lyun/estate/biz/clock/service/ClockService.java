package com.lyun.estate.biz.clock.service;

import com.lyun.estate.biz.clock.repository.ClockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class ClockService {
    @Autowired
    ClockMapper clockMapper;

    public Date currentTime() {
        return clockMapper.currentTime();
    }

    public Instant now() {
        return clockMapper.currentTime().toInstant();
    }
}
