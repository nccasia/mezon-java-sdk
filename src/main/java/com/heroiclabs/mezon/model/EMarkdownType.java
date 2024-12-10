package com.heroiclabs.mezon.model;

public enum EMarkdownType {
    TRIPLE("t"),
    SINGLE("s");

    private final String value;

    EMarkdownType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
