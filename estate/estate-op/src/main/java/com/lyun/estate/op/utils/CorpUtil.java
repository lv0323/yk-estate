package com.lyun.estate.op.utils;

import com.lyun.estate.op.corp.entity.BizRuntimeException;
import com.lyun.estate.op.corp.entity.TagCount;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CorpUtil {
    private static final Logger logger = LoggerFactory.getLogger(CorpUtil.class);

    private static String secret = "dianpingSecret123456";

    public static String getToken(long userId){
        String compactJws = Jwts.builder()
                .setSubject("corp_dianping")
                .claim("user_id", ""+userId)
                .claim("start_time", ""+System.currentTimeMillis())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return compactJws;
    }


    public static Long getUserId(String token){

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
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

    public static List<TagCount> countTag(List<String> tagStrs){

        HashMap<String, Integer> tagCountMap = new HashMap<>();
        List<TagCount> tagList = new ArrayList<>();
        if(tagStrs == null){
            return tagList;
        }

        if(tagStrs != null && tagStrs.size() > 0){
            for(String str : tagStrs){
                String[] tags = str.split("_");

                if(tags != null){
                    for (String tag : tags){
                        if(tagCountMap.containsKey(tag)){
                            tagCountMap.put(tag, tagCountMap.get(tag)+1);

                        }else{
                            tagCountMap.put(tag, 1);
                        }
                    }
                }
            }
        }



        tagCountMap.forEach((k,v) -> {
            TagCount tc = new TagCount();
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

//    public static void collectTags(List<Comment> comments, List<RawComment> raws) {
//
//        for (Comment comment : comments) {
//            List<String> tags = new ArrayList<>();
//
//
//            for (RawComment raw : raws) {
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
//    public static List<Comment> distinct(List<RawComment> raw) {
//        List<Comment> comments = new ArrayList<>();
//        for (RawComment tmp : raw) {
//            Pair<Integer, Boolean> exist = exist(comments, tmp);
//
//            if (!exist.getValue()) {
//                Comment cmt = new Comment(tmp);
//                comments.add(cmt);
//            }
//        }
//
//        return comments;
//    }
//
//    public static Pair<Integer, Boolean> exist(List<Comment> list, RawComment target) {
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