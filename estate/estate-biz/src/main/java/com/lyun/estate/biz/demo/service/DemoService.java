package com.lyun.estate.biz.demo.service;

import com.lyun.estate.biz.demo.repository.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DemoService {
    @Autowired
    DemoMapper demoMapper;

    public Date currentTime() {
        return demoMapper.currentTime();
    }
}
