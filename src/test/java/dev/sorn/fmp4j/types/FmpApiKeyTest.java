package dev.sorn.fmp4j.types;

import static dev.sorn.fmp4j.types.FmpApiKey.FMP_API_KEY_PATTERN;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.sorn.fmp4j.exceptions.FmpInvalidApiKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FmpApiKeyTest {
    @Test
    void value_returns_raw_value() {
        // given
        var apiKey = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var val = apiKey.value();

        // then
        assertEquals("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6", val);
    }

    @Test
    void to_string_masked() {
        // given
        var apiKey = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var str = apiKey.toString();

        // then
        assertEquals("AB****************************y6", str);
    }

    @Test
    void null_key_throws() {
        // given
        var nullKey = (String) null;

        // when
        Function<String, FmpApiKey> f = FmpApiKey::new;

        // then
        var e = assertThrows(FmpInvalidApiKey.class, () -> f.apply(nullKey));
        assertEquals("'value' must not be null or blank", e.getMessage());
    }

    @Test
    void blank_key_throws() {
        // given
        var blankKey = "";

        // when
        Function<String, FmpApiKey> f = FmpApiKey::new;

        // then
        var e = assertThrows(FmpInvalidApiKey.class, () -> f.apply(blankKey));
        assertEquals("'value' must not be null or blank", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y", // too short
                "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6z", // too long
                "ABCDEf0ghIjklmN$1pqRsT2u34VWx5y6" // invalid symbol $
            })
    void invalid_pattern_throws(String value) {
        // when
        Function<String, FmpApiKey> f = FmpApiKey::new;

        // then
        var e = assertThrows(FmpInvalidApiKey.class, () -> f.apply(value));
        assertEquals(format("'value' [%s] does not match pattern [%s]", value, FMP_API_KEY_PATTERN), e.getMessage());
    }

    @Test
    void hash_code_value() {
        // given
        var str = "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6";
        var k = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var hc = k.hashCode();

        // then
        assertEquals(str.hashCode(), hc);
    }

    @Test
    void equals_same_true() {
        // given
        var k = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var eq = k.equals(k);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_identical_true() {
        // given
        var k1 = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        var k2 = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var eq = k1.equals(k2);

        // then
        assertTrue(eq);
    }

    @Test
    void equals_null_false() {
        // given
        var k1 = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        var k2 = (FmpApiKey) null;

        // when
        var eq = k1.equals(k2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_different_false() {
        // given
        var k1 = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        var k2 = new FmpApiKey("ABcDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when
        var eq = k1.equals(k2);

        // then
        assertFalse(eq);
    }

    @Test
    void equals_wrong_instance_false() {
        // given
        var k1 = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
        var k2 = "ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6";

        // when
        var eq = k1.equals(k2);

        // then
        assertFalse(eq);
    }

    @Test
    void transient_field_should_not_be_serialized() throws Exception {
        // given
        var original = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");

        // when: serialize
        var baos = new ByteArrayOutputStream();
        try (ObjectOutputStream out = new ObjectOutputStream(baos)) {
            out.writeObject(original);
        }

        // and: deserialize
        FmpApiKey deserialized;
        try (var in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            deserialized = (FmpApiKey) in.readObject();
        }

        // then
        assertEquals("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6", original.value());
        assertNull(deserialized.value(), "transient field should not be serialized");
    }
}
