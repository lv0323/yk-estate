package com.lyun.estate.amqp.pojos;

public class Attention {
    private String dest;
    private String message;

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Attention{" +
                "dest='" + dest + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
