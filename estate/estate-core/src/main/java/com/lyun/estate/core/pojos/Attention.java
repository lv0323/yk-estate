package com.lyun.estate.core.pojos;

public class Attention {
    private String dest;
    private String message;

    public String getDest() {
        return dest;
    }

    public Attention setDest(String dest) {
        this.dest = dest;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Attention setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "dest='" + dest + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}