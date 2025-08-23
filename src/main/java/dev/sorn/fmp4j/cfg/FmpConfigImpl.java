package dev.sorn.fmp4j.cfg;

import java.io.IOException;
import java.util.Properties;

public final class FmpConfigImpl implements FmpConfig {
    public static final FmpConfigImpl FMP_CONFIG = new FmpConfigImpl();
    public static final String FMP4J_PROPERTIES_FILE = "fmp4j.properties";
    public static final String FMP4J_API_KEY_PROPERTY = "fmp4j.apiKey";
    public static final String FMP4J_BASE_URL_PROPERTY = "fmp4j.baseUrl";
    private final String fmpApiKey;
    private final String fmpBaseUrl;

    private FmpConfigImpl() {
        var properties = new Properties();
        try (var input = getClass().getClassLoader().getResourceAsStream(FMP4J_PROPERTIES_FILE)) {
            if (input == null) {
                throw new FmpConfigException("Sorry, unabled to find %s", FMP4J_PROPERTIES_FILE);
            }
            properties.load(input);
            fmpApiKey = properties.getProperty(FMP4J_API_KEY_PROPERTY);
            fmpBaseUrl = properties.getProperty(FMP4J_BASE_URL_PROPERTY);
        } catch (IOException e) {
            throw new FmpConfigException(e, "Sorry, unable to load property.");
        }
    }

    @Override
    public String fmpApiKey() {
        return fmpApiKey;
    }

    @Override
    public String fmpBaseUrl() {
        return fmpBaseUrl;
    }
}
