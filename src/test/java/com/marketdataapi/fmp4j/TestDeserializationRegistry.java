package com.marketdataapi.fmp4j;

import static com.marketdataapi.fmp4j.csv.FmpCsvDeserializer.FMP_CSV_DESERIALIZER;
import static com.marketdataapi.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;

import com.marketdataapi.fmp4j.http.FmpContentType;
import com.marketdataapi.fmp4j.http.FmpDeserializationRegistry;
import com.marketdataapi.fmp4j.http.FmpDeserializer;

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
