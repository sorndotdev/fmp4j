package com.marketdataapi.fmp4j.services;

import static com.marketdataapi.fmp4j.HttpClientStub.httpClientStub;
import static com.marketdataapi.fmp4j.TestDeserializationRegistry.TEST_DESERIALIZATION_REGISTRY;
import static com.marketdataapi.fmp4j.TestUtils.testResource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.marketdataapi.fmp4j.HttpClientStub;
import com.marketdataapi.fmp4j.cfg.FmpConfig;
import com.marketdataapi.fmp4j.http.FmpHttpClientImpl;
import com.marketdataapi.fmp4j.types.FmpApiKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class HttpTest {
    protected static final String BASE_URL = "https://financialmodelingprep.com/stable";
    protected static final FmpApiKey API_KEY = new FmpApiKey("ABCDEf0ghIjklmNO1pqRsT2u34VWx5y6");
    protected final HttpClientStub httpStub = httpClientStub();
    protected final FmpHttpClientImpl client = new FmpHttpClientImpl(httpStub, TEST_DESERIALIZATION_REGISTRY);
    protected final FmpConfig config = mock(FmpConfig.class);

    @BeforeEach
    void setupHttpTest() {
        when(config.fmpBaseUrl()).thenReturn(BASE_URL);
        when(config.fmpApiKey()).thenReturn(API_KEY);
    }

    protected synchronized <T> void mockHttpGetFromFile(String filename) {
        httpStub.configureResponse()
                .statusCode(200)
                .body(testResource(filename))
                .contentType(filename.endsWith(".json") ? "application/json" : "text/csv")
                .apply();
    }
}
