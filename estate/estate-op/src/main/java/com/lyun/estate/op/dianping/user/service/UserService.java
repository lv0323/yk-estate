package com.lyun.estate.op.dianping.user.service;

import com.google.gson.Gson;
import com.lyun.estate.op.dianping.corp.domain.WxLoginResponse;
import com.lyun.estate.op.dianping.corp.domain.WxLoginToken;
import com.lyun.estate.op.dianping.corp.entity.*;
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

    private static String appid = "wx8d47f7b7aa41cec2";
    private static String secret = "f03f58cfeb02e523e6255161ceabb353";
//    private static String appid = "wxcd0237790af77445";
//    private static String secret = "71e2ca1050ab66bc280975c648987216";
    private static String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=" +appid +
            "&secret="+secret+
            "&js_code=%s&grant_type=authorization_code";


    @Transactional
    public WxLoginToken wxLogin(String jscode){

        WxLoginResponse response = loginToWX(jscode);

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

        return new WxLoginToken().setToken(token);
    }

    private WxLoginResponse loginToWX(String jscode){

        String urlStr = String.format(wxLoginUrl, jscode);

        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(urlStr, String.class);

        Gson gson = new Gson();
        WxLoginResponse response = gson.fromJson(str, WxLoginResponse.class);

        if( response == null){
            logger.error("weixin login failed : response == null url="+urlStr);
            throw new BizRuntimeException("wxLogin no response");
        }

        if(response.getErrmsg() != null ){
            logger.error("weixin login failed : url="+urlStr+" errmsg="+response.getErrmsg());
            throw new BizRuntimeException(response.getErrmsg());
        }

        return response;
    }


    public void userInfo(long userId, String nicky, String avatar){

        userRepo.updateUserInfo(userId, nicky, avatar);
    }

}
