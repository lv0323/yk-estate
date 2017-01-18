package com.lyun.estate.rest.user;

import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.spec.common.DomainType;
import com.lyun.estate.biz.user.service.FavoriteService;
import com.lyun.estate.rest.supports.resources.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse favorite(@RequestParam long targetId,
                                   @RequestParam DomainType domainType,
                                   @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.createFavorite(targetId, domainType));
    }

    @PostMapping(value = "/cancel", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse favoriteCancel(@RequestParam long targetId,
                                         @RequestParam DomainType domainType,
                                         @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.favoriteCancle(targetId, domainType));
    }

    @PostMapping(value = "/is-favorite", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse isFavorite(@RequestParam long targetId,
                                     @RequestParam DomainType domainType,
                                     @RequestHeader("auth") JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.isFavorite(targetId, domainType));
    }
}
