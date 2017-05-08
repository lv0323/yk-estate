package com.lyun.estate.op.test;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeffrey on 2017-05-08.
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("get")
    public Map<String, String> testGet() {
        Map<String, String> map = new HashMap<>();
        map.put("resource", "test");
        map.put("method", "get");
        return map;
    }

    @PostMapping("post")
    public Map<String, String> testPost(@RequestParam String key1, @RequestParam String key2) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", key1);
        map.put("key2", key2);
        return map;
    }

}
