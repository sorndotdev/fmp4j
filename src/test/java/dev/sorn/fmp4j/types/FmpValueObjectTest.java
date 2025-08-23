package dev.sorn.fmp4j.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;

class FmpValueObjectTest {
    @Test
    void integer_value_object() {
        // given
        var i = 42;

        // when
        var vo = (FmpValueObject<Integer>) () -> i;

        // then
        assertEquals(i, vo.value());
    }

    @Test
    void string_value_object() {
        // given
        var s = "42";

        // when
        var vo = (FmpValueObject<String>) () -> s;

        // then
        assertEquals(s, vo.value());
    }

    @Test
    void complex_value_object() {
        // given
        var m = Map.of("secret", 42);

        // when
        var vo = (FmpValueObject<Map<String, Integer>>) () -> m;

        // then
        assertEquals(m, vo.value());
    }
}
