package com.lyun.estate.op.dianping.user.service;

import com.lyun.estate.op.config.DianpingProperties;
import com.lyun.estate.op.dianping.user.domain.WxLoginResponse;
import com.lyun.estate.op.dianping.user.domain.TokenDTO;
import com.lyun.estate.op.dianping.user.repo.UserRepo;
import com.lyun.estate.op.dianping.utils.CorpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by localuser on 2017/5/16.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;

    @Autowired
    private DianpingProperties properties;

    @Autowired
    private CorpUtil util;

    @Transactional
    public TokenDTO wxLogin(String jscode){

        WxLoginResponse response = CorpUtil.loginToWX(properties.getWeChatLoginUrl(), jscode);

        if( userRepo.registered(response.getOpenId()) == 0){

            userRepo.register(
                    "wechat",
                    response.getOpenId(),
                    response.getSessionKey());

        }else{

            userRepo.updateSessionKey(
                    response.getOpenId(),
                    response.getSessionKey());
        }

        long userId = userRepo.getByOpenId(response.getOpenId());

        String token = util.getToken(userId);

        return new TokenDTO().setToken(token);
    }

    public void userInfo(long userId, String nicky, String avatar){

        userRepo.updateUserInfo(userId, nicky, avatar);
    }

}
