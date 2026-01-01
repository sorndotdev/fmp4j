package com.marketdataapi.fmp4j.http;

@FunctionalInterface
public interface FmpDeserializationRegistry {
    <T> FmpDeserializer resolve(FmpContentType contentType);
}
