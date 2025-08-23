package dev.sorn.fmp4j.types;

import static java.util.Arrays.stream;

import dev.sorn.fmp4j.exceptions.FmpInvalidPeriodException;

public enum FmpPeriod implements FmpValueObject<String> {
    ANNUAL("annual"),
    QUARTER("quarter"),
    Q1("Q1"),
    Q2("Q2"),
    Q3("Q3"),
    Q4("Q4"),
    FY("FY");

    private final String value;

    FmpPeriod(String value) {
        this.value = value;
    }

    public static FmpPeriod period(String value) {
        return stream(FmpPeriod.values())
                .filter(p -> p.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new FmpInvalidPeriodException("[%s] is not a valid period", value));
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
