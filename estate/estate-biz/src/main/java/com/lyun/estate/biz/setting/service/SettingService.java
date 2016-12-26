package com.lyun.estate.biz.setting.service;

import com.lyun.estate.biz.setting.def.NameSpace;
import com.lyun.estate.biz.setting.entity.Setting;
import com.lyun.estate.biz.setting.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jeffrey on 2016-12-26.
 */
@Service
public class SettingService {

    @Autowired
    private SettingRepository settingRepository;

    public List<Setting> find(NameSpace nameSpace, String key) {
        return settingRepository.find(nameSpace, key);
    }

    public Setting findOne(Long id) {
        return settingRepository.findOne(id);
    }

}
