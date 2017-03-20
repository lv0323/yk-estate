package com.lyun.estate.rest.favorite;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.auth.token.CheckToken;
import com.lyun.estate.biz.auth.token.JWTToken;
import com.lyun.estate.biz.auth.token.JWTTokenArgumentResolver;
import com.lyun.estate.biz.favorite.def.FavoriteType;
import com.lyun.estate.biz.favorite.service.FavoriteService;
import com.lyun.estate.biz.spec.fang.rest.entity.FangSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.support.def.DomainType;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
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


    @GetMapping("")
    @CheckToken
    public PageList<FangSummary> fang(FavoriteType type,
                                      @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds,
                                      @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {

        return null;
    }


    @GetMapping("")
    @CheckToken
    public PageList<XiaoQuSummary> xiaoQu(@RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds,
                                          @RequestHeader(JWTTokenArgumentResolver.AUTH_HEADER) JWTToken jwtToken) {

        return null;
    }
}