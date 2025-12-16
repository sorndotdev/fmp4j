package dev.sorn.fmp4j.json;

import static dev.sorn.fmp4j.json.FmpJsonDeserializer.FMP_JSON_DESERIALIZER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.TestObject;
import dev.sorn.fmp4j.TestObjectValue;
import dev.sorn.fmp4j.exceptions.FmpDeserializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpJsonDeserializerTest {

    private FmpJsonDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = FMP_JSON_DESERIALIZER;
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
        var obj = deserializer.deserialize(json, TestObject.class);

        // then
        assertEquals(2, obj.size());
        assertEquals("key3", obj.get(0).key());
        assertEquals("key7", obj.get(1).key());
        assertEquals(new TestObjectValue(28), obj.get(0).object());
        assertEquals(new TestObjectValue(42), obj.get(1).object());
    }

    @Test
    void deserialize_array_fails_on_malformed_json() {
        // given
        var malformedJson = "[";
        // when // then
        var e = assertThrows(
                FmpDeserializationException.class,
                () -> FMP_JSON_DESERIALIZER.deserialize(malformedJson, TestObject.class));
        assertEquals("Failed to deserialize JSON to 'TestObject': [", e.getMessage());
    }

    @Test
    void deserialize_array_fails_on_element_type_mismatch() {
        var invalidElementJson =
                """
            [
                {"key": "valid", "object": {"value": 1}},
                {"key": "invalid", "object": "not_an_object"}
            ]
            """;
        // when // then
        var e = assertThrows(
                FmpDeserializationException.class,
                () -> FMP_JSON_DESERIALIZER.deserialize(invalidElementJson, TestObject.class));
        assertEquals(
                """
            Failed to deserialize JSON to 'TestObject': [
                {"key": "valid", "object": {"value": 1}},
                {"key": "invalid", "object": "not_an_object"}
            ]
            """
                        .replaceAll("\\s", ""),
                e.getMessage().replaceAll("\\s", ""));
    }
}
