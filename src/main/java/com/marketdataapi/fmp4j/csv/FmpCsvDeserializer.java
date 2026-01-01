package com.marketdataapi.fmp4j.csv;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.marketdataapi.fmp4j.exceptions.FmpDeserializationException;
import com.marketdataapi.fmp4j.http.FmpDeserializer;
import com.marketdataapi.fmp4j.json.FmpJsonModule;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public final class FmpCsvDeserializer implements FmpDeserializer {
    public static final FmpCsvDeserializer FMP_CSV_DESERIALIZER = new FmpCsvDeserializer();
    private static final CsvMapper CSV_MAPPER = (CsvMapper) new CsvMapper()
            .findAndRegisterModules()
            .configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(JsonReadFeature.ALLOW_TRAILING_COMMA.mappedFeature(), true)
            .registerModule(new FmpJsonModule());

    private FmpCsvDeserializer() {
        // prevent direct instantiation
    }

    private String removeByteOrderMark(String content) {
        if (content.startsWith("\uFEFF")) {
            return content.substring(1);
        } else {
            return content;
        }
    }

    @Override
    public <T> List<T> deserialize(String content, Class<T> clazz) {
        try {
            final var cleanedCsv = removeByteOrderMark(content);
            final var schema = emptySchema().withHeader().withNullValue("");
            final var reader = CSV_MAPPER.readerFor(clazz).with(schema);
            return reader.readValues(new StringReader(cleanedCsv)).readAll().stream()
                    .filter(clazz::isInstance)
                    .map(clazz::cast)
                    .toList();
        } catch (IOException e) {
            throw new FmpDeserializationException(
                    e, "Failed to deserialize CSV to List<%s>: %s", clazz.getSimpleName(), content);
        }
    }
}
