package com.lyun.estate.rest.user;

import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.user.service.UserService;
import com.lyun.estate.rest.supports.resources.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attention")
public class AttentionController {

    @Autowired
    UserService userService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse attention(@RequestParam long communityId,
                                    @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(userService.attentionCommunity(communityId));
    }

    @PostMapping(value = "/cancel", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse attentionCancel(@RequestParam long communityId,
                                          @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(userService.attentionCancel(communityId));
    }

    @PostMapping(value = "/is-attention", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse isAttention(@RequestParam long communityId,
                                      @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(userService.isAttention(communityId));
    }
}
