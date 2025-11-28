package dev.sorn.fmp4j.http;

import dev.sorn.fmp4j.types.FmpValueObject;

public enum FmpContentType implements FmpValueObject<String> {
    CSV("text/csv"),
    JSON("application/json");

    private final String value;

    FmpContentType(String value) {
        this.value = value;
    }

    public static FmpContentType fromContentTypeHeader(String header) {
        for (final var contentType : values()) {
            if (header != null && header.equals(contentType.value())) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Unsupported content type: " + header);
    }

    public String value() {
        return value;
    }
}
