package dev.sorn.fmp4j.models;

import java.io.Serial;

public record FmpEtfCountryWeighting(String country, String weightPercentage) implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
