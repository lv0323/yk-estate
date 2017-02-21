package com.lyun.estate.mgt.supports;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-02-21.
 */
public class CommonResp {

    private Result result;
    private Map<String, Object> ext = new HashMap<>();

    private CommonResp() {
    }

    public static CommonResp succeed() {
        return new CommonResp().setResult(Result.SUCCEED);
    }

    public static CommonResp failed() {
        return new CommonResp().setResult(Result.FAILED);
    }

    public Result getResult() {
        return result;
    }

    public CommonResp setResult(Result result) {
        this.result = result;
        return this;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public CommonResp addExt(String key, Object value) {
        this.ext.put(key, value);
        return this;
    }

    private enum Result {
        SUCCEED,
        FAILED
    }


}
