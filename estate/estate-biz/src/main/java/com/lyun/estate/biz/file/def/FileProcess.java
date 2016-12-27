package com.lyun.estate.biz.file.def;

public enum FileProcess {
    NONE(0),
    WATERMARK(1);

    private int flag;

    FileProcess(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }
}
