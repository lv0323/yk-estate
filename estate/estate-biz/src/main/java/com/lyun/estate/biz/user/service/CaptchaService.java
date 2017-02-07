package com.lyun.estate.biz.user.service;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import com.lyun.estate.biz.user.repository.UserMapper;
import com.lyun.estate.core.config.EstateCacheConfig;
import com.lyun.estate.core.supports.LJSWordRenderer;
import com.lyun.estate.core.supports.exceptions.ValidateException;
import com.lyun.estate.core.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

@Service
public class CaptchaService {
    private static final int maxWidth = 320;
    private static final int maxHeight = 320;
    @Autowired
    UserMapper userMapper;

    @Autowired
    @Qualifier(EstateCacheConfig.MANAGER_10_5K)
    CacheManager cacheManager;

    public BufferedImage getCaptcha(int clientId, String id, int width, int height) {
        if (!ValidateUtil.isClientId(clientId)) {
            throw new ValidateException("clientId.not.exists", "客户端编号不存在");
        }
        if (width < 60 || width > maxWidth || height < 20 || height > maxHeight || width < height * 3) {
            throw new ValidateException("getVerifyCodeImage.illegal", "宽高非法");
        }
        int fontSize = (int) (height < width ? height * 0.8 : width * 0.8);
        if (fontSize < 20) {
            fontSize = 20;
        }
        java.util.List<Color> colors = new ArrayList<>();
        colors.add(Color.BLACK);
        java.util.List<Font> fonts = new ArrayList<>();
        fonts.add(new Font("Arial", Font.PLAIN, fontSize));
        fonts.add(new Font("Courier", Font.PLAIN, fontSize));
        final String chars = "abcdefghijkmnpqrstuvwxzABCDEFGHJKMNPQRSTUVWXYZ23456789";
        Captcha captcha = new Captcha.Builder(width, height)
                .addText(new DefaultTextProducer(4, chars.toCharArray()), new LJSWordRenderer(colors, fonts))
                .addBackground()
                .addNoise(new CurvedLineNoiseProducer(Color.ORANGE, 1.0f))
                .addNoise(new CurvedLineNoiseProducer(Color.BLACK, 2.0f))
                .gimp()
                .build();
        String captchaStr = clientId + ":" + id + ":" + captcha.getAnswer().toLowerCase();
        cacheManager.getCache(EstateCacheConfig.CAPTCHA_CACHE).put(captchaStr, captchaStr);
        return captcha.getImage();
    }

    public boolean isCaptchaCorrect(int clientId, String id, String code) {
        String captchaStr = clientId + ":" + id + ":" + code.toLowerCase();
        boolean result = cacheManager.getCache(EstateCacheConfig.CAPTCHA_CACHE).get(captchaStr) != null;
        cacheManager.getCache(EstateCacheConfig.CAPTCHA_CACHE).evict(captchaStr);
        return result;
    }
}
