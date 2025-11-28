package dev.sorn.fmp4j.csv;

import static com.fasterxml.jackson.dataformat.csv.CsvSchema.emptySchema;
import static java.util.stream.IntStream.range;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import dev.sorn.fmp4j.exceptions.FmpDeserializationException;
import dev.sorn.fmp4j.http.FmpDeserializer;
import dev.sorn.fmp4j.json.FmpJsonModule;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;

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
    public <T> T deserialize(String content, TypeReference<T> type) {
        try {
            final var cleanedCsv = removeByteOrderMark(content);
            final var javaType = CSV_MAPPER.getTypeFactory().constructType(type.getType());
            final var componentType = javaType.getContentType();
            final var schema = emptySchema().withHeader().withNullValue("");
            final var reader = CSV_MAPPER.readerFor(componentType).with(schema);
            final var iterator = reader.readValues(new StringReader(cleanedCsv));
            final var list = iterator.readAll();
            final var array = Array.newInstance(componentType.getRawClass(), list.size());
            range(0, list.size()).forEach(i -> Array.set(array, i, list.get(i)));
            return (T) array;
        } catch (IOException e) {
            throw new FmpDeserializationException(
                    e, "Failed to deserialize CSV to '%s': %s", type.getType().getTypeName(), content);
        }
    }
}
