package com.lyun.estate.op.dianping.utils;

import com.google.gson.Gson;
import com.lyun.estate.op.config.DianpingProperties;
import com.lyun.estate.op.dianping.common.BizRuntimeException;
import com.lyun.estate.op.dianping.corp.domain.TagCountDTO;
import com.lyun.estate.op.dianping.user.domain.WxLoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class CorpUtil {
    private static final Logger logger = LoggerFactory.getLogger(CorpUtil.class);

    @Autowired
    DianpingProperties properties;

//    private static String secret = "dianpingSecret123456";

    public String getToken(long userId){
        String compactJws = Jwts.builder()
                .setSubject("corp_dianping")
                .claim("user_id", ""+userId)
                .claim("start_time", ""+System.currentTimeMillis())
                .signWith(SignatureAlgorithm.HS512, properties.getTokenSecret())
                .compact();
        return compactJws;
    }


    public Long getUserId(String token){

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(properties.getTokenSecret())
                    .parseClaimsJws(token)
                    .getBody();
            Object object = claims.get("user_id");

            Long ret = Long.parseLong(object.toString());
            return ret;

        } catch (Exception e) {
//            e.printStackTrace();
            logger.error(e.getMessage());
        }

        throw new BizRuntimeException("invalid token");
    }

    public static List<TagCountDTO> countTag(List<String> tagStrs){

        List<TagCountDTO> tagList = new ArrayList<>();
        if(tagStrs == null){
            return tagList;
        }

        HashMap<String, Integer> tagCountMap = new HashMap<>();

        if(tagStrs.size() > 0){
            for(String str : tagStrs){
                String[] tags = str.split("_");

                for (String tag : tags){
                    if(tagCountMap.containsKey(tag)){
                        tagCountMap.put(tag, tagCountMap.get(tag)+1);

                    }else{
                        tagCountMap.put(tag, 1);
                    }
                }
            }
        }

        tagCountMap.forEach((k,v) -> {
            TagCountDTO tc = new TagCountDTO();
            tc.setName(k);
            tc.setCount(v);
            tagList.add(tc);

        });

        tagList.sort((o1, o2)->{
            if(o1.getCount() >= o2.getCount()){
                return -1;
            }
            else {
                return 1;
            }
        });

        return tagList;
    }

    public static WxLoginResponse loginToWX(String loginUrl, String jscode){

        String urlStr = String.format(loginUrl, jscode);

        RestTemplate restTemplate = new RestTemplate();
        String str = restTemplate.getForObject(urlStr, String.class);

        Gson gson = new Gson();
        WxLoginResponse response = gson.fromJson(str, WxLoginResponse.class);

        if( response == null){
            logger.error("weixin login failed : response == null url="+urlStr);
            throw new BizRuntimeException("wxLogin no response");
        }

        if(response.getErrmsg() != null ){
            logger.error("weixin login failed : url="+urlStr+" errmsg="+response.getErrmsg());
            throw new BizRuntimeException(response.getErrmsg());
        }

        return response;
    }


//    public static void collectTags(List<CommentDTO> comments, List<Comment> raws) {
//
//        for (CommentDTO comment : comments) {
//            List<String> tags = new ArrayList<>();
//
//
//            for (Comment raw : raws) {
//                if (!contain(tags, raw.getTags())) {
//                    tags.add(raw.getTags());
//                }
//            }
//
//            comment.setTags(tags);
//        }
//
//    }
//
//    public static List<CommentDTO> distinct(List<Comment> raw) {
//        List<CommentDTO> comments = new ArrayList<>();
//        for (Comment tmp : raw) {
//            Pair<Integer, Boolean> exist = exist(comments, tmp);
//
//            if (!exist.getValue()) {
//                CommentDTO cmt = new CommentDTO(tmp);
//                comments.add(cmt);
//            }
//        }
//
//        return comments;
//    }
//
//    public static Pair<Integer, Boolean> exist(List<CommentDTO> list, Comment target) {
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getId() == target.getId()) {
//                return new Pair<>(i, true);
//            }
//        }
//        return new Pair<>(-1, false);
//    }
//
//    public static boolean contain(List<String> tags, String tag) {
//        for (String tmp : tags) {
//            if (tmp.equals(tag)) {
//                return true;
//            }
//        }
//        return false;
//    }
}