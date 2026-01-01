package com.marketdataapi.fmp4j.json;

import com.fasterxml.jackson.core.type.TypeReference;
import java.lang.reflect.Type;
import java.util.List;

public final class FmpJsonUtils {
    private FmpJsonUtils() {
        throw new AssertionError(FmpJsonUtils.class.getSimpleName() + " cannot be instantiated.");
    }

    public static <T> TypeReference<T> typeRef(Class<T> clazz) {
        return new TypeReference<T>() {
            @Override
            public Type getType() {
                return clazz;
            }
        };
    }

    public static <T> TypeReference<List<T>> typeRefList(Class<T> clazz) {
        return new TypeReference<>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        };
    }
}
