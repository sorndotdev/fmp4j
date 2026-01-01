package com.marketdataapi.fmp4j;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.junit.jupiter.api.BeforeEach;

/**
 * <a href="https://chat.deepseek.com">https://chat.deepseek.com</a>
 *
 * @author DeepSeek
 */
public class HttpClientStub implements HttpClient {
    private final ThreadLocal<ResponseConfig> currentConfig = new ThreadLocal<>();
    private final Supplier<ResponseConfig> defaultConfig = () -> new ResponseConfig("", 200, "application/json");

    @BeforeEach
    void resetStub() {
        this.configureResponse().apply();
    }

    public static HttpClientStub httpClientStub() {
        return new HttpClientStub();
    }

    public ResponseConfigurer configureResponse() {
        return new ResponseConfigurer(this);
    }

    public static class ResponseConfigurer {
        private final HttpClientStub stub;
        private String body = "";
        private int statusCode = 200;
        private String contentType = "application/json";

        private ResponseConfigurer(HttpClientStub stub) {
            this.stub = stub;
        }

        public ResponseConfigurer body(String body) {
            this.body = body;
            return this;
        }

        public ResponseConfigurer statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public ResponseConfigurer contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public void apply() {
            stub.currentConfig.set(new ResponseConfig(body, statusCode, contentType));
        }
    }

    private ClassicHttpResponse createResponse() {
        ResponseConfig config = currentConfig.get();
        if (config == null) config = defaultConfig.get();

        ClassicHttpResponse response = mock(ClassicHttpResponse.class);
        HttpEntity entity = mock(HttpEntity.class);

        try {
            InputStream content = new ByteArrayInputStream(config.body.getBytes(StandardCharsets.UTF_8));
            when(entity.getContent()).thenReturn(content);
            when(entity.getContentLength()).thenReturn((long) config.body.getBytes().length);
            when(entity.getContentType()).thenReturn(config.contentType);
            when(response.getEntity()).thenReturn(entity);
            when(response.getCode()).thenReturn(config.statusCode);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create HTTP response", e);
        }

        return response;
    }

    @Override
    public ClassicHttpResponse executeOpen(HttpHost target, ClassicHttpRequest request, HttpContext context) {
        return createResponse();
    }

    @Override
    public HttpResponse execute(ClassicHttpRequest request) {
        return createResponse();
    }

    @Override
    public HttpResponse execute(ClassicHttpRequest request, HttpContext context) {
        return createResponse();
    }

    @Override
    public ClassicHttpResponse execute(HttpHost target, ClassicHttpRequest request) {
        return createResponse();
    }

    @Override
    public HttpResponse execute(HttpHost target, ClassicHttpRequest request, HttpContext context) {
        return createResponse();
    }

    @Override
    public <T> T execute(ClassicHttpRequest request, HttpClientResponseHandler<? extends T> responseHandler)
            throws IOException {
        try {
            return responseHandler.handleResponse(createResponse());
        } catch (HttpException e) {
            throw new IOException(e);
        }
    }

    @Override
    public <T> T execute(
            ClassicHttpRequest request, HttpContext context, HttpClientResponseHandler<? extends T> responseHandler)
            throws IOException {
        try {
            return responseHandler.handleResponse(createResponse());
        } catch (HttpException e) {
            throw new IOException(e);
        }
    }

    @Override
    public <T> T execute(
            HttpHost target, ClassicHttpRequest request, HttpClientResponseHandler<? extends T> responseHandler)
            throws IOException {
        try {
            return responseHandler.handleResponse(createResponse());
        } catch (HttpException e) {
            throw new IOException(e);
        }
    }

    @Override
    public <T> T execute(
            HttpHost target,
            ClassicHttpRequest request,
            HttpContext context,
            HttpClientResponseHandler<? extends T> responseHandler)
            throws IOException {
        try {
            return responseHandler.handleResponse(createResponse());
        } catch (HttpException e) {
            throw new IOException(e);
        }
    }

    private static class ResponseConfig {
        final String body;
        final int statusCode;
        final String contentType;

        ResponseConfig(String body, int statusCode, String contentType) {
            this.body = body;
            this.statusCode = statusCode;
            this.contentType = contentType;
        }
    }
}
