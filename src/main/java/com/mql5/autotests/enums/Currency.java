package com.mql5.autotests.enums;

public enum Currency {
    USD("US dollar", 1),
    EUR("Euro", 2),
    JPY("Japanese yen", 4),
    GBP("Pound sterling", 8),
    CAD("Canadian dollar", 16),
    AUD("Australian Dollar", 32),
    CHF("Swiss frank", 64),
    CNY("Chinese yuan", 128),
    NZD("New Zealand dollar", 256),
    BRL("Brazilian real", 1024),
    KRW("South Korean won", 2048),
    HKD("Hong Kong dollar", 4096),
    SGD("Singapore dollar", 8192),
    MXN("Mexican peso", 16384);

    private String name;
    private int value;

    Currency(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
