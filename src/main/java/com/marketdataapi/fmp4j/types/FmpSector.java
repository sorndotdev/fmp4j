package com.marketdataapi.fmp4j.types;

import static java.util.Arrays.stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.marketdataapi.fmp4j.exceptions.FmpInvalidSectorException;
import java.util.Objects;

public enum FmpSector implements FmpValueObject<String> {
    BASIC_MATERIALS("Basic Materials"),
    COMMUNICATION_SERVICES("Communication Services"),
    CONSUMER_CYCLICAL("Consumer Cyclical"),
    CONSUMER_DEFENSIVE("Consumer Defensive"),
    ENERGY("Energy"),
    FINANCIAL_SERVICES("Financial Services"),
    HEALTHCARE("Healthcare"),
    INDUSTRIALS("Industrials"),
    REAL_ESTATE("Real Estate"),
    TECHNOLOGY("Technology"),
    UTILITIES("Utilities");

    private final String value;

    FmpSector(String value) {
        this.value = value;
    }

    @JsonCreator
    public static FmpSector sector(String value) {
        return stream(values())
                .filter(sector -> Objects.equals(sector.value, value))
                .findFirst()
                .orElseThrow(() -> new FmpInvalidSectorException("[%s] is not a valid sector", value));
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
