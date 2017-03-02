package com.lyun.estate.rest.favorite;

import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTTokenArgumentResolver;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.favorite.service.FavoriteService;
import com.lyun.estate.biz.support.def.DomainType;
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
    public CommonResponse createFavorite(@RequestParam long targetId,
                                         @RequestParam DomainType domainType,
                                         @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.createFavorite(targetId, domainType));
    }

    @PostMapping(value = "/cancel", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse cancelFavorite(@RequestParam long targetId,
                                       @RequestParam DomainType domainType,
                                       @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.cancelFavorite(targetId, domainType));
    }

    @PostMapping(value = "/is-favorite", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @CheckToken
    public CommonResponse isFavorite(@RequestParam long targetId,
                                   @RequestParam DomainType domainType,
                                   @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {
        return new CommonResponse().setSuccess(favoriteService.isFavorite(targetId, domainType));
    }
}