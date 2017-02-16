package com.lyun.estate.mgt.auth.def;

/**
 * Created by Jeffrey on 2017-02-16.
 */
public class SaltSugar {

    private String salt;
    private String sugar;

    public String getSalt() {
        return salt;
    }

    public SaltSugar setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public String getSugar() {
        return sugar;
    }

    public SaltSugar setSugar(String sugar) {
        this.sugar = sugar;
        return this;
    }
}
