package dev.sorn.fmp4j.csv;

import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dev.sorn.fmp4j.deserialize.FmpDeserializer;
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

    private FmpCsvDeserializer() {}

    @Override
    public <T> T deserialize(String content, TypeReference<T> type) {
        try {
            JavaType javaType = CSV_MAPPER.getTypeFactory().constructType(type.getType());

            JavaType componentType = javaType.getContentType();
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withNullValue("");
            ObjectReader reader = CSV_MAPPER.readerFor(componentType).with(schema);
            MappingIterator<?> iterator = reader.readValues(new StringReader(content));
            var list = iterator.readAll();

            Object array = Array.newInstance(componentType.getRawClass(), list.size());
            for (int i = 0; i < list.size(); i++) {
                Array.set(array, i, list.get(i));
            }
            return (T) array;
        } catch (IOException e) {
            throw new FmpCsvException(
                    e, "Failed to deserialize CSV to '%s': %s", type.getType().getTypeName(), content);
        }
    }
}
