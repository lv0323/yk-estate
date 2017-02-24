package com.lyun.estate.rest.user;

import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.biz.follow.service.FollowService;
import com.lyun.estate.rest.supports.resources.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse follow(@RequestParam long targetId,
                                 @RequestParam DomainType domainType,
                                 @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(followService.createFollow(targetId, domainType));
    }

    @PostMapping(value = "/cancel", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse cancelFollow(@RequestParam long targetId,
                                       @RequestParam DomainType domainType,
                                       @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(followService.cancelFollow(targetId, domainType));
    }

    @PostMapping(value = "/is-favorite", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse isFollow(@RequestParam long targetId,
                                   @RequestParam DomainType domainType,
                                   @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(followService.isFollow(targetId, domainType));
    }
}