package com.lyun.estate.op.dianping.user;

import com.lyun.estate.op.dianping.corp.entity.ActionResultDTO;
import com.lyun.estate.op.dianping.corp.domain.WxLoginToken;
import com.lyun.estate.op.dianping.user.service.UserService;
import com.lyun.estate.op.dianping.utils.CorpUtil;
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
    public ActionResultDTO userInfo(@RequestHeader String token, @RequestParam String nicky, @RequestParam String avatar){

        long userId = CorpUtil.getUserId(token);

        userService.userInfo(userId, nicky, avatar);

        return new ActionResultDTO().setSuccess(true);

    }
}
