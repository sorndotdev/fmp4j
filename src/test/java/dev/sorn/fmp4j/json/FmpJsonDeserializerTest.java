package dev.sorn.fmp4j.json;

import static dev.sorn.fmp4j.json.FmpJsonDeserializerImpl.FMP_JSON_DESERIALIZER;
import static dev.sorn.fmp4j.json.FmpJsonUtils.typeRef;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.TestObject;
import dev.sorn.fmp4j.TestObjectValue;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpJsonDeserializerTest {
    private FmpJsonDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = FMP_JSON_DESERIALIZER;
    }

    @Test
    void deserialize_object() {
        // given
        var json =
                """
                {
                    "key": "key",
                    "object": {
                        "value": 42
                    }
                }
                """;

        // when
        var obj = deserializer.fromJson(json, typeRef(TestObject.class));

        // then
        assertEquals("key", obj.key());
        assertEquals(new TestObjectValue(42), obj.object());
    }

    @Test
    void deserialize_array() {
        // given
        var json =
                """
                [
                    {
                        "key": "key3",
                        "object": {
                            "value": 28
                        }
                    },
                    {
                        "key": "key7",
                        "object": {
                            "value": 42
                        }
                    }
                ]
                """;

        // when
        var obj = deserializer.fromJson(json, typeRef(TestObject[].class));

        // then
        assertEquals(2, obj.length);
        assertEquals("key3", obj[0].key());
        assertEquals("key7", obj[1].key());
        assertEquals(new TestObjectValue(28), obj[0].object());
        assertEquals(new TestObjectValue(42), obj[1].object());
    }

    @Test
    void deserialize_object_failsOnMalformedJson() {
        // given
        var tr = typeRef(TestObject.class);
        var malformedJson = "{";

        // when
        Consumer<String> f = (json) -> FMP_JSON_DESERIALIZER.fromJson(json, tr);

        // then
        var e = assertThrows(FmpJsonException.class, () -> f.accept(malformedJson));
        assertEquals("Failed to deserialize JSON to 'dev.sorn.fmp4j.TestObject': {", e.getMessage());
    }

    @Test
    void deserialize_array_failsOnMalformedJson() {
        // given
        var tr = typeRef(TestObject[].class);
        var malformedJson = "[";

        // when
        Consumer<String> f = (json) -> FMP_JSON_DESERIALIZER.fromJson(json, tr);

        // then
        var e = assertThrows(FmpJsonException.class, () -> f.accept(malformedJson));
        assertEquals("Failed to deserialize JSON to 'dev.sorn.fmp4j.TestObject[]': [", e.getMessage());
    }

    @Test
    void deserialize_object_failsOnTypeMismatch() {
        // given
        var tr = typeRef(TestObject.class);
        var mismatchedJson =
                """
                {
                    "key": "key",
                    "object": "not_an_object"
                }
                """;

        // when
        Consumer<String> f = (json) -> FMP_JSON_DESERIALIZER.fromJson(json, tr);

        // then
        var e = assertThrows(FmpJsonException.class, () -> f.accept(mismatchedJson));
        assertEquals(
                """
                Failed to deserialize JSON to 'dev.sorn.fmp4j.TestObject': {
                    "key": "key",
                    "object": "not_an_object"
                }
                """
                        .replaceAll("\\s", ""),
                e.getMessage().replaceAll("\\s", ""));
    }

    @Test
    void deserialize_array_failsOnElementTypeMismatch() {
        var tr = typeRef(TestObject[].class);
        var invalidElementJson =
                """
                [
                    {"key": "valid", "object": {"value": 1}},
                    {"key": "invalid", "object": "not_an_object"}
                ]
                """;

        // when
        Consumer<String> f = (json) -> FMP_JSON_DESERIALIZER.fromJson(json, tr);

        // when // then
        var expectedMessage =
                """
                Failed to deserialize JSON to 'dev.sorn.fmp4j.TestObject[]': [
                    {"key": "valid", "object": {"value": 1}},
                    {"key": "invalid", "object": "not_an_object"}
                ]
                """
                        .replaceAll("\\s", "");
        var e = assertThrows(FmpJsonException.class, () -> f.accept(invalidElementJson));
        assertEquals(expectedMessage, e.getMessage().replaceAll("\\s", ""));
    }
}
