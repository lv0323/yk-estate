package com.lyun.estate.op.corp;

import com.lyun.estate.op.corp.entity.ActionResultBean;
import com.lyun.estate.op.corp.entity.WxLoginToken;
import com.lyun.estate.op.corp.service.UserService;
import com.lyun.estate.op.utils.CorpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by localuser on 2017/5/16.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**登录流程
     1 获得code；
     2 传给服务器；
     3 服务器登录；
     4 获得openid和session_key
     5 保存
     6 生成token，返回给客户端token；


     wx登录过期时，重新登录，重走上述流程，保存时要先查询，存在就更新，不存在就保存
     重发token；

     换机登录时，重走上述流程，*/


    @PostMapping(value = "/wx_login", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public WxLoginToken wxLogin(@RequestParam String code){
//        return userService.wxLogin(code);
        return userService.wxLogin(code);
    }

    @PostMapping(value = "/wx_userinfo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ActionResultBean userInfo(@RequestHeader String token, @RequestParam String nicky, @RequestParam String avatar){

        System.out.println("token "+token);
        long userId = CorpUtil.getUserId(token);
        System.out.println("userId "+userId);

        userService.userInfo(userId, nicky, avatar);

        return new ActionResultBean().setSuccess(true);

    }
}
