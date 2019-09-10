package com.mql5.autotests.enums;

public enum Country {
    UNITED_STATES("United States"),
    EUROPEAN_UNION("European Union"),
    JAPAN("Japan"),
    UNITED_KINGDOM("United Kingdom"),
    CANADA("Canada"),
    AUSTRALIA("Australia"),
    SWITZERLAND("Switzerland"),
    CHINA("China"),
    NEW_ZEALAND("New Zealand"),
    GERMANY("Germany"),
    FRANCE("France"),
    ITALY("Italy"),
    SPAIN("Spain"),
    BRAZIL("Brazil"),
    SOUTH_KOREA("South Korea"),
    HONG_KONG("Hong Kong"),
    SINGAPORE("Singapore"),
    MEXICO("Mexico");

    private String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
