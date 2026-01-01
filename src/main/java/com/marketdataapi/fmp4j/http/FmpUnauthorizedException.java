package com.marketdataapi.fmp4j.http;

public class FmpUnauthorizedException extends FmpHttpException {
    public FmpUnauthorizedException(String message, Object... args) {
        super(message, args);
    }
}
