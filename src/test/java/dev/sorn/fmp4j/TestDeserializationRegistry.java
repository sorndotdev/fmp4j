package dev.sorn.fmp4j;

import static dev.sorn.fmp4j.csv.FmpCsvDeserializer.FMP_CSV_DESERIALIZER;
import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;

import dev.sorn.fmp4j.http.FmpContentType;
import dev.sorn.fmp4j.http.FmpDeserializationRegistry;
import dev.sorn.fmp4j.http.FmpDeserializer;

public class TestDeserializationRegistry implements FmpDeserializationRegistry {
    public static final TestDeserializationRegistry TEST_DESERIALIZATION_REGISTRY = new TestDeserializationRegistry();

    @Override
    public <T> FmpDeserializer resolve(FmpContentType contentType) {
        return switch (contentType) {
            case CSV -> FMP_CSV_DESERIALIZER;
            case JSON -> FMP_JSON_DESERIALIZER;
        };
    }
}
