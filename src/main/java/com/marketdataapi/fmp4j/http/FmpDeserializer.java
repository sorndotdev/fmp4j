package com.marketdataapi.fmp4j.http;

import java.util.List;

public interface FmpDeserializer {
    <T> List<T> deserialize(String content, Class<T> clazz);
}
