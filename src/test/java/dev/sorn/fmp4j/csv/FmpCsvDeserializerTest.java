package dev.sorn.fmp4j.csv;

import static dev.sorn.fmp4j.csv.FmpCsvDeserializer.FMP_CSV_DESERIALIZER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.sorn.fmp4j.TestCsvObject;
import dev.sorn.fmp4j.exceptions.FmpDeserializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FmpCsvDeserializerTest {

    private FmpCsvDeserializer deserializer;

    @BeforeEach
    void setUp() {
        deserializer = FMP_CSV_DESERIALIZER;
    }

    @Test
    void deserialize_array() {
        // given
        var csv =
                """
            symbol,name,price,quantity
            AAPL,Apple Inc.,150.25,100
            GOOGL,Alphabet Inc.,2800.50,50
            MSFT,Microsoft Corp.,300.75,75
            """;

        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);

        // then
        assertEquals(3, result.size());
        assertEquals("AAPL", result.get(0).symbol());
        assertEquals("Apple Inc.", result.get(0).name());
        assertEquals(150.25, result.get(0).price());
        assertEquals(100, result.get(0).quantity());
        assertEquals("GOOGL", result.get(1).symbol());
        assertEquals("Alphabet Inc.", result.get(1).name());
        assertEquals(2800.50, result.get(1).price());
        assertEquals(50, result.get(1).quantity());
        assertEquals("MSFT", result.get(2).symbol());
        assertEquals("Microsoft Corp.", result.get(2).name());
        assertEquals(300.75, result.get(2).price());
        assertEquals(75, result.get(2).quantity());
    }

    @Test
    void deserialize_array_with_null_values() {
        // given
        var csv =
                """
            symbol,name,price,quantity
            AAPL,Apple Inc.,,100
            GOOGL,,2800.50,
            """;
        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);
        // then
        assertEquals(2, result.size());
        assertEquals("AAPL", result.get(0).symbol());
        assertEquals("Apple Inc.", result.get(0).name());
        assertNull(result.get(0).price());
        assertEquals(100, result.get(0).quantity());
        assertEquals("GOOGL", result.get(1).symbol());
        assertNull(result.get(1).name());
        assertEquals(2800.50, result.get(1).price());
        assertNull(result.get(1).quantity());
    }

    @Test
    void deserialize_array_with_byte_order_mark() {
        // given
        var csv =
                """
            \uFEFFsymbol,name,price,quantity
            AAPL,Apple Inc.,150.25,100
            """;
        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);
        // then
        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).symbol());
        assertEquals("Apple Inc.", result.get(0).name());
        assertEquals(150.25, result.get(0).price());
        assertEquals(100, result.get(0).quantity());
    }

    @Test
    void deserialize_array_empty_content() {
        // given
        var csv = """
            symbol,name,price,quantity
            """;
        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);
        // then
        assertEquals(0, result.size());
    }

    @Test
    void deserialize_array_fails_on_malformed_csv() {
        // given
        var malformedCsv =
                """
            symbol,name,price,quantity
            AAPL,Apple Inc.,not_a_number,100
            """;
        // when // then
        var e = assertThrows(
                FmpDeserializationException.class,
                () -> FMP_CSV_DESERIALIZER.deserialize(malformedCsv, TestCsvObject.class));
        assertEquals(
                """
            Failed to deserialize CSV to List<TestCsvObject>: symbol,name,price,quantity
            AAPL,Apple Inc.,not_a_number,100
            """,
                e.getMessage());
    }

    @Test
    void deserialize_array_with_trailing_comma() {
        // given
        var csv = """
            symbol,name,price,quantity,
            AAPL,Apple Inc.,150.25,100,
            """;
        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);
        // then
        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).symbol());
        assertEquals("Apple Inc.", result.get(0).name());
        assertEquals(150.25, result.get(0).price());
        assertEquals(100, result.get(0).quantity());
    }

    @Test
    void deserialize_array_with_empty_string_as_null() {
        // given
        var csv = """
            symbol,name,price,quantity
            AAPL,"",150.25,100
            """;
        // when
        var result = deserializer.deserialize(csv, TestCsvObject.class);
        // then
        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).symbol());
        assertNull(result.get(0).name());
        assertEquals(150.25, result.get(0).price());
        assertEquals(100, result.get(0).quantity());
    }
}
