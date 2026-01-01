package com.marketdataapi.fmp4j.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

class FmpJsonUtilsTest {

    @Test
    void constructor_throws_assertion_error() throws Exception {
        // given
        Constructor<FmpJsonUtils> constructor = FmpJsonUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        try {
            // when
            constructor.newInstance();
        } catch (InvocationTargetException e) {
            // then
            Throwable cause = e.getCause();
            assertTrue(cause instanceof AssertionError);
            assertEquals("FmpJsonUtils cannot be instantiated.", cause.getMessage());
        }
    }
}
