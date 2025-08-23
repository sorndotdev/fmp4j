package dev.sorn.fmp4j.types;

import static java.util.Arrays.stream;

import dev.sorn.fmp4j.exceptions.FmpInvalidStructureException;
import java.util.Objects;

public enum FmpStructure implements FmpValueObject<String> {
    FLAT("flat");

    private final String value;

    FmpStructure(String value) {
        this.value = value;
    }

    public static FmpStructure structure(String value) {
        return stream(values())
                .filter(structure -> Objects.equals(structure.value, value))
                .findFirst()
                .orElseThrow(() -> new FmpInvalidStructureException("[%s] is not a valid structure", value));
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
