package com.lyun.estate.biz.appconfig.entity;

/**
 * Created by Jeffrey on 2017-03-03.
 */
public class AndroidVersion {
    private String version;
    private Boolean forceUpdate;
    private String url;

    public String getVersion() {
        return version;
    }

    public AndroidVersion setVersion(String version) {
        this.version = version;
        return this;
    }

    public Boolean getForceUpdate() {
        return forceUpdate;
    }

    public AndroidVersion setForceUpdate(Boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public AndroidVersion setUrl(String url) {
        this.url = url;
        return this;
    }
}
