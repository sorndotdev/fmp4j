package dev.sorn.fmp4j.json;

import com.fasterxml.jackson.core.type.TypeReference;
import java.lang.reflect.Type;

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
}
