---
name: API Integration
about: Request a new API integration
title: "Add new API integration: /replace-this-with-endpoint"
labels: enhancement
assignees: ''

---

**Instructions**
Replace *all* example values below with your actual implementation details. Do **not** keep the examples as-is. Make
sure to read the full [documentation](https://github.com/marketdataapi/fmp4j/tree/master/docs) before contributing.

---

**FMP Docs Link:**
*Replace with the direct link to the relevant endpoint in the Financial Modeling Prep docs*

> https://site.financialmodelingprep.com/developer/docs/stable#search-by-symbol

**Parameters:**
*Replace with the required and optional endpoint parameters (or `N/A`)*

> - Required: symbol, from, to
> - Optional: page, limit


**Proposed model class name:**
*Replace with the name of the model class you plan to add*

> FmpEtfHolding

**Proposed client class name:**
*Replace with the name of the client class*

> FmpEtfClient

**Proposed client method names:**
*Replace with the method names in the client*

> - info()
> - holdings()
> - sectorWeightings()

---

## Requirements

**All models**

- [ ] start with `Fmp`
- [ ] are implemented as Java 16+ records
- [ ] implement the `FmpModel` interface
- [ ] have `serialVersionUID`
- [ ] have test: `is_serializable`
- [ ] have test: `serializes`
- [ ] use date time classes for dates, instead of strings, where appropriate
- [ ] use value objects, instead of strings, where appropriate
- [ ] have `*TestData` in `testFixtures`

**All services**

- [ ] use the model name + `Service` suffix
- [ ] are added to the appropriate FmpClient
- [ ] have tests with real responses from FMP
- [ ] with responses stored as JSON in `testFixtures` (*for large responses, include only an excerpt*)

**General**

- [ ] aim for 100% test coverage; TDD is preferred
- [ ] double-check your work before creating a PR
- [ ] refactor unrelated code in a separate PR from feature or bugfix changes (don’t refactor "as you go")
- [ ] adhere to the existing code style