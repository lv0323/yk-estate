package com.lyun.estate.op.corp.entity;

/**
 * Created by localuser on 2017/5/31.
 */
public class BooleanResponse {
    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    boolean liked;
}
