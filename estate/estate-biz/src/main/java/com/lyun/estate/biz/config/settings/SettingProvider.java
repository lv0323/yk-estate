package com.lyun.estate.biz.config.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SettingProvider {

    @Autowired
    private SettingRepository settingRepository;

    public List<Setting> find(NameSpace nameSpace, String key) {
        return settingRepository.find(nameSpace, key);
    }

    public Setting findOne(Long id) {
        return settingRepository.findOne(id);
    }

}
