package com.lyun.estate.biz.support.settings;

import com.lyun.estate.biz.support.settings.def.NameSpace;
import com.lyun.estate.biz.support.settings.entity.Setting;
import com.lyun.estate.biz.support.settings.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingProvider {

    @Autowired
    private SettingRepository settingRepository;

    public Setting find(NameSpace nameSpace, String key) {
        return settingRepository.find(nameSpace, key);
    }

    public Setting findOne(Long id) {
        return settingRepository.findOne(id);
    }

}
