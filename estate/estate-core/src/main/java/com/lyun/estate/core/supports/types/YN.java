package com.lyun.estate.core.supports.types;

public enum YN {
    Y("罗辑是"),
    N("罗辑非");

    private final String label;

    YN(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
