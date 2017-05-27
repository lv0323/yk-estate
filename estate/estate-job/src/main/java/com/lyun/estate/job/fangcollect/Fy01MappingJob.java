package com.lyun.estate.job.fangcollect;

import com.lyun.estate.biz.fang.def.HouseType;
import com.lyun.estate.biz.fang.def.Orientation;
import com.lyun.estate.biz.fangcollect.entity.FangPoolDistrict;
import com.lyun.estate.biz.fangcollect.service.MgtFangCollectService;
import com.lyun.estate.biz.spec.fang.mgt.service.MgtFangService;
import com.lyun.estate.biz.fangcollect.def.FangOrigin;
import com.lyun.estate.biz.fangcollect.entity.FY01Fang;
import com.lyun.estate.biz.fangcollect.entity.FangPool;
import com.lyun.estate.biz.fangcollect.repo.FY01Repo;
import com.lyun.estate.biz.spec.xiaoqu.rest.service.XiaoQuService;
import com.lyun.estate.job.BaseJob;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by robin on 17/5/5.
 */
@Service
public class Fy01MappingJob extends BaseJob {

    @Autowired
    FY01Repo fy01Repo;

    @Autowired
    MgtFangService mgtFangService;

    @Autowired
    XiaoQuService xiaoQuService;

    @Autowired
    MgtFangCollectService mgtFangCollectService;

    @Override
    public String jobName() {
        return "Fy01MappingJob";
    }

    @Override
    public void runJob() {
        FY01Fang fang;
        FangPool fangPool;
        List<FY01Fang> list = fy01Repo.selectUnProcessItems();

        for (FY01Fang fy01Fang : list){
            fangPool = this.fangMapping(fy01Fang);
            if(mgtFangCollectService.createFangPool(fangPool)>0){
                System.out.println(fangPool);
            }
           Long id = fy01Fang.getThirdPartyId();
           fy01Repo.updateProcess(id);
        }

    }

    private FangPool fangMapping(FY01Fang fy01Fang){
        FangPool fangPool = new FangPool();
        fangPool.setUrl(fy01Fang.getUrl());
        fangPool.setThirdPartyId(fy01Fang.getThirdPartyId());
        fangPool.setHeader(fy01Fang.getHeader());
        fangPool.setBizType(fy01Fang.getBizType());
        fangPool.setXiaoQuName(fy01Fang.getXiaoQuName());
        fangPool.setsCounts(fy01Fang.getsCounts());
        fangPool.settCounts(fy01Fang.gettCounts());
        fangPool.setwCounts(fy01Fang.getwCounts());
        fangPool.setcCounts(fy01Fang.getcCounts());
        fangPool.setYtCounts(fy01Fang.getYtCounts());
        fangPool.setDecorate(fy01Fang.getDecorateStr());
        fangPool.setEstateArea(fy01Fang.getEstateArea());
        fangPool.setPublishPrice(fy01Fang.getPublishPrice());
        fangPool.setPriceUnit(fy01Fang.getPriceUnit());
        fangPool.setUnitPrice(fy01Fang.getUnitPrice());
        fangPool.setFloor(fy01Fang.getFloor());
        fangPool.setFloorCounts(fy01Fang.getFloorCounts());
        fangPool.setCreateTime(fy01Fang.getCreateTime());
        fangPool.setContactName(fy01Fang.getContactName());
        fangPool.setContactMobile(fy01Fang.getContactMobile());
        fangPool.setDescription(fy01Fang.getDescription());
        if(fy01Fang.getImagePath() !=null && !fy01Fang.getImagePath().equals("")){
            String[] imgArr = fy01Fang.getImagePath().split(",");
            List<String> imgList = new ArrayList<String>(Arrays.asList(imgArr));
            JSONArray jsArr = JSONArray.fromObject(imgList);
            fangPool.setImagePath(jsArr.toString());
        }
        fangPool.setUpdateTime(fy01Fang.getUpdataTime());

        if (fy01Fang.getFloor() != null && fy01Fang.getFloorCounts() != null) {
            fangPool.setFloorType(mgtFangService.calculateFloorType(fy01Fang.getFloor(), fy01Fang.getFloorCounts()));
        }
        String xiaoquAddr = fy01Fang.getXiaoQuAddr();
        String districtName = xiaoquAddr.split("-")[1].trim();
        if(districtName !=null && !districtName.equals("")){
            FangPoolDistrict district = mgtFangCollectService.getDistrictByName(districtName);
            if(district != null){
                fangPool.setCityId(district.getCityId());
                fangPool.setDistrictId(district.getId());
            }
        }
        if(fy01Fang.getOrientationStr()!=null){
            fangPool.setOrientation(Orientation.parse(fy01Fang.getOrientationStr()));
        }
        if(fy01Fang.getHouseTypeStr()!=null){
            fangPool.setHouseType(HouseType.parse(fy01Fang.getHouseTypeStr()));
        }

        fangPool.setAddress(xiaoquAddr);
        Map extMap = new HashMap<String,String>();
        if(fy01Fang.getOverview()!=null){
            extMap.put("overview", fy01Fang.getOverview());
        }
        if(fy01Fang.getExtInfo() != null){
            extMap.put("expand", fy01Fang.getExtInfo());
        }
        JSONObject extJson = JSONObject.fromObject(extMap);
        fangPool.setExtInfo(extJson.toString());
        fangPool.setFangOrigin(FangOrigin.FY01);
        return fangPool;
    }
}
