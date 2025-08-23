package dev.sorn.fmp4j.types;

import java.io.Serializable;

@FunctionalInterface
public interface FmpValueObject<T> extends Serializable {
    T value();
}
