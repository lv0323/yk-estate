package com.lyun.estate.biz.filesystem;

public enum FileType {
    IMAGE(".png");

    private String suffix;

    FileType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
