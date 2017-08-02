package com.lyun.estate.mgt.xiaoqu;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lyun.estate.biz.fang.def.StructureType;
import com.lyun.estate.biz.file.def.CustomType;
import com.lyun.estate.biz.file.entity.FileDescription;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuDetail;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuFilter;
import com.lyun.estate.biz.spec.xiaoqu.mgt.entity.MgtXiaoQuSummary;
import com.lyun.estate.biz.spec.xiaoqu.rest.entity.XiaoQuSummary;
import com.lyun.estate.biz.xiaoqu.entity.CommunityEntity;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQu;
import com.lyun.estate.biz.xiaoqu.entity.XiaoQuEntity;
import com.lyun.estate.core.supports.exceptions.ExceptionUtil;
import com.lyun.estate.core.supports.pagebound.PageBoundsArgumentResolver;
import com.lyun.estate.mgt.supports.CommonResp;
import com.lyun.estate.mgt.xiaoqu.service.XiaoQuMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by Jeffrey on 2017-02-27.
 */
@RestController
@RequestMapping("api/xiao-qu")
public class XiaoQuRest {

    @Autowired
    private XiaoQuMgtService xiaoQuMgtService;

    @GetMapping("/{id}")
    public XiaoQu xiaoQu(@PathVariable("id") Long id) {
        return xiaoQuMgtService.findOne(id);
    }

    @GetMapping("/list")
    public PageList<MgtXiaoQuSummary> summary(@RequestParam Long cityId,
                                              @RequestParam(required = false) Long districtId,
                                              @RequestParam(required = false) Long subDistrictId,
                                              @RequestParam(required = false) Long xiaoQuId,
                                              @RequestHeader(PageBoundsArgumentResolver.PAGE_HEADER) PageBounds pageBounds) {
        MgtXiaoQuFilter filter = new MgtXiaoQuFilter()
                .setCityId(cityId)
                .setDistrictId(districtId)
                .setSubDistrictId(subDistrictId)
                .setXiaoQuId(xiaoQuId);

        return xiaoQuMgtService.list(filter, pageBounds);
    }

    @GetMapping("/detail/{id}")
    public MgtXiaoQuDetail summary(@PathVariable("id") Long id) {
        return xiaoQuMgtService.detail(id);
    }


    @PostMapping("image")
    public CommonResp createImage(@RequestParam Long xiaoQuId,
                                  @RequestParam CustomType customType,
                                  @RequestParam List<MultipartFile> images) {
        int[] result = new int[]{0, 0};
        StringBuilder messageBuilder = new StringBuilder();

        for (int i = 0; i < images.size(); i++) {
            try {
                MultipartFile image = images.get(i);
                try (InputStream inputStream = image.getInputStream()) {
                    xiaoQuMgtService.createImage(xiaoQuId,
                            customType,
                            inputStream,
                            image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf(".")));
                    result[0] = result[0] + 1;
                }
            } catch (Exception e) {
                ExceptionUtil.catching(e);
                messageBuilder.append("第").append(i + 1).append("图错误，：").append(e.getLocalizedMessage()).append(",");
                result[1] = result[1] + 1;
            }
        }

        String message = messageBuilder.toString();
        if (message.length() > 1) {
            message = message.substring(0, message.length() - 1);
        }

        if (result[1] > 0) {
            return CommonResp.failed().addExt("succeed", result[0]).addExt("failed", result[1])
                    .addExt("message", message);
        } else {
            return CommonResp.succeed().addExt("succeed", result[0]);
        }
    }

    @GetMapping("image")
    public List<FileDescription> getImages(@RequestParam Long fangId,
                                           @RequestParam CustomType customType) {
        return xiaoQuMgtService.getImages(fangId, customType);
    }

    @PostMapping("delete-image")
    public CommonResp deleteImage(Long fileId) {
        return Objects.equals(true, xiaoQuMgtService.deleteImage(fileId)) ?
                CommonResp.succeed() : CommonResp.failed();
    }

    @PostMapping("set-first-image")
    public CommonResp setFirstImage(Long fileId) {
        return Objects.equals(true, xiaoQuMgtService.setFirstImage(fileId)) ?
                CommonResp.succeed() : CommonResp.failed();
    }

    @PutMapping("")
    public int updateXiaoQu(@RequestParam long id,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String alias,
                                        @RequestParam(required = false) String address,
                                        @RequestParam(required = false) String developers,
                                        @RequestParam(required = false) Integer structureType,
                                        @RequestParam(required = false) Integer buildedYear,
                                        @RequestParam(required = false) Integer developYear,
                                        @RequestParam(required = false) String propertyCompany,
                                        @RequestParam(required = false) String propertyCompanyPhone,
                                        @RequestParam(required = false) String propertyFee,
                                        @RequestParam(required = false) String parkingSpace,
                                        @RequestParam(required = false) String parkingFee,
                                        @RequestParam(required = false) String parkingRate,
                                        @RequestParam(required = false) Integer buildings,
                                        @RequestParam(required = false) Integer houses,
                                        @RequestParam(required = false) String containerRate,
                                        @RequestParam(required = false) String greenRate) {
        CommunityEntity communityEntity = new CommunityEntity(){{
           setId(id);
           setName(name);
           setAlias(alias);
//           setAddress(address); // disabled this for now
           setDevelopers(developers);
           setStructureType(structureType);
           setBuildedYear(buildedYear);
           setDevelopYear(developYear);
           setPropertyCompany(propertyCompany);
           setPropertyCompanyPhone(propertyCompanyPhone);
           setPropertyFee(propertyFee);
           setParkingSpace(parkingSpace);
           setParkingFee(parkingFee);
           setParkingRate(parkingRate);
           setBuildings(buildings);
           setHouses(houses);
           setContainerRate(containerRate);
           setGreenRate(greenRate);
        }};

        return xiaoQuMgtService.updateXiaoQu(communityEntity);
    }

    @PostMapping("")
    public MgtXiaoQuDetail createNewXiaoQu(@RequestParam String name,
                                         @RequestParam(required = false) String alias,
                                         @RequestParam String address,
                                         @RequestParam long cityId,
                                         @RequestParam long subDistrictId,
                                           @RequestParam BigDecimal longitude,
                                           @RequestParam BigDecimal latitude) {
        return xiaoQuMgtService.createXiaoQu(name, alias, cityId, subDistrictId, longitude, latitude, address);
    }
}
