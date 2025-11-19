package dev.sorn.fmp4j.deserialize;

import com.fasterxml.jackson.core.type.TypeReference;

public interface FmpDeserializer {
    <T> T deserialize(String content, TypeReference<T> type);
}
