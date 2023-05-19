package com.cash.flow.core.enums;

public enum CashType {
    IN("IN"),
    OUT("OUT");

    private final String value;

    CashType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
