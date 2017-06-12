package com.lyun.estate.op.dianping.user.service;

import com.google.gson.Gson;
import com.lyun.estate.op.dianping.common.BizRuntimeException;
import com.lyun.estate.op.dianping.user.domain.WeChatProperty;
import com.lyun.estate.op.dianping.user.domain.WxLoginResponse;
import com.lyun.estate.op.dianping.user.domain.TokenDTO;
import com.lyun.estate.op.dianping.user.repo.UserRepo;
import com.lyun.estate.op.dianping.utils.CorpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by localuser on 2017/5/16.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserRepo userRepo;

    @Autowired
    private WeChatProperty weChatProperty;

    @Transactional
    public TokenDTO wxLogin(String jscode){

        WxLoginResponse response = CorpUtil.loginToWX(weChatProperty.getLoginUrl(), jscode);

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

        String token = CorpUtil.getToken(userId);

        return new TokenDTO().setToken(token);
    }

    public void userInfo(long userId, String nicky, String avatar){

        userRepo.updateUserInfo(userId, nicky, avatar);
    }

}
