package com.lyun.estate.op.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lyun.estate.op.corp.entity.BizIllegalArgumentException;
import com.lyun.estate.op.corp.entity.BizRuntimeException;
import com.lyun.estate.op.corp.entity.TagCount;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//
//import com.lyun.estate.op.corp.entity.Comment;
//import com.lyun.estate.op.corp.entity.RawComment;
//import javafx.util.Pair;
//
//import java.util.ArrayList;
//import java.util.List;
//
public class CorpUtil {

    private static String secret = "dianpingSecret123456";

    public static String getToken(long userId){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("sign_time", System.currentTimeMillis())
                    .withIssuer("op_dianping")
                    .sign(algorithm);

            return token;

        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        throw new BizRuntimeException("generate token failuer");
    }


    public static Long getUserId(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance

            verifier.verify(token);

            DecodedJWT jwt = JWT.decode(token);

            Claim claim = jwt.getClaim("userId");

            return claim.asLong();

        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }

        throw new BizIllegalArgumentException("invalid token");
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