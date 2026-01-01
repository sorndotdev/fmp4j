# Project Overview

fmp4j is a lightweight Java SDK for the Financial Modeling Prep (FMP) API.

Follow the existing architecture, naming conventions, and idioms.
Do not introduce new abstractions or unrelated refactors.

# Coding Style

- Make minimal, focused edits.
- Make the code descriptive, so that comments become unnecessary.
- Follow the style of the existing codebase.
- No unrelated changes.
- Prefer static imports:
    - `ZERO` instead of `BigDecimal.ZERO`
    - `emptyMap()` instead of `Map.of()`
    - `symbol(String)` instead of `FmpSymbol.symbol(String)`
- Prefer value objects from `com.marketdataapi.fmp4j.types`
- For value objects/types, create a static factory method named after the type
  - Example: `symbol(String)` instead of `FmpSymbol.of(String)`
- All models are Java 16+ records
- All models must implement FmpModel and define `serialVersionUID`
  - `serialVersionUID` must be incremented if a model is changed

## New API Endpoints

- Only implement FMP stable endpoints
- Add required and optional parameters as per FMP docs
- Use appropriate value types from `com.marketdataapi.fmp4j.types`
- Prefer reusing existing models for exact matches
- Prefix new models with `Fmp`
- Service naming: `Fmp<Model>Service`

# Test Guidelines

Follow strict TDD and maintain 100% coverage.

## General

- Use `var` in tests (no `final var`, no explicit types)
- Prefer `*TestData` interfaces for fixtures

## Model Serialization

Always test serialization and deserialization.

Example:

```java

@Test
void is_serializable() throws Exception {
    // given
    var before = aRandomModel();

    // when
    var after = (FmpSomeModel) deserialize(serialize(before));

    // then
    assertEquals(before, after);
}

@Test
void serializes() throws Exception {
    // given
    var o = aRandomModel();

    // when // then
    verifySerialization(o);
}
```

## Test Naming

* Use snake_case
* Names must be clear and descriptive
* Never prefix with `test_` or `should_`

## Test Structure

Use BDD-like comment blocks:

```java
// given
// when
// then
```

## FMP Response Fixtures

* Always use real FMP responses from `testFixtures/resources/stable`
  * Always use `%3F` instead of `?` for Windows-compatible filenames.
  * Filenames mirror endpoint paths.
