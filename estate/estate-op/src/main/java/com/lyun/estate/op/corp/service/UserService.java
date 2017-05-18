package com.lyun.estate.op.corp.service;

import com.google.gson.Gson;
import com.lyun.estate.op.corp.entity.*;
import com.lyun.estate.op.corp.repo.UserRepo;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.rmi.CORBA.Util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;

/**
 * Created by localuser on 2017/5/16.
 */
@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    private static String appid = "wx8d47f7b7aa41cec2";
    private static String secret = "f03f58cfeb02e523e6255161ceabb353";
    private static String wxLoginUrl = "https://api.weixin.qq.com/sns/jscode2session?" +
            "appid=" +appid +
            "&secret="+secret+
            "&js_code=%s&grant_type=authorization_code";


    @Transactional
    public WxLoginToken wxLogin(String jscode){

        System.out.println("jscode"+jscode);


        String urlStr = String.format(wxLoginUrl, jscode);

        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(urlStr, String.class);

        System.out.println("str"+str);

        Gson gson = new Gson();
        WxLoginResponse response = gson.fromJson(str, WxLoginResponse.class);

        if( response == null){
            throw new BizRuntimeException("wxLogin no response");
        }

        if(response.getErrmsg() != null ){

            throw new BizRuntimeException(response.getErrmsg());
        }

        if( userRepo.registered(response.getOpenId()) <= 0){

            System.out.println("before register");
            userRepo.register("wechat", response.getOpenId(), response.getSession_key());

            System.out.println("after register");
        }else{
            System.out.println("before update");
            userRepo.updateSessionKey(response.getOpenId(), response.getSession_key());
            System.out.println("after update");
        }

        long userId = userRepo.getByOpenId(response.getOpenId());

        System.out.println("userId "+userId);

        String token = CorpUtil.getToken(userId);

        System.out.println("token "+token);
        return new WxLoginToken().setToken(token);
    }

    public void userInfo(long userId, String nicky, String avatar){
        userRepo.updateUserInfo(userId, nicky, avatar);
    }

}
