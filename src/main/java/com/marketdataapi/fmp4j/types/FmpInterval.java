package com.marketdataapi.fmp4j.types;

import static java.util.Arrays.stream;

import com.marketdataapi.fmp4j.exceptions.FmpInvalidIntervalException;

public enum FmpInterval implements FmpValueObject<String> {
    ONE_MINUTE("1min"),
    FIVE_MINUTE("5min"),
    FIFTEEN_MINUTE("15min"),
    THIRTY_MINUTE("30min"),
    ONE_HOUR("1hour"),
    FOUR_HOUR("4hour");

    private final String value;

    FmpInterval(String value) {
        this.value = value;
    }

    public static FmpInterval interval(String value) {
        return stream(FmpInterval.values())
                .filter(p -> p.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new FmpInvalidIntervalException("[%s] is not a valid interval", value));
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value();
    }
}
