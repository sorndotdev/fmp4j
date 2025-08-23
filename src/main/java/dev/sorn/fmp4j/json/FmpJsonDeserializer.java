package dev.sorn.fmp4j.json;

import com.fasterxml.jackson.core.type.TypeReference;

public interface FmpJsonDeserializer {
    <T> T fromJson(String json, TypeReference<T> type);
}
